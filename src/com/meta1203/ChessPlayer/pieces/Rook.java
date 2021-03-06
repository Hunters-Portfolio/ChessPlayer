package com.meta1203.ChessPlayer.pieces;

import java.util.List;

import com.meta1203.ChessPlayer.ChessPiece;
import com.meta1203.ChessPlayer.Coordinate;
import com.meta1203.ChessPlayer.Move;
import com.meta1203.ChessPlayer.Turn;

public class Rook implements ChessPiece {

	private Coordinate coord = null;
	private boolean white = false;
	private final String name = "Rook";
	
	public Rook(byte x, byte y, boolean white) {
		this.coord = new Coordinate(x, y);
		this.white = white;
	}
	
	@Override
	public Coordinate getCurrentLocation() {
		return this.coord;
	}

	@Override
	public List<Move> getValidMoves(Turn t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPointValue() {
		return 5;
	}

	@Override
	public String getPieceName() {
		return this.name;
	}

	@Override
	public boolean isWhite() {
		return white;
	}
}
