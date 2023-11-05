package model;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
	private T value;
	private List<Vertex<T>> adjacentList;
	private String color;
	private int distance, finalVisitTime;
	private Vertex<T> father;
	
	public Vertex(T value) {
		this.value=value;
		this.adjacentList= new ArrayList<>();
		this.color="";
		this.distance=-1;
		this.finalVisitTime=Integer.MAX_VALUE;
		this.father=null;
	}

	public void addAdjacent(Vertex<T> vertex) {
		adjacentList.add(vertex);
	}
	
	public T getValue() {
		return value;
	}

	public List<Vertex<T>> getAdjacentList() {
		return adjacentList;
	}

	public void setAdjacentList(List<Vertex<T>> adjacentList) {
		this.adjacentList = adjacentList;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex<T> getFather() {
		return father;
	}

	public void setFather(Vertex<T> father) {
		this.father = father;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getFinalVisitTime() {
		return finalVisitTime;
	}

	public void setFinalVisitTime(int finalVisitTime) {
		this.finalVisitTime = finalVisitTime;
	}
	
}
