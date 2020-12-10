package fr.vyxs.routes.math;

public abstract class Vector4D<T> {
		
	protected T w;
	protected T x;
	protected T y;
	protected T z;
	
	public Vector4D(T x, T y, T z, T w) {
		set(x, y, z, w);
	}
	
	public void set(T x, T y, T z, T w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public T getW() {
		return w;
	}

	public void setW(T w) {
		this.w = w;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}

	public T getZ() {
		return z;
	}

	public void setZ(T z) {
		this.z = z;
	}
	
	@Override
	public String toString() {
		return String.format("(top) x: %s (right) y: %s (bot) z: %s (left) w: %s", x, y, z, w);
	}
	
	public String toMini() {
		return String.format("t:%s-r:%s-b:%s-l:%s\n", x, y, z, w);
	}
	
	public String toFormattedString() {
		return String.format("top: %s\nright: %s\nbot: %s\nleft: %s\n", x, y, z, w);
	}
}

