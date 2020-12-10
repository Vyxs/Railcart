package fr.vyxs.routes.graphics;

public enum ImageBlockType {
	PLAYER (0),
	RAIL_ALL (1),
	RAIL_HORIZONTAL (2),
	RAIL_VERTICAL (3),
	RAIL_TOP_LEFT (4),
	RAIL_TOP_RIGHT (5),
	RAIL_BOTTOM_LEFT (6),
	RAIL_BOTTOM_RIGHT (7),
	RAIL_TOP_RIGHT_BOT (8),
	RAIL_TOP_LEFT_BOT (9),
	RAIL_RIGHT_BOT_LEFT(10),
	RAIL_RIGHT_TOP_LEFT (11),
	RAIL_UNLINKED (12);
	
	private final int id;
	
	ImageBlockType(int id) {
		this.id = id;
		
	}
	
	public int getID() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s", this.id, this.name());
	}
}