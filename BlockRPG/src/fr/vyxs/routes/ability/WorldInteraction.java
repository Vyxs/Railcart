package fr.vyxs.routes.ability;

import java.util.List;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.exception.PositionException;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public interface WorldInteraction {
	List<Block> getViewAt(Integer2D position);
	void addBlockAt(BlockModel model, Integer2D position) throws PositionException;
}
