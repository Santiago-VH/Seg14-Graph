package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph <T extends Comparable<T>> { 
	private List<Vertex<T>> vertices;
	private List<Edge<T>> edges;

	public Graph() {
		this.vertices=new ArrayList<>();
		this.edges=new ArrayList<>();
	}

	public void addVertex(Vertex<T> vertex) {
		vertices.add(vertex);
	}
	
	public void addEdge(Vertex<T> start, Vertex<T> end) {
		
		Edge<T>edge=new Edge<>(start,end);
		edges.add(edge);
		start.addAdjacent(end);
		//end.addAdjacent(start); // documentar si se quiere un grafo dirigido.
	}
	
	public void bfsSearch(Graph<T> grafo, Vertex<T> start) {
		Vertex<T> vertex;
		for(int i=0; i<vertices.size();i++) {
			vertex = vertices.get(i);
			if(vertex.getValue().compareTo(start.getValue())!=0) {
				vertex.setColor("WHITE");
				vertex.setDistance(Integer.MAX_VALUE);
				vertex.setFather(null);
			} 
		}
		start.setColor("GRAY");
		start.setDistance(0);
		start.setFather(null);
		Queue<Vertex<T>> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			vertex=queue.poll();
			for(int i=0; i<vertex.getAdjacentList().size();i++) {
				Vertex<T>adjacent=vertices.get(i);
				if(adjacent.getColor().equals("WHITE")) {
					adjacent.setColor("GRAY");
					adjacent.setDistance(vertex.getDistance()+1);
					adjacent.setFather(vertex);
					queue.offer(vertex);
				}
			}
			vertex.setColor("BLACK");
		}
		
	}
	
	public List<Vertex<T>> dfs(Graph<T> grafo) {
		Vertex<T> vertex=null;
		List<Vertex<T>> dfsOrder = new ArrayList<>();
		for(int i=0; i<vertices.size();i++) {
			vertex=vertices.get(i);
			vertex.setColor("WHITE");
			vertex.setFather(null);
		}
		
		int time = 0;
		
		for(int i=0; i<vertices.size();i++) {
			if(vertex.getColor().equals("WHITE")) {
				dfsVisit(grafo, vertex, time, dfsOrder);
			}
		}
		
		return dfsOrder;
	}
	
	public void dfsVisit(Graph<T> grafo, Vertex<T> vertex, int time, List<Vertex<T>> dfsOrder){
		dfsOrder.add(vertex);
		time++;
		vertex.setDistance(time); //En este caso, distancia se usa para determinar el tiempo inicial de visita del nodo.
		vertex.setColor("GRAY");
		for(int i=0; i<vertex.getAdjacentList().size();i++) {
			Vertex<T>adjacent=vertices.get(i);
			if(adjacent.getColor().equals("WHITE")) {
				adjacent.setFather(vertex);
				dfsVisit(grafo,adjacent,time,dfsOrder);
			}
		}
		vertex.setColor("BLACK");
		time++;
		vertex.setFinalVisitTime(time);
		
	}
	
	public boolean isStronglyConnected(Graph<T> graph) {
		
		dfs(graph);
		
		for (int i=0; i<vertices.size();i++) {
			Vertex<T>vertex=vertices.get(i);
			if(vertex.getColor()=="WHITE") {
				return false;
			}
		}
		
		Graph<T>transposedGraph=getTransposedGraph();
		
		for(int i=0; i<vertices.size();i++) {
			Vertex<T>vertex = vertices.get(i);
			vertex.setColor("WHITE");
			vertex.setDistance(Integer.MAX_VALUE);
			vertex.setFather(null);
		}
		transposedGraph.dfs(transposedGraph);
		
		for(int i = 0; i < vertices.size(); i++) {
		    Vertex<T> vertex = vertices.get(i);
		    if(vertex.getColor().equals("WHITE")) {
		    	return false;
		    }
		}
		
		return true;
	}
	
	public Graph<T> getTransposedGraph(){ //definimos grafo transpuesto como aquel grafo donde se han invertido todas las aristas del grafo original.
		
		Graph<T> transposedGraph=new Graph<>();
		
		for(int i = 0; i < edges.size(); i++) {
		    Edge<T> edge = edges.get(i);
		    transposedGraph.addEdge(edge.getEnd(), edge.getStart());
		}
		
		return transposedGraph;
	}
	
	public Vertex<T> findVertexByValue(T value) {
		for(int i = 0; i < vertices.size(); i++) {
		    Vertex<T> vertex = vertices.get(i);
        	
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }
	
	
	public void printGraph() {
		
		for(int i = 0; i < vertices.size();i++) {
			
			Vertex<T> vertex=vertices.get(i);
			System.out.print("Adjacent to "+vertex.getValue()+": ");
			
			for(int j=0; j<vertex.getAdjacentList().size();j++) {
				
				Vertex<T> adjacent=vertex.getAdjacentList().get(j);
				System.out.print(adjacent.getValue()+" ");
				
			}
			
			System.out.println();
			System.out.println();
			
		}
		
	}
}
