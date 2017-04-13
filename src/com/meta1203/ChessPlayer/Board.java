package com.meta1203.ChessPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Cloneable {
	private ChessPiece[][] inPlay;
	private int whitePointValue = 0;
	private int blackPointValue = 0;

	public Board() {
		inPlay = new ChessPiece[8][8];
	}

	public void populate() {
		// TODO: add the pieces to the board
	}

	public ChessPiece getPosition(Coordinate c) {
		return inPlay[c.getX()][c.getY()];
	}

	public Board executeMove(Move m) {
		// Deep copy the board
		Board ret = null;
		ret = this.clone();
		// Time to make the move
		ret.inPlay[m.getPiece().getCurrentLocation().getX()][m.getPiece().getCurrentLocation().getY()] = null;
		if (m.getAttack() != null) {
			ret.inPlay[m.getNewPosition().getX()][m.getNewPosition().getY()] = null;
			if (m.getPiece().isWhite()) {
				ret.whitePointValue = ret.whitePointValue + m.getValue();
			} else {
				ret.blackPointValue = ret.blackPointValue + m.getValue();
			}
		}
		ret.inPlay[m.getNewPosition().getX()][m.getNewPosition().getY()] = m.getPiece();
		return ret;
	}

	public List<Move> getMoves(Turn t) {
		// TODO: finish this up
		List<Move> total = new ArrayList<Move>();
		for (ChessPiece[] x : inPlay) {
			for (ChessPiece y : x) {
				if (y == null) {
					continue;
				}
				if (y.isWhite() != t.isWhiteTurn()) {
					total.addAll(y.getValidMoves(t));
				}
			}
		}
		return total;
	}

	// Cloning stuff for deep copies
	private Board(ChessPiece[][] inPlay, int whitePoint, int blackPoint) {
		this.inPlay = inPlay;
		blackPointValue = blackPoint;
		whitePointValue = whitePoint;
	}

	@Override
	protected Board clone() {
		ChessPiece[][] cloneArray = new ChessPiece[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				cloneArray[x][y] = this.inPlay[x][y];
			}
		}
		return new Board(cloneArray, this.whitePointValue, this.blackPointValue);
	}

	public int getWhitePointValue() {
		return whitePointValue;
	}

	public int getBlackPointValue() {
		return blackPointValue;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Board)) {
			return false;
		}
		Board compare = (Board)o;
		return Arrays.equals(this.inPlay, compare.inPlay) && this.whitePointValue == compare.whitePointValue && this.blackPointValue == compare.blackPointValue;
	}
}
