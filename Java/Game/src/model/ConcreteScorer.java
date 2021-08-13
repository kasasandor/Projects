package model;

public class ConcreteScorer implements Scorer<Tile>{

	@Override
	public int computeCost(Tile from, Tile to) {
		return (int)Math.sqrt(Math.pow(from.getX() - to.getX(), 2) 
				+ Math.pow(from.getY() - to.getY(), 2));
	}
}
