package fr.vyxs.routes.math;

public interface Transformer<T extends Number, S extends Number> {
	public Vector2D<T> toMapCoord(Vector2D<S> object);
}
