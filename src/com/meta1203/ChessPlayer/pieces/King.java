package com.meta1203.ChessPlayer.pieces;

import java.util.List;

import com.meta1203.ChessPlayer.ChessPiece;
import com.meta1203.ChessPlayer.Coordinate;
import com.meta1203.ChessPlayer.Move;
import com.meta1203.ChessPlayer.Turn;

public class King implements ChessPiece {
	private Coordinate coord = null;
	private boolean white = false;
	private final String name = "Pawn";
	
	public King(byte x, byte y, boolean white) {
		this.coord = new Coordinate(x, y);
		this.white = white;
	}
	
	@Override
	public Coordinate getCurrentLocation() {
		return this.coord;
	}
	
	@Override
	public List<Move> getValidMoves(Turn t) {
		// TODO Deal with check and checkmate
		return null;
	}

	@Override
	public int getPointValue() {
		// TODO Subject to change as the King object probably won't deal with point values at all
		return 10000;
	}

	@Override
	public String getPieceName() {
		return this.name;
	}

	@Override
	public boolean isWhite() {
		return this.white;
	}

}
