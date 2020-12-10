package fr.vyxs.routes.math;

public abstract class Vector2D<T> {
	
	protected T first;
	protected T second;
	
	public Vector2D(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public void set(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return this.first;
	}
	
	public T getSecond() {
		return this.second;
	}
	
	public void setFirst(T value) {
		this.first = value;
	}
	
	public void setSecond(T value) {
		this.second = value;
	}
	
	public abstract Vector2D<T> getDifference(Vector2D<T> source);
	
	@Override
	public String toString() {
		return String.format("{%s,%s}", first, second);
	}
}
