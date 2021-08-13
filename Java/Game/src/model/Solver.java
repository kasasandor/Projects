package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solver {

	private Graph<Tile> board;
	
	private RouteFinder<Tile> routeFinder;
	
	public void setUp(Board board) {
		Set<Tile> tiles = new HashSet<>();
		Map<Tile, Set<Tile>> connections = new HashMap<>();
		
		int size = board.getDifficulty();
		Tile[][] t = board.getTiles();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				tiles.add(t[i][j]);
			}
		}
		
		int di[] = {-1, 0, 0, 1};
		int dj[] = {0, -1, 1, 0};
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				Set<Tile> neighbours = new HashSet<>();
				for(int k = 0; k < 4; k++) {
					if(isInside(size, i + di[k], j + dj[k])) {
						neighbours.add(t[i + di[k]][j + dj[k]]);
					}
				}
				connections.put(t[i][j], neighbours);
			}
		}
		
		this.board = new Graph<>(tiles, connections);
		this.routeFinder = new RouteFinder<>(this.board, new ConcreteScorer(), new ConcreteScorer());
	}
	
	public boolean isInside(int size, int i, int j) {
		if(i >= 0 && i < size) {
			if(j >= 0 && j < size) {
				return true;
			}
		}
		return false;
	}
	public List<Tile> findRoute(Tile start, Tile goal) {
		List<Tile> route = routeFinder.findRoute(start, goal);
		return route;
	}
}
