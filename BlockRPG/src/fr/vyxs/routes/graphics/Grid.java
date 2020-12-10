package fr.vyxs.routes.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import fr.vyxs.routes.ability.Renderable;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.misc.Constants;

public class Grid implements Renderable {

	protected Integer2D cellSize;
	
	public Grid(final Integer2D cellSize) {
		this.cellSize = cellSize;
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.resetTransform();
		graphics.resetFont();
		graphics.resetLineWidth();
		graphics.setColor(new Color(3, 0.3f, 0.3f, 0.3f));
		
		drawVerticalLine(container, graphics);
		drawHorizontalLine(container, graphics);
	}

	private void drawHorizontalLine(GameContainer container, Graphics graphics) {
		for (int i = 0; i < cellSize.getSecond(); i++) {
			final float x = 0;
			final float y = i * Constants.REAL_BLOCK_SIZE.getSecond();
			graphics.drawLine(x, y, container.getWidth(), y);
		}
	}

	private void drawVerticalLine(GameContainer container, Graphics graphics) {
		for (int i = 0; i < cellSize.getFirst(); i++) {
			final float x = i * Constants.REAL_BLOCK_SIZE.getFirst();
			final float y = 0;
			graphics.drawLine(x, y, x, container.getHeight());
		}
	}

}
