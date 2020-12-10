package fr.vyxs.routes.selection;

import java.util.List;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public class DotSelection extends BlockSelection {

	public DotSelection(final List<Block> initialBlocks, final Integer2D sourcePosition) {
		super(initialBlocks, sourcePosition);
	}
	
	public BlockModel getBlockModel() {
		for (Block b : initialBlockList)
			if (b.getPosition().equals(sourcePosition))
				return b.getModel();
		return null;
	}
}
