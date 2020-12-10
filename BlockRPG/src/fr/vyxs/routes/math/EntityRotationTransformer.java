package fr.vyxs.routes.math;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.core.Constant;

public class EntityRotationTransformer {
	
	private EntityRotationTransformer() {
		throw new UnsupportedOperationException();
	}

	/**	
	 	0 - 360
		45
		90
		135
		180
		225
		270<Block>
		315
		360 - 0
	 */
	
	public static float reduce(final float norme, final float degree) {
		if (degree <= norme)
			return degree < norme / 2 ? 0f : norme;
		
		float rest = degree / norme;
		float count = Math.round(rest);
		
		float rsl = count * norme;
		
		return rsl;
	}
	
	public static float normalize(float degree) {
		return reduce(Constant.CART_ORIENTATION_DEGREE, degree);
	}
}
