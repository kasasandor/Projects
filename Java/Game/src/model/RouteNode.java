package model;

import java.util.StringJoiner;

public class RouteNode<T extends GraphNode> implements Comparable<RouteNode<T>> {

	private T current;
	private T previous;
	private int routeScore;
	private int estimatedScore;
	
	RouteNode(T current){
		this(current, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	RouteNode(T current, T previous, int routeScore, int estimatedScore){
		this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
	}
	
	
	public T getPrevious() {
		return previous;
	}

	public void setPrevious(T previous) {
		this.previous = previous;
	}

	public int getRouteScore() {
		return routeScore;
	}

	public void setRouteScore(int routeScore) {
		this.routeScore = routeScore;
	}

	public int getEstimatedScore() {
		return estimatedScore;
	}

	public void setEstimatedScore(int estimatedScore) {
		this.estimatedScore = estimatedScore;
	}

	public int getHeuristic() {
		return this.current.getHeuristic();
	}
	public T getCurrent() {
		return current;
	}

	@Override
	public int compareTo(RouteNode<T> other) {
		if(this.estimatedScore > other.estimatedScore) {
			return 1;
		} else if(this.estimatedScore < other.estimatedScore) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", RouteNode.class.getSimpleName() + "[", "]").add("current=" + current)
	            .add("previous=" + previous).add("routeScore=" + routeScore).add("estimatedScore=" + estimatedScore)
	            .toString();
	}
}
