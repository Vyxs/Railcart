package fr.vyxs.routes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import fr.vyxs.routes.ability.MouseInteraction;
import fr.vyxs.routes.ability.Renderable;
import fr.vyxs.routes.ability.Updateable;
import fr.vyxs.routes.ability.WorldInteraction;
import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.block.BlockType;
import fr.vyxs.routes.core.App;
import fr.vyxs.routes.exception.NoModelFound;
import fr.vyxs.routes.exception.PositionException;
import fr.vyxs.routes.factory.BlockModelFactory;
import fr.vyxs.routes.graphics.MouseCursor;
import fr.vyxs.routes.math.Float2D;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.math.MapPositionTransformer;
import fr.vyxs.routes.model.BlockModel;
import fr.vyxs.routes.selection.BlockAroundSelection;
import fr.vyxs.routes.selection.BlockSelection;
import fr.vyxs.routes.selection.DotSelection;

public class Player implements Renderable, Updateable {

	private Integer2D position;
	private float rotation;
	private List<Block> view;
	private WorldInteraction interaction;
	private BlockModel model;
	private Image image;
	
	public Player(Integer2D position) {
		this.view = new ArrayList<>();
		this.position = new Integer2D(position);
		try {
			this.model = BlockModelFactory.get().create(BlockType.PLAYER);
		} catch (NoModelFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player() {
		this(new Integer2D(0, 0));
	}
	
	public List<Block> getView() {
		return this.view;
	}
	
	public Integer2D getPosition() {
		return this.position;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public void setWorldInteraction(WorldInteraction interaction) {
		this.interaction = interaction;
		this.view = interaction.getViewAt(this.position);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		
	}

	@Override
	public void render(final GameContainer container, final Graphics graphics) {
		final Float2D pos = MapPositionTransformer.toGraphicCoord(position);
		graphics.rotate(pos.getFirst() + 32, pos.getSecond() + 32, rotation);
		graphics.drawImage(image, pos.getFirst(), pos.getSecond());
		graphics.resetTransform();
	}
	
	public boolean hasInteraction() {
		return interaction != null;
	}

	public WorldInteraction getInteraction() {
		return this.interaction;
	}
}
