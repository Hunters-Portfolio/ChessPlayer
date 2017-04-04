package com.meta1203.ChessPlayer;

public class Move {
	private ChessPiece piece;
	private ChessPiece attack;
	private Coordinate newPosition;
	private int value;
	
	// Constructor
	public Move(ChessPiece piece, Coordinate newPosition, Board b) {
		this.piece = piece;
		this.newPosition = newPosition;
		attack = b.getPosition(newPosition);
		if (attack != null) {
			value = attack.getPointValue();
		}
	}
	
	// Getters
	
	/*
	 * Piece effected by the move
	 */
	public ChessPiece getPiece() {
		return piece;
	}
	/* 
	 * Where the piece will end up after the move
	 */
	public Coordinate getNewPosition() {
		return newPosition;
	}
	/*
	 * Return the piece that the move will take, null if there is no piece taken by this move
	 */
	public ChessPiece getAttack() {
		return attack;
	}
	/*
	 * Value of the taken piece, 0 if no piece is taken 
	 */
	public int getValue() {
		return value;
	}
	/*
	 * Return human readable information for logging purposes
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.piece.getPieceName() + " from " + this.piece.getCurrentLocation().toString() + " to " + this.newPosition.toString();
	}
}