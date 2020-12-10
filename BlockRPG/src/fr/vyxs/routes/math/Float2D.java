package fr.vyxs.routes.math;

public class Float2D extends Vector2D<Float>{

	public Float2D() {
		super(0f, 0f);
	}
	
	public Float2D(Number first, Number second) {
		super(first.floatValue(), second.floatValue());
	}

	public Integer2D toInt() {
		return new Integer2D(first.intValue(), second.intValue());
	}

	@Override
	public Vector2D<Float> getDifference(Vector2D<Float> source) {
		final float dx = source.getFirst() > first ? source.getFirst() - first : first - source.getFirst();
		final float dy = source.getSecond() > second ? source.getSecond() - second : second - source.getSecond();
		return new Float2D(dx, dy);
	}

}
