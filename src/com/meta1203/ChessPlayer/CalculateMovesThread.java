package com.meta1203.ChessPlayer;

import java.util.List;

public class CalculateMovesThread implements Runnable {
	private Turn originTurn;
	
	public CalculateMovesThread(Turn t) {
		originTurn = t;
	}
	
	@Override
	public void run() {
		List<Move> moves = originTurn.getBoard().getMoves(originTurn);
		for (Move x : moves) {
			// I don't know enough about the thread safety of the Java logger, so I'm not taking any chances
			synchronized (Main.logger) {
				Main.logger.info(x.toString());
			}
			originTurn.addChild(originTurn.getBoard().executeMove(x));
		}
	}
}
