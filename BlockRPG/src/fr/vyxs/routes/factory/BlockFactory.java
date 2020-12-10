package fr.vyxs.routes.factory;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.graphics.BlockImageList;
import fr.vyxs.routes.graphics.ImageBlockType;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public final class BlockFactory {

	private final BlockImageList blockImageList;
	private final Integer2D blockSize;
	
	public BlockFactory(final BlockImageList blockImageList, Integer2D blockSize) {
		this.blockImageList = blockImageList;
		this.blockSize = blockSize;
	}
	
	public final Block create(BlockModel model, ImageBlockType type, Integer2D position) {
		return new Block(model, blockImageList.get(type), blockSize, position);
	}

}
