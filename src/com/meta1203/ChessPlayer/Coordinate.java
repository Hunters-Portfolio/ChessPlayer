package com.meta1203.ChessPlayer;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate translate(int x, int y, boolean white) {
		// Black moves in a different y direction to white
		if (!white) {
			x = 0-x;
			y = 0-y;
		}
		// New coordinates
		int newX = this.x + x;
		int newY = this.y + y;
		// Check to see if the move is out of bounds, set null if so
		if (newX < 0 || newX > 7 || newY < 0 || newY > 0) {
			return null;
		}
		// Return the translated coordinate
		return new Coordinate(newX, newY);
	}
	
	// Getters/Setters... pretty self-explanitory
	// May not need setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	// Check if this Coordinate occupies the same space as another Coordinate
	public boolean compare(Coordinate other) {
		return this.x == other.getX() && this.y == other.getY();
	}
	
	// Translate a human-readable location (G4) to a machine recognizable location (6,3)
	public static Coordinate letterToCoord(String x, int y) {
		x = x.toLowerCase();
		int xIntern = 0;
		switch (x) {
		case "a":
			xIntern = 0;
			break;
		case "b":
			xIntern = 1;
			break;
		case "c":
			xIntern = 2;
			break;
		case "d":
			xIntern = 3;
			break;
		case "e":
			xIntern = 4;
			break;
		case "f":
			xIntern = 5;
			break;
		case "g":
			xIntern = 6;
			break;
		case "h":
			xIntern = 7;
			break;
		default:
			throw new IllegalArgumentException();
		}
		return new Coordinate(xIntern, y-1);
	}
}
