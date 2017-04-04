package com.meta1203.ChessPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Cloneable {
	private Map<Coordinate,ChessPiece> inPlay;
	private int whitePointValue = 0;
	private int blackPointValue = 0;

	public Board() {
		inPlay = new HashMap<Coordinate,ChessPiece>();
	}

	public void populate() {
		// TODO: add the pieces to the board
	}

	public ChessPiece getPosition(Coordinate c) {
		return inPlay.get(c);
	}

	public Board executeMove(Move m) {
		// Deep copy the board
		Board ret = null;
		ret = this.clone();
		// Time to make the move
		ret.inPlay.remove(m.getPiece().getCurrentLocation(), m.getPiece());
		if (m.getAttack() != null) {
			ret.inPlay.remove(m.getNewPosition(), m.getAttack());
			if (m.getPiece().isWhite()) {
				ret.whitePointValue = ret.whitePointValue + m.getValue();
			} else {
				ret.blackPointValue = ret.blackPointValue + m.getValue();
			}
		}
		ret.inPlay.put(m.getNewPosition(), m.getPiece());
		return ret;
	}

	public List<Move> getMoves(boolean white) {
		// TODO: finish this up
		List<Move> total = new ArrayList<Move>();
		for (ChessPiece x : inPlay.values()) {
			if (x.isWhite() == white) {
				total.addAll(x.getValidMoves());
			}
		}
		return total;
	}

	// Cloning stuff for deep copies
	private Board(Map<Coordinate,ChessPiece> inPlay, int whitePoint, int blackPoint) {
		this.inPlay = inPlay;
		blackPointValue = blackPoint;
		whitePointValue = whitePoint;
	}

	@Override
	protected Board clone() {
		Map<Coordinate,ChessPiece> cloneMap = new HashMap<Coordinate, ChessPiece>();
		for (Map.Entry<Coordinate, ChessPiece> x : this.inPlay.entrySet()) {
			cloneMap.put(x.getKey(), x.getValue());
		}
		return new Board(cloneMap, this.whitePointValue, this.blackPointValue);
	}

	public int getWhitePointValue() {
		return whitePointValue;
	}

	public int getBlackPointValue() {
		return blackPointValue;
	}
}
