package com.meta1203.ChessPlayer;

import java.util.List;

public class CalculateMovesThread extends Thread {
	private Turn originTurn;
	
	public CalculateMovesThread(Turn t) {
		originTurn = t;
	}
	
	@Override
	public void run() {
		List<Move> moves = originTurn.getData().getMoves(false);
		for (Move x : moves) {
			Main.logger.info(x.toString());
			originTurn.addChild(originTurn.getData().executeMove(x));
		}
	}
}
