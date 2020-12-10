package fr.vyxs.routes.graphics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.vyxs.routes.math.Integer2D;

public class BlockImageList {
	
	private Map<ImageBlockType, Image> blockImageList;
	public static final String TEXTURE_PATH = "/res/block/blocks.png";
	private final int blockSize;
	
	public BlockImageList(int blockSize) {
		this.blockImageList = new HashMap<>();
		this.blockSize = blockSize;
	}
	
	public void load() throws SlickException {
		blockImageList.put(ImageBlockType.RAIL_ALL, getImageAt(new Integer2D(0, 0)));
		blockImageList.put(ImageBlockType.RAIL_VERTICAL, getImageAt(new Integer2D(0, 16)));
		blockImageList.put(ImageBlockType.RAIL_HORIZONTAL, getImageAt(new Integer2D(8, 16)));
		blockImageList.put(ImageBlockType.RAIL_TOP_LEFT, getImageAt(new Integer2D(0, 8)));
		blockImageList.put(ImageBlockType.RAIL_TOP_RIGHT, getImageAt(new Integer2D(8, 8)));
		blockImageList.put(ImageBlockType.RAIL_BOTTOM_LEFT, getImageAt(new Integer2D(16, 8)));
		blockImageList.put(ImageBlockType.RAIL_BOTTOM_RIGHT, getImageAt(new Integer2D(24, 8)));
		blockImageList.put(ImageBlockType.RAIL_TOP_RIGHT_BOT, getImageAt(new Integer2D(0, 24)));
		blockImageList.put(ImageBlockType.RAIL_RIGHT_BOT_LEFT, getImageAt(new Integer2D(8, 24)));
		blockImageList.put(ImageBlockType.RAIL_TOP_LEFT_BOT, getImageAt(new Integer2D(16, 24)));
		blockImageList.put(ImageBlockType.RAIL_RIGHT_TOP_LEFT, getImageAt(new Integer2D(24, 24)));
		blockImageList.put(ImageBlockType.RAIL_UNLINKED, getImageAt(new Integer2D(0, 32)));
		blockImageList.put(ImageBlockType.PLAYER, getImageAt(new Integer2D(0, 40)));
	}

	private Image getImageAt(Integer2D position) throws SlickException {
		Image img = new Image(TEXTURE_PATH, false, Image.FILTER_NEAREST).getSubImage(position.getFirst(), position.getSecond(), 8, 8).getScaledCopy(blockSize, blockSize);
		return img;
	}
	
	public Image get(ImageBlockType image) {
		return blockImageList.get(image);
	}

	public Collection<? extends Image> getAll() {
		return blockImageList.values();
	}
}
