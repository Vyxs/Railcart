package fr.vyxs.routes.block;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import fr.vyxs.routes.ability.Renderable;
import fr.vyxs.routes.math.Float2D;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public class Block implements Renderable {
	
	private final org.newdawn.slick.Image image;
	private Integer2D position;
	private final Integer2D size;
	private final BlockModel model;
	
	public Block(BlockModel model, org.newdawn.slick.Image image, Integer2D size, Float2D position) {
		this.model = model;
		this.image = image;
		this.size = size;
		this.position = new Integer2D(Math.round(position.getFirst()), Math.round(position.getSecond()));
	}
	
	public Block(BlockModel model, org.newdawn.slick.Image image, Integer2D size, Integer2D position) {
		this.model = model;
		this.image = image;
		this.size = size;
		this.position = position;
	}

	@Override
	public void render(final GameContainer container, final Graphics graphics) {
		graphics.drawImage(image, position.getFirst() * size.getFirst(), position.getSecond() * size.getSecond());
	}

	public Integer2D getSize() {
		return this.size;
	}
	
	public Integer2D getPosition() {
		return this.position;
	}

	public BlockModel getModel() {
		return this.model;
	}

}
