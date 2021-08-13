package model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {

	private  Set<T> nodes;
	private  Map<Tile, Set<Tile>> connections;
	
	public Graph(Set<T> nodes, Map<Tile, Set<Tile>> connections) {
		this.nodes = nodes;
		this.connections = connections;
	}
	
	public T getNode(Tile tile) {
		return nodes.stream()
				.filter(node -> node.equals(tile))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No node found with ID"));
	}
	
	public Set<T> getConnections(T node){
		return connections.get(node).stream()
				.map(this::getNode)
				.collect(Collectors.toSet());
	}
}
