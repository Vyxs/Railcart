package fr.vyxs.routes.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.vyxs.routes.block.BlockType;
import fr.vyxs.routes.exception.NoModelFound;
import fr.vyxs.routes.graphics.ImageBlockType;
import fr.vyxs.routes.math.SideAllowed;
import fr.vyxs.routes.model.BlockModel;

public final class BlockModelFactory {//@todo !remove this singleton
	
	private Map<BlockType, BlockModel> mapTypeModel;

	protected BlockModelFactory() { //@todo remove init(), refactor.
		mapTypeModel = new HashMap<>();
		this.init();
	}
	
	private void init() {
		mapTypeModel.put(BlockType.PLAYER, new BlockModel(ImageBlockType.PLAYER, new SideAllowed(0, 0, 0, 0)));
		mapTypeModel.put(BlockType.RAIL_ALL, new BlockModel(ImageBlockType.RAIL_ALL, new SideAllowed(1, 1, 1, 1)));
		mapTypeModel.put(BlockType.RAIL_BOTTOM_LEFT, new BlockModel(ImageBlockType.RAIL_BOTTOM_LEFT, new SideAllowed(0, 0, 1, 1)));
		mapTypeModel.put(BlockType.RAIL_BOTTOM_RIGHT, new BlockModel(ImageBlockType.RAIL_BOTTOM_RIGHT, new SideAllowed(0, 1, 1, 0)));
		mapTypeModel.put(BlockType.RAIL_HORIZONTAL, new BlockModel(ImageBlockType.RAIL_HORIZONTAL, new SideAllowed(0, 1, 0, 1)));
		mapTypeModel.put(BlockType.RAIL_RIGHT_BOT_LEFT, new BlockModel(ImageBlockType.RAIL_RIGHT_BOT_LEFT, new SideAllowed(0, 1, 1, 1)));
		mapTypeModel.put(BlockType.RAIL_RIGHT_TOP_LEFT, new BlockModel(ImageBlockType.RAIL_RIGHT_TOP_LEFT, new SideAllowed(1, 1, 0, 1)));
		mapTypeModel.put(BlockType.RAIL_TOP_LEFT, new BlockModel(ImageBlockType.RAIL_TOP_LEFT, new SideAllowed(1, 0, 0, 1)));
		mapTypeModel.put(BlockType.RAIL_TOP_LEFT_BOT, new BlockModel(ImageBlockType.RAIL_TOP_LEFT_BOT, new SideAllowed(1, 0, 1, 1)));
		mapTypeModel.put(BlockType.RAIL_TOP_RIGHT, new BlockModel(ImageBlockType.RAIL_TOP_RIGHT, new SideAllowed(1, 1, 0, 0)));
		mapTypeModel.put(BlockType.RAIL_TOP_RIGHT_BOT, new BlockModel(ImageBlockType.RAIL_TOP_RIGHT_BOT, new SideAllowed(1, 1, 1, 0)));
		mapTypeModel.put(BlockType.RAIL_UNLINKED, new BlockModel(ImageBlockType.RAIL_UNLINKED, new SideAllowed(0, 0, 0, 0)));
		mapTypeModel.put(BlockType.RAIL_VERTICAL, new BlockModel(ImageBlockType.RAIL_VERTICAL, new SideAllowed(1, 0, 1, 0)));
	}

	protected static final class Holder {
		private static final BlockModelFactory INSTANCE = new BlockModelFactory();
	}
	
	public static final BlockModelFactory get() {
		return Holder.INSTANCE;
	}
	
	public final BlockModel create(BlockType type) throws NoModelFound {
		if (!mapTypeModel.containsKey(type))
			throw new NoModelFound();
		return mapTypeModel.get(type);
	}
	
	public final BlockModel createRail(SideAllowed sideAllowed) throws NoModelFound {
		if (sideAllowed.sumOfTrue() == 1) {
			if (sideAllowed.isBottomAllowed() || sideAllowed.isTopAllowed())
				return create(BlockType.RAIL_VERTICAL);
			if (sideAllowed.isRightAllowed() || sideAllowed.isLeftAllowed())
				return create(BlockType.RAIL_HORIZONTAL);
		}
		for (Entry<BlockType, BlockModel> entry : mapTypeModel.entrySet()) {
			if (entryKeyIsNotPlayer(entry)) {
				if (entry.getValue().getConnectionsAllowed().equals(sideAllowed)) {
					return entry.getValue();
				}
			}
		}
		return create(BlockType.RAIL_UNLINKED);
	}
	
	public final boolean entryKeyIsNotPlayer(final Entry<BlockType, BlockModel> entry) {
		if (!entry.getKey().equals(BlockType.PLAYER)) 
			return true;
		return false;
	}
}
