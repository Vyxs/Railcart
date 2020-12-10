package fr.vyxs.routes.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import fr.vyxs.routes.ability.Renderable;
import fr.vyxs.routes.ability.Updateable;
import fr.vyxs.routes.ability.WorldInteraction;
import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.block.BlockType;
import fr.vyxs.routes.entity.Player;
import fr.vyxs.routes.entity.UniqueIdentifier;
import fr.vyxs.routes.exception.NoModelFound;
import fr.vyxs.routes.exception.PositionException;
import fr.vyxs.routes.exception.WorldException;
import fr.vyxs.routes.factory.BlockFactory;
import fr.vyxs.routes.factory.BlockModelFactory;
import fr.vyxs.routes.graphics.BlockImageList;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.misc.Constants;
import fr.vyxs.routes.model.BlockModel;

public class World implements Renderable, Updateable, WorldInteraction {
	
	private static Long currentIncrementedId = 0L;
	private List<Block> blocks;
	private final BlockImageList imgs;
	private Map<UniqueIdentifier, Player> mapIdPlayers;
	private List<UniqueIdentifier> customRenderingList;
	
	public World(BlockImageList imgs) {
		this.blocks = new ArrayList<>();
		this.mapIdPlayers = new HashMap<>();
		this.customRenderingList = new ArrayList<>();
		this.imgs = imgs;
	}
	
	public UniqueIdentifier register(Player player) throws IllegalArgumentException {
		if (player == null)
			throw new IllegalArgumentException("Player cannot be null.");
		final UniqueIdentifier uid = getNewUID();
		this.mapIdPlayers.put(uid, player);
		return uid;
	}
	
	public Player getPlayer(UniqueIdentifier uid) throws WorldException {
		if (!hasPlayer(uid))
			throw new WorldException(String.format("There is no player registered with UID:%s", uid.getID()));
		return this.mapIdPlayers.get(uid);
	}
	
	public boolean hasPlayer(UniqueIdentifier uid) {
		return mapIdPlayers.containsKey(uid);
	}
	
	protected static UniqueIdentifier getNewUID() {
		return new UniqueIdentifier(++currentIncrementedId);
	}

	@Override
	public void render(final GameContainer container, final Graphics graphics) {
		blocks.forEach(	     (b) ->    {b.render(container, graphics);});
		mapIdPlayers.forEach((k, v) -> {
			if (!customRenderingList.contains(k))
				v.render(container, graphics);
		});
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		mapIdPlayers.forEach((k, v) -> {v.update(container, delta);});
	}

	@Override
	public void addBlockAt(BlockModel model, Integer2D position) throws PositionException {
		if (hasBlockAt(position))
			throw new PositionException(String.format("There is already a block at position %s", position.toString()));
		final BlockFactory factory = new BlockFactory(this.imgs, Constants.REAL_BLOCK_SIZE);
		blocks.add(factory.create(model, model.getBlockImage(), position));
	}

	private boolean hasBlockAt(Integer2D position) {
		for (Block block : blocks)
			if (block.getPosition().equals(position))
				return true;
		return false;
	}

	@Override
	public List<Block> getViewAt(Integer2D position) {
		return this.blocks;
	}

	public void setCustomRender(UniqueIdentifier id, boolean enable) {
		if (hasPlayer(id)) {
			if (enable && !customRenderingList.contains(id))
				customRenderingList.add(id);
			else if (!enable)
				customRenderingList.remove(id);
		}
	}

	public void prepopulate() {
		try {
			
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_HORIZONTAL), new Integer2D(4, 5));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_HORIZONTAL), new Integer2D(5, 5));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_HORIZONTAL), new Integer2D(6, 5));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_BOTTOM_LEFT), new Integer2D(7, 5));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_BOTTOM_RIGHT), new Integer2D(3, 5));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_VERTICAL), new Integer2D(7, 6));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_TOP_LEFT_BOT), new Integer2D(3, 6));
			addBlockAt(BlockModelFactory.get().create(BlockType.RAIL_HORIZONTAL), new Integer2D(2, 6));
			
		} catch (PositionException | NoModelFound e) {
			e.printStackTrace();
		}
	}
}
