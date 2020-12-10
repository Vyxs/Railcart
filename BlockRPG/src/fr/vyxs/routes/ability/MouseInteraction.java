package fr.vyxs.routes.ability;

import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.selection.BlockSelection;

public interface MouseInteraction extends Clickable, BlockSelectable {

	<T extends BlockSelection> T getSelection(Class<T> class1, Integer2D cp);

}
