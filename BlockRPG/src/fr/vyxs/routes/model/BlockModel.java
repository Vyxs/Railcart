package fr.vyxs.routes.model;

import fr.vyxs.routes.graphics.ImageBlockType;
import fr.vyxs.routes.math.Boolean4D;
import fr.vyxs.routes.math.SideAllowed;

public class BlockModel {
	
	protected final ImageBlockType image;
	protected final SideAllowed sideAllowed;
	
	public BlockModel(ImageBlockType image, SideAllowed sideAllowed) {
		this.image = image;
		this.sideAllowed = sideAllowed;
	}

	public ImageBlockType getBlockImage() {
		return image;
	}

	public Boolean4D getConnectionsAllowed() {
		return sideAllowed;
	}
	
	@Override
	public String toString() {
		return String.format("BlockType: %s SideAllowed: %s", image.name(), sideAllowed.toString());
	}
	
}
