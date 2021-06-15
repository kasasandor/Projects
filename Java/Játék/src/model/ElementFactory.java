package model;

public class ElementFactory {

	public static Element getElement(String type, String filename, int x, int y, int size, int heuristic) {
		
		if(type.equalsIgnoreCase("OBSTACLE")) {
			return new Obstacle(filename, x, y, size, heuristic);
		}
		
		if(type.equalsIgnoreCase("Treasure")) {
			return new Treasure(filename, x, y, size, heuristic);
		}
		
		if(type.equalsIgnoreCase("ANIMAL")) {
			return new Animal(filename, x, y, size);
		}
		
		return null;
	}
}
