package com.meta1203.ChessPlayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * A turn that is sorted into a tree to make calculating moves easier
 * The status of the board in the Turn object is AFTER the turn is made by the corresponding player
 * 
 * Lovingly yanked from https://github.com/gt4dev/yet-another-tree-structure
 */

public class Turn implements Comparable <Turn>, Comparator<Turn> {

	private Board board;
	private Turn parent;
	private boolean whiteTurn;
	private List<Turn> children;
	private int depth;
	
	// Create a root turn
	public Turn(Board data) {
		this.board = data;
		this.children = new LinkedList<Turn>();
		this.depth = 1;
		this.parent = null;
		this.whiteTurn = false;
	}
	
	// Constructor for child turns
	private Turn(Board data, int depth, boolean white) {
		this.board = data;
		this.children = new LinkedList<Turn>();
		this.depth = depth;
	}
	
	// Create a child turn
	public Turn addChild(Board child) {
		Turn childNode = new Turn(child, this.depth + 1, !this.whiteTurn);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}
	
	// Sorting mechanism, will probably be replaced with a largest function instead
	public void sort() {
		if (children.isEmpty()) return;
		Collections.sort(children);
		for (Turn x : children) {
			x.sort();
		}
	}
	
	// Getters
	public Board getBoard() {
		return board;
	}
	public Turn getParent() {
		return parent;
	}
	public List<Turn> getChildren() {
		return children;
	}
	public boolean isWhiteTurn() {
		return whiteTurn;
	}
	
	// Assign a hard point value to turns based on the black score of the turn and all of its children
	public int getValue() {
		int ret = this.board.getBlackPointValue() - this.board.getWhitePointValue();
		// Point values should decrease exponentially by turn
		for (Turn x : this.getChildren()) {
			ret += x.getValue()/2;
		}
		return ret;
	}
	
	// Comparison
	@Override
	public int compare(Turn one, Turn another) {
		// Weigh both points and # of turns made
		return one.getValue() - another.getValue();
	}

	@Override
	public int compareTo(Turn other) {
		return this.compare(this, other);
	}
}