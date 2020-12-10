package fr.vyxs.routes.selection;

import java.util.List;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.math.Integer2D;

public class ChunkSelection extends BlockSelection {

	public ChunkSelection(List<Block> initialBlocks, Integer2D sourcePosition) {
		super(initialBlocks, sourcePosition);
	}

}
