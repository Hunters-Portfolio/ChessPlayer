package com.meta1203.ChessPlayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * Lovingly yanked from https://github.com/gt4dev/yet-another-tree-structure
 */

public class Turn implements Comparable <Turn>, Comparator<Turn> {

	private Board data;
	private Turn parent;
	private List<Turn> children;

	public Turn(Board data) {
		this.data = data;
		this.children = new LinkedList<Turn>();
	}

	public Turn addChild(Board child) {
		Turn childNode = new Turn(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}
	
	public void sort() {
		if (children.isEmpty()) return;
		Collections.sort(children);
		for (Turn x : children) {
			x.sort();
		}
	}
	
	// Getters and setters
	public Board getData() {
		return data;
	}
	public void setData(Board data) {
		this.data = data;
	}
	public Turn getParent() {
		return parent;
	}
	public void setParent(Turn parent) {
		this.parent = parent;
	}
	public List<Turn> getChildren() {
		return children;
	}
	public void setChildren(List<Turn> children) {
		this.children = children;
	}

	@Override
	public int compare(Turn one, Turn another) {
		return one.data.compareTo(another.data);
	}

	@Override
	public int compareTo(Turn other) {
		return this.data.compareTo(other.data);
	}
}