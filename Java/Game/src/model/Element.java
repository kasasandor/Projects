package model;

import java.awt.Image;

public class Element {

	protected int xPos;
	protected int yPos;
	protected Image image;
	protected int heuristic;
	
	public Element() {}

	public int getX() {
		return this.xPos;
	}
	
	public int getY() {
		return this.yPos;
	}
	
	public Image getImage() {
		return this.image;
	}

	public void setX(int x) {
		this.xPos = x;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
}
