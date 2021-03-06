package com.meta1203.ChessPlayer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import com.meta1203.ChessPlayer.Board;
import com.meta1203.ChessPlayer.CalculateMovesThread;
import com.meta1203.ChessPlayer.ChessPiece;
import com.meta1203.ChessPlayer.Coordinate;
import com.meta1203.ChessPlayer.Main;
import com.meta1203.ChessPlayer.Move;
import com.meta1203.ChessPlayer.Turn;

public class Command implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		String command = "";
		if (source instanceof JTextComponent) {
			command = ((JTextComponent)source).getText();
		} else {
			command = ((JTextComponent)((JComponent)source).getParent().getComponent(1)).getText();
		}
		String[] splitCommand = command.split(" ");
		if (splitCommand.length != 3) {
			// TODO: Alert the user of invalid command
			return;
		}
		String piece = splitCommand[0].toLowerCase();
		String coord1 = splitCommand[1];
		String coord2 = splitCommand[2];
		// runPlayerTurn(piece, Coordinate.letterToCoord(coord1, y))
	}

	// Perform the player's turn
	public boolean runPlayerTurn(String pieceName, Coordinate coord1, Coordinate coord2) {
		ChessPiece piece = Main.singleton.currentTurn.getBoard().getPosition(coord1);
		// Sanity check to make sure that the piece is the one we want to move, and its where the player thinks it is
		if (!piece.getPieceName().toLowerCase().equals(piece) || !piece.getCurrentLocation().equals(coord1)) {
			Main.singleton.window.addText("Invalid piece: " + piece.getPieceName() + " @ " + piece.getCurrentLocation().toString());
			return false;
		}
		Move newMove = new Move(piece, coord2, Main.singleton.currentTurn.getBoard());
		if (!piece.getValidMoves(Main.singleton.currentTurn).contains(newMove)) {
			Main.singleton.window.addText("Invalid move: " + piece.getPieceName() + " " + coord1.toString() + " to " + coord2.toString());
			return false;
		}
		Board newBoard = Main.singleton.currentTurn.getBoard().executeMove(newMove);
		for (Turn x : Main.singleton.currentTurn.getChildren()) {
			if (x.getBoard().equals(newBoard)) {
				Main.singleton.currentTurn = x;
				return true;
			}
		}
		// This shouldn't ever execute, so log and crash if it does
		Main.logger.severe("ERROR: Valid move not found within the turn-set!");
		Main.logger.severe(newMove.toString());
		throw new RuntimeException("Valid move not found within the turn-set!");
	}

	// Perform the computer's turn
	public void runComputerTurn() throws InterruptedException {
		// Already know what the best move is beforehand, just execute it now
		Main.singleton.currentTurn = Main.singleton.currentTurn.bestMove();
		// Check the depth so we don't recalculate moves (target is 6 moves ahead)
		int depth = Main.singleton.currentTurn.getDepth();
		// Set everything up
		ConcurrentLinkedQueue<Turn> queue = new ConcurrentLinkedQueue<Turn>();
		List<Turn> done = new ArrayList<Turn>();
		
		// If this is the first move, we need to set everything up properly
		if (depth == 0) {
			CalculateMovesThread cmt = new CalculateMovesThread(Main.singleton.currentTurn);
			cmt.run();
			depth = 1;
		}
		
		// Go down to the depth to begin with
		queue.addAll(Main.singleton.currentTurn.getChildren());
		for (int x = 0; x < depth; x++) {
			while (!queue.isEmpty()) {
				done.addAll(queue.poll().getChildren());
			}
			queue.addAll(done);
			done.clear();
		}
		
		// Calculate the missing moves
		for (int count = depth; count < 7; count++) {
			// Run through everything in the queue
			ExecutorService es = Executors.newCachedThreadPool();
			while (!queue.isEmpty()) {
				Turn t = queue.poll();
				done.add(t);
				es.execute(new CalculateMovesThread(t));
			}
			es.shutdown();
			es.awaitTermination(5, TimeUnit.DAYS);
			
			// Setup the next batch
			for (Turn x : done) {
				queue.addAll(x.getChildren());
			}
			done.clear();
		}
	}
}
