package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Treasure extends Element{
	
	public Treasure(String filename, int x, int y, int size, int heuristic) {
		super();
		this.xPos = x;//(size + 3) * x + 2;
		this.yPos = y;//(size + 3) * y + 2;
		this.heuristic = heuristic;
		loadImage(filename, size);
	}
	
	private void loadImage(String filename, int size) {
		ImageIcon ii = new ImageIcon(filename);
		Image newimage = ii.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
		image = new ImageIcon(newimage).getImage();
	}
}
