package com.meta1203.ChessPlayer;

public class Coordinate {
	private byte x;
	private byte y;
	
	public Coordinate(byte x, byte y) {
		this.x = x;
		this.y = y;
	}
	
	public byte getX() {
		return x;
	}
	public void setX(byte x) {
		this.x = x;
	}
	public byte getY() {
		return y;
	}
	public void setY(byte y) {
		this.y = y;
	}
	
	public boolean compare(Coordinate other) {
		return this.x == other.getX() && this.y == other.getY();
	}
	
	public static Coordinate letterToCoord(String x, byte y) {
		x = x.toLowerCase();
		byte xIntern = 0;
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
		return new Coordinate(xIntern, (byte) (y-1));
	}
}
