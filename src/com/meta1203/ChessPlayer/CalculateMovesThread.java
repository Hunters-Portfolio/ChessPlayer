package com.meta1203.ChessPlayer;

import java.util.List;

public class CalculateMovesThread extends Thread {
	private Turn originTurn;
	
	public CalculateMovesThread(Turn t) {
		originTurn = t;
	}
	
	@Override
	public void run() {
		List<Move> moves = originTurn.getBoard().getMoves(originTurn);
		for (Move x : moves) {
			Main.logger.info(x.toString());
			originTurn.addChild(originTurn.getBoard().executeMove(x));
		}
	}
}
