package com.meta1203.ChessPlayer;

public class Move {
	private ChessPiece piece;
	private ChessPiece attack;
	private Coordinate newPosition;
	private int value;
	
	public Move(ChessPiece piece, Coordinate newPosition) {
		this.piece = piece;
		this.newPosition = newPosition;
		attack = Main.board.getPosition(newPosition);
		if (attack != null) {
			value = attack.getPointValue();
		}
	}
	
	// Getters
	public ChessPiece getPiece() {
		return piece;
	}
	public Coordinate getNewPosition() {
		return newPosition;
	}
	public ChessPiece getAttack() {
		return attack;
	}
	public int getValue() {
		return value;
	}
}