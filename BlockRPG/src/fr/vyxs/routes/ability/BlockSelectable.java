package fr.vyxs.routes.ability;

import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.selection.BlockSelection;

public interface BlockSelectable {
	BlockSelection getSelection(final Integer2D sourcePosition);
}
