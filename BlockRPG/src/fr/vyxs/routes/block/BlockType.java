package fr.vyxs.routes.block;

public enum BlockType {
	PLAYER (0),
	RAIL_ALL (1),
	RAIL_TOP_LEFT (2),
	RAIL_TOP_RIGHT (3),
	RAIL_BOTTOM_LEFT (4),
	RAIL_BOTTOM_RIGHT (5),
	RAIL_TOP_RIGHT_BOT (6),
	RAIL_TOP_LEFT_BOT (7),
	RAIL_RIGHT_BOT_LEFT(8),
	RAIL_RIGHT_TOP_LEFT (9),
	RAIL_HORIZONTAL (10),
	RAIL_VERTICAL (11),
	RAIL_UNLINKED (12);
	
	private final int id;
	
	BlockType(int id) {
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
