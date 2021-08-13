package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.List;
import java.util.Random;

public class Board {

	private Tile[][] tiles;
	private int size;
	private int difficulty;
	private Element player;
	private Element goal;
	
	public Board(int difficulty, int width) {
		this.size = (width - 2)/difficulty;
		this.tiles = new Tile[difficulty][difficulty];
		this.difficulty = difficulty;
		
		for(int i = 0; i < difficulty; i++) {
			for(int j = 0; j < difficulty; j++) {
				tiles[i][j] = new Tile(i, j);
			}
		}
		
		placeElements(difficulty);
	}
	
	public Element getPlayer() {
		return this.player;
	}
	
	public Element getGoal() {
		return this.goal;
	}
	
	public Tile[][] getTiles(){
		return this.tiles;
	}
	
	public Tile getPlayerTile() {
		return tiles[player.getX()][player.getY()];
	}
	
	public Tile getGoalTile() {
		return tiles[goal.getX()][goal.getY()];
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public boolean modifyPos(Element e, int newX, int newY) {
		int x = e.getX();
		int y = e.getY();	
		
		if(newX == goal.getX() && newY == goal.getY()) {
			player.setX(newX);
			player.setY(newY);
			tiles[newX][newY].placeElement(player);
			return true;
		}
		if(tiles[newX][newY].isEmpty()) {
			e.setX(newX);
			e.setY(newY);
			tiles[newX][newY].placeElement(e);
			tiles[x][y].placeElement(null);
		}
		else {
			e.setX(e.getX());
			e.setY(e.getY());
		}
		return false;
	}
	
	public boolean placeElement(Element e) {
		int x = e.getX();
		int y = e.getY();
		
		if(tiles[x][y].isEmpty()) {
			tiles[x][y].placeElement(e);
			return true;
		}
		return false;
	}
	
	public void Draw(Graphics g, ImageObserver obs) {
		for(int i = 0; i < difficulty; i++) {
			for(int j = 0; j < difficulty; j++) {
				tiles[i][j].Draw(g, size, obs);
			}
		}
	}
	
	public void placeElements(int diff) {
		int traps = 0;
		int minDistance = 0;
		
		if(diff == 5) {
			traps = 6;
			minDistance = 3;
		}
		
		if(diff == 7) {
			traps = 15;
			minDistance = 5;
		}
		
		if(diff == 10) {
			traps = 25;
			minDistance = 7;
		}
		
		Random r = new Random();
		
		int playerX = r.nextInt(diff);
		int playerY = r.nextInt(diff);
		player = ElementFactory.getElement("Animal", "Images/bunny.png", playerX, playerY, size - 3, 0);
		placeElement(player);
		
		while(true) {
			int randPosX = r.nextInt(diff);
			int randPosY = r.nextInt(diff);
			
			if(manhattanDistance(randPosX, randPosY, playerX, playerY) > minDistance) {
				goal = ElementFactory.getElement("Treasure", "Images/carrot.jfif", randPosX, randPosY, size-3, 0);
				placeElement(goal);
				break;
			}
		}
		
		while(traps != 0) {
			int trapX = r.nextInt(diff);
			int trapY = r.nextInt(diff);
			
			Element ex = ElementFactory.getElement("Obstacle", "Images/trap.png", trapX, trapY, size-3, 999);
			if(placeElement(ex)) {
				traps--;
			}
		}
	}
	
	public void colorRoute(List<Tile> route) {
		for(Tile t : route) {
			t.setColor(Color.red);
		}
	}
	private int manhattanDistance(int x1, int y1, int x2, int y2) {
		return (int)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
