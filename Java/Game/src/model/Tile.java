package model;

import java.awt.*; 
import java.awt.image.ImageObserver;

public class Tile implements GraphNode{

	private int x;
	private int y;
	private Element isEmpty;
	private Color color = new Color(255,255,255);
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		isEmpty = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color col) {
		this.color = col;
	}
	
	public void placeElement(Element e) {
		this.isEmpty = e;
	}
	
	public boolean isEmpty() {
		if(this.isEmpty == null) {
			return true;
		}
		
		return false;
	}
	
	public Element getElement() {
		return this.isEmpty;
	}
	
	public int getHeuristic() {
		if(this.isEmpty == null) {
			return 0;
		}
		else {
			return this.isEmpty.getHeuristic();
		}
	}
	
	public void Draw(Graphics g, int size, ImageObserver obs) {
		g.setColor(color);
		g.fillRect(x * size + 2, y * size + 2, size - 3, size - 3);
		
		if(!isEmpty()) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(isEmpty.getImage(), isEmpty.getX() * size + 2, isEmpty.getY() * size + 2, obs);
		}
	}

	@Override
	public boolean equals(Tile o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Tile)) {
			return false;
		}
		
		Tile t = (Tile) o;
		return (t.getX() == this.x && t.getY() == this.y);
	}
}
