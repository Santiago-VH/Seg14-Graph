package model;

public class Edge<T> {
	private Vertex<T> start;
	private Vertex<T> end;
	
	public Edge(Vertex<T>start, Vertex<T> end) {
		this.start=start;
		this.end=end;
	}

	public Vertex<T> getStart() {
		return start;
	}

	public void setStart(Vertex<T> start) {
		this.start = start;
	}

	public Vertex<T> getEnd() {
		return end;
	}

	public void setEnd(Vertex<T> end) {
		this.end = end;
	}
}
