package com.meta1203.ChessPlayer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Cloneable, Comparator<Board>, Comparable<Board> {
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
		try {
			ret = this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(200);
		}
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
		return null;
	}
	
	@Override
	public int compareTo(Board other) {
		if (this.blackPointValue == other.blackPointValue) {
			return this.whitePointValue - other.whitePointValue;
		}
		return this.blackPointValue - other.blackPointValue;
	}

	@Override
	public int compare(Board one, Board another) {
		if (one.blackPointValue == another.blackPointValue) {
			return one.whitePointValue - another.whitePointValue;
		}
		return one.blackPointValue - another.blackPointValue;
	}
	
	// Cloning stuff for deep copies
	private Board(Map<Coordinate,ChessPiece> inPlay, int whitePoint, int blackPoint) {
		this.inPlay = inPlay;
		blackPointValue = blackPoint;
		whitePointValue = whitePoint;
	}
	
	@Override
	protected Board clone() throws CloneNotSupportedException {
		Map<Coordinate,ChessPiece> cloneMap = new HashMap<Coordinate, ChessPiece>();
		for (Map.Entry<Coordinate, ChessPiece> x : this.inPlay.entrySet()) {
			cloneMap.put(x.getKey(), x.getValue());
		}
		return new Board(cloneMap, this.whitePointValue, this.blackPointValue);
	}
}
