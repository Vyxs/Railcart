package fr.vyxs.routes.graphics;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import fr.vyxs.routes.ability.BlockSelectable;
import fr.vyxs.routes.ability.MouseInteraction;
import fr.vyxs.routes.ability.Renderable;
import fr.vyxs.routes.ability.Updateable;
import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.block.BlockType;
import fr.vyxs.routes.core.App;
import fr.vyxs.routes.debug.Log;
import fr.vyxs.routes.exception.NoModelFound;
import fr.vyxs.routes.factory.BlockModelFactory;
import fr.vyxs.routes.math.Boolean4D;
import fr.vyxs.routes.math.Float2D;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.math.MapPositionTransformer;
import fr.vyxs.routes.math.SideAllowed;
import fr.vyxs.routes.model.BlockModel;
import fr.vyxs.routes.selection.BlockAroundSelection;
import fr.vyxs.routes.selection.BlockSelection;
import fr.vyxs.routes.selection.DotSelection;

public class MouseCursor implements Renderable, Updateable {

	private BlockImageList imgList;
	private BlockModel blockModelHolding;
	private Float2D cursorPosition;
	private MouseInteraction target;
	private Integer2D lastPositionOnMap;
	
	public MouseCursor(BlockImageList list) throws NoModelFound {
		imgList = list;
		blockModelHolding = BlockModelFactory.get().create(BlockType.RAIL_ALL);
		cursorPosition = new Float2D();
		lastPositionOnMap = new Integer2D();
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		cursorPosition = getMousePosition(container);
		checkMove();
		handleClick(container);
        handleRightClick(container);
	}

	private void checkMove() {
		if (!lastPositionOnMap.equals((MapPositionTransformer.toMapCoord(cursorPosition)))) {
			lastPositionOnMap = MapPositionTransformer.toMapCoord(cursorPosition);
			getRightBlockConnector();
		}
	}

	private void handleRightClick(GameContainer container) {
		if (userRightClick(container)) {
        	try {
				nextImage();
			} catch (NoModelFound e) {
				App.log(e.getMessage());
			}
        	this.target.onRightClick();
        }
	}

	private void handleClick(GameContainer container) {
		if (userClick(container)) {
	        this.target.onClick();
        }
	}
	
	private void getRightBlockConnector() {
		Integer2D actualPosition = lastPositionOnMap;
		
		BlockAroundSelection selection = (BlockAroundSelection) target.getSelection(BlockAroundSelection.class, actualPosition);
		BlockModel modelTop = selection.getBlockModelTop();
		BlockModel modelBot = selection.getBlockModelBottom();
		BlockModel modelRight = selection.getBlockModelRight();
		BlockModel modelLeft = selection.getBlockModelLeft();
		
	
		SideAllowed sideRequired = new SideAllowed(	modelTop == null ? false : modelTop.getConnectionsAllowed().getBottom().booleanValue(),
													modelRight == null ? false : modelRight.getConnectionsAllowed().getLeft().booleanValue(),
													modelBot == null ? false : modelBot.getConnectionsAllowed().getTop().booleanValue(),
													modelLeft == null ? false : modelLeft.getConnectionsAllowed().getRight().booleanValue());
		Log.i("sr " + sideRequired.toFormattedString());
		try {
			this.blockModelHolding = BlockModelFactory.get().createRail(sideRequired);
		} catch (NoModelFound e) {
			Log.e(e.getLocalizedMessage());
		}
	}
	
	public BlockModel getBlockModelHolding() {
		return this.blockModelHolding;
	}
	
	public void register(MouseInteraction mouseInteraction) {
		this.target = mouseInteraction;
	}
	
	private Float2D getMousePosition(GameContainer gc) {
		return new Float2D((gc.getInput().getMouseX()), gc.getInput().getMouseY());
	}
	
	public boolean userClick(GameContainer gameContainer) {
		return gameContainer.getInput().isMousePressed(0);
	}
	
	private boolean userRightClick(GameContainer gameContainer) {
		return gameContainer.getInput().isMousePressed(1);
	}

	private void nextImage() throws NoModelFound {
		int hId = this.blockModelHolding.getBlockImage().getID();
		hId++;
		hId = hId >= ImageBlockType.values().length ? 0 : hId;
		this.blockModelHolding = BlockModelFactory.get().create(BlockType.values()[hId]);
	}
	
	@Override
	public void render(final GameContainer container, final Graphics graphics) {
		if (imgList.get(blockModelHolding.getBlockImage()) == null)
			return;
		graphics.drawImage(imgList.get(blockModelHolding.getBlockImage()), cursorPosition.getFirst() - 32, cursorPosition.getSecond() - 32);
		renderDebug(graphics);
	}
	
	public void renderDebug(final Graphics graphics) {
		BlockModel model = target.getSelection(DotSelection.class, lastPositionOnMap).getBlockModel();
		String hover = "";
		if (model != null) {
			hover = model.getBlockImage().name() + "\n";
			hover += model.getConnectionsAllowed().toFormattedString();
			hover += "\n" + lastPositionOnMap.toString();
		} else {
			hover = lastPositionOnMap.toString();;
		}
		
		graphics.setColor(new Color(0f, 0.5f, 0.9f, 1f));
		graphics.fillRect(this.cursorPosition.getFirst(), this.cursorPosition.getSecond() + 10,  graphics.getFont().getWidth(hover) + 10, graphics.getFont().getHeight(hover) + 20);
		graphics.setColor(new Color(0f, 0f, 0f, 1f));
		graphics.drawString(hover, this.cursorPosition.getFirst() + 5, this.cursorPosition.getSecond() + 25);
		
		
		graphics.setColor(new Color(100f, 0f, 0f, 0.25f));
		graphics.drawRect(0,  0,  220, 300);
		graphics.setColor(new Color(0f, 0f, 0f, 0.8f));
		graphics.fillRect(0,  0,  220, 300);
		graphics.setColor(Color.orange);
		StringBuilder builder = new StringBuilder();
		builder.append(blockModelHolding.getBlockImage().toString())
		.append("\n")
		.append(blockModelHolding.getConnectionsAllowed().toFormattedString())
		.append("\n")
		.append("[cursor] ")
		.append(cursorPosition.toString())
		.append("\n[map] ")
		.append(MapPositionTransformer.toMapCoord(cursorPosition).toString());
		graphics.drawString(builder.toString(), 5, 40);
	}

	public Image getImage() {
		return imgList.get(blockModelHolding.getBlockImage());
	}

	public Integer2D getLastPositionOnMap() {
		return this.lastPositionOnMap;
	}
}
