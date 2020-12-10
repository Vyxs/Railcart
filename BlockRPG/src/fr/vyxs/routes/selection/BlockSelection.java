package fr.vyxs.routes.selection;

import java.util.List;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public abstract class BlockSelection {

	protected final Integer2D sourcePosition;
	protected List<Block> initialBlockList;
	
	protected BlockSelection(final List<Block> initialBlocks, final Integer2D sourcePosition) {
		this.initialBlockList = initialBlocks;
		this.sourcePosition = sourcePosition;
	}
	
	/**
	 * 
	 * Finds the block whose position corresponds to the position 
	 * passed as an argument. Returns the first occurrence or null
	 * if no block is found.
	 * 
	 * @param list of blocks where to find the requested position.
	 * @param p position requested
	 * @return block or null if no block were found at this position.
	 */
	protected static final BlockModel getBlockAtPosition(List<Block> blocks, Integer2D p) {
		for (Block b : blocks) {
			if (b.getPosition().equals(p))
				return b.getModel();
		}
		return null;
	}
}
