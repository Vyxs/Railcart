package fr.vyxs.routes.math;

import fr.vyxs.routes.misc.Constants;

public class MapPositionTransformer {

	private MapPositionTransformer() {
		throw new UnsupportedOperationException();
	}

	public static Integer2D toMapCoord(Vector2D<Float> position) {
		final int x = (int) Math.ceil(position.getFirst() / Constants.REAL_BLOCK_SIZE.getFirst());
		final int y = (int) Math.ceil(position.getSecond() / Constants.REAL_BLOCK_SIZE.getSecond());
		return new Integer2D(x - 1, y - 1);
	}
	
	public static Float2D toGraphicCoord(Vector2D<Integer> position) {
		final int x = (int) Math.ceil(position.getFirst() * Constants.REAL_BLOCK_SIZE.getFirst());
		final int y = (int) Math.ceil(position.getSecond() * Constants.REAL_BLOCK_SIZE.getSecond());
		return new Float2D(x, y);
	}
}
