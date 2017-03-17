package com.meta1203.ChessPlayer.Pieces;

import java.util.List;

import com.meta1203.ChessPlayer.ChessPiece;
import com.meta1203.ChessPlayer.Coordinate;
import com.meta1203.ChessPlayer.Move;


public class Pawn implements ChessPiece {
	private Coordinate coord = null;
	private boolean white = false;
	private final String name = "Pawn";
	
	public Pawn(byte x, byte y, boolean white) {
		this.coord = new Coordinate(x, y);
		this.white = white;
	}
	
	@Override
	public Coordinate getCurrentLocation() {
		return this.coord;
	}

	@Override
	public List<Move> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPointValue() {
		return 1;
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
