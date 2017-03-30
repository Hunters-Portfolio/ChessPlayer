package com.meta1203.ChessPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private Map<Coordinate,ChessPiece> inPlay;
	private List<ChessPiece> outOfPlay;
	
	public Board() {
		inPlay = new HashMap<Coordinate,ChessPiece>();
		outOfPlay = new ArrayList<ChessPiece>();
	}
	
	public void populate() {
		// TODO: add the pieces to the board
	}
	
	public ChessPiece getPosition(Coordinate c) {
		return inPlay.get(c);
	}
}
