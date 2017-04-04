package com.meta1203.ChessPlayer.Pieces;

import java.util.ArrayList;
import java.util.List;

import com.meta1203.ChessPlayer.ChessPiece;
import com.meta1203.ChessPlayer.Coordinate;
import com.meta1203.ChessPlayer.Move;
import com.meta1203.ChessPlayer.Turn;


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
	public List<Move> getValidMoves(Turn t) {
		List<Move> ret = new ArrayList<Move>();
		// Unrolling loops is fun!
		Move add = null;
		Coordinate test = null;
		// Move 1: Can I go 1 up?
		test = coord.translate(0, 1, white);
		if (coord != null){
			add = new Move(this, test, t.getBoard());
			// Pawns can only advance if there is nothing in their path
			if (add.getAttack() == null) {
				ret.add(add);
			}
		}
		// Move 2: double-forward on the pawn's first move
		if ((white && coord.getY() == 1) || (!white && coord.getY() == 6)) {
			test = coord.translate(0, 2, white);
			// Don't check for a null move because you can't have one!
			if (add.getAttack() == null) {
				ret.add(add);
			}
		}
		// Move 3: attack to the left
		test = coord.translate(-1, 1, white);
		if (coord != null){
			add = new Move(this, test, t.getBoard());
			// Pawns can only advance diagonally if they kill something in the process
			if (add.getAttack() != null) {
				ret.add(add);
			}
		}
		// Move 4: attack to the right
		test = coord.translate(1, 1, white);
		if (coord != null){
			add = new Move(this, test, t.getBoard());
			// Pawns can only advance diagonally if they kill something in the process
			if (add.getAttack() != null) {
				ret.add(add);
			}
		}
		return ret;
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
