package com.meta1203.ChessPlayer;

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
	private int value;
	
	// Create a root turn
	public Turn(Board data) {
		this.board = data;
		this.children = new LinkedList<Turn>();
		this.parent = null;
		this.whiteTurn = false;
		this.value = 0;
	}
	
	// Constructor for child turns
	private Turn(Board data, boolean white) {
		this.board = data;
		this.children = new LinkedList<Turn>();
		this.value = data.getBlackPointValue() - data.getWhitePointValue();
	}
	
	// Create a child turn
	public Turn addChild(Board child) {
		Turn childNode = new Turn(child, !this.whiteTurn);
		childNode.parent = this;
		this.children.add(childNode);
		// Update value of the Turn when a child is added
		this.value = calculateValue();
		return childNode;
	}
	
	// Get best available computer move from the children
	public Turn bestMove() {
		Turn best = children.get(0);
		for (Turn x : children) {
			if (x.getValue() > best.getValue()) {
				best = x;
			}
		}
		return best;
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
	public int getValue() {
		return value;
	}
	
	// Get max depth of children
	public int getDepth() {
		int depth = 0;
		Turn t = this;
		boolean containsChild = !t.children.isEmpty();
		
		while (containsChild) {
			t = t.children.get(0);
			depth += 1;
			containsChild = !t.children.isEmpty();
		}
		
		return depth;
	}
	
	// Assign a hard point value to turns based on the black score of the turn and all of its children
	private int calculateValue() {
		int ret = this.board.getBlackPointValue() - this.board.getWhitePointValue();
		// Point values should decrease exponentially by turn
		for (Turn x : this.getChildren()) {
			ret += x.calculateValue()/2;
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