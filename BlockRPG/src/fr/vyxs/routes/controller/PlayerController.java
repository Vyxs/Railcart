package fr.vyxs.routes.controller;

import org.newdawn.slick.GameContainer;

import fr.vyxs.routes.ability.MouseInteraction;
import fr.vyxs.routes.ability.Updateable;
import fr.vyxs.routes.entity.Player;
import fr.vyxs.routes.exception.PositionException;
import fr.vyxs.routes.graphics.MouseCursor;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.selection.BlockAroundSelection;
import fr.vyxs.routes.selection.BlockSelection;
import fr.vyxs.routes.selection.DotSelection;

public class PlayerController implements MouseInteraction {

	private Player player;
	private MouseCursor cursor;
	
	public PlayerController() {
		
	}
	
	public void bindPlayer(Player player) {
		this.player = player;
	}
	
	public void setMouseCursor(final MouseCursor cursor) {
		this.cursor = cursor;
		this.cursor.register(this);
	}
	
	@Override
	public void onClick() {
		if (player.hasInteraction())
			try {
				player.getInteraction().addBlockAt(cursor.getBlockModelHolding(), cursor.getLastPositionOnMap());
			} catch (PositionException e) {
				//App.log(Player.class.getSimpleName(), Level.ALL, e.getLocalizedMessage());
			}
	}

	@Override
	public void onRightClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BlockSelection getSelection(Integer2D sourcePosition) {
		return new DotSelection(player.getView(), sourcePosition);
	}

	@Override
	public <T extends BlockSelection> T getSelection(Class<T> class1, Integer2D cp) {
		if (class1.equals(BlockAroundSelection.class))
			return class1.cast(new BlockAroundSelection(player.getView(), cp));
		if (class1.equals(DotSelection.class))
			return class1.cast(new DotSelection(player.getView(), cp));
		throw new UnsupportedOperationException();
	}
	
	private boolean hasCursor() {
		return cursor != null;
	}

}
