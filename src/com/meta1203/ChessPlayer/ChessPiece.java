package com.meta1203.ChessPlayer;

import java.util.List;

public interface ChessPiece {
	public Coordinate getCurrentLocation();
	public List<Move> getValidMoves(Turn t);
	public int getPointValue();
	public String getPieceName();
	public boolean isWhite();
}