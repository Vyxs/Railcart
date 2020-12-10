package fr.vyxs.routes.selection;

import java.util.List;

import fr.vyxs.routes.block.Block;
import fr.vyxs.routes.debug.Log;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.model.BlockModel;

public class BlockAroundSelection extends BlockSelection {

	private BlockModel[][] blockModelAroundSourcePosition;
	
	public BlockAroundSelection(final List<Block> initialBlocks, final Integer2D sourcePosition) {
		super(initialBlocks, sourcePosition);
		this.refreshInitialBlocks(this.initialBlockList);
	}
	
	public void refreshInitialBlocks(final List<Block> initialBlocks) {
		this.initialBlockList = initialBlocks;
		blockModelAroundSourcePosition = getBlocksAround(initialBlockList, sourcePosition);
	}
	
	public BlockModel getBlockModelCenter() {
		return blockModelAroundSourcePosition[1][1];
	}
	
	public BlockModel getBlockModelAt(Integer2D index) {
		if (index.getFirst() > 2 || index.getSecond() > 2)
			throw new ArrayIndexOutOfBoundsException(String.format("Invalid index %s, limit {0-2,0-2}", index.toString()));
		return blockModelAroundSourcePosition[index.getFirst()][index.getSecond()];
	}
	
	public BlockModel getBlockModelLeft() {
		return blockModelAroundSourcePosition[1][0];
	}
	
	public BlockModel getBlockModelRight() {
		return blockModelAroundSourcePosition[1][2];
	}
	
	public BlockModel getBlockModelTop() {
		return blockModelAroundSourcePosition[0][1];
	}
	
	public BlockModel getBlockModelBottom() {
		return blockModelAroundSourcePosition[2][1];
	}
	
	public BlockModel getBlockModelTopLeft() {
		return blockModelAroundSourcePosition[0][0];
	}
	
	public BlockModel getBlockModelTopRight() {
		return blockModelAroundSourcePosition[0][2];
	}
	
	public BlockModel getBlockModelBottomLeft() {
		return blockModelAroundSourcePosition[2][0];
	}
	
	public BlockModel getBlockModelBottomRight() {
		return blockModelAroundSourcePosition[2][2];
	}
	
	public BlockModel[][] getBlocksAround() {
		return blockModelAroundSourcePosition;
	}
	
	protected static BlockModel[][] getBlocksAround(List<Block> blocks, Integer2D position) {
		BlockModel[][] blocksAround = new BlockModel[3][3];
		Integer2D[][] matrice = getMatriceOfPosition(position);
				
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++) {
				blocksAround[y][x] = getBlockAtPosition(blocks, matrice[y][x]);
				Log.w(y+":y "+x+"x: "+getBlockAtPosition(blocks, matrice[y][x]));
			}
				
		return blocksAround;
	}
	
	protected static Integer2D[][] getMatriceOfPosition(Integer2D p) {
		final Integer2D[][] matrice = {
			{new Integer2D(p.getFirst() - 1, p.getSecond() - 1), new Integer2D(p.getFirst(), p.getSecond() - 1), new Integer2D(p.getFirst() + 1, p.getSecond() - 1)},
			{new Integer2D(p.getFirst() - 1, p.getSecond()), new Integer2D(p.getFirst(), p.getSecond()), new Integer2D(p.getFirst() + 1, p.getSecond())},
			{new Integer2D(p.getFirst() - 1, p.getSecond() + 1 ), new Integer2D(p.getFirst(), p.getSecond() + 1), new Integer2D(p.getFirst() + 1, p.getSecond() + 1)},
		};
		return matrice;
	}
	
	protected final boolean isNull(BlockModel model) {
		return model == null;
	}
	
	protected final String getCharOf(BlockModel model) {
		if (isNull(model))
			return "null";
		return model.getBlockImage().name();
	}
	
	@Override
	public String toString() {
		String rsl = String.format("\n[%s][%s][%s]\n[%s][%s][%s]\n[%s][%s][%s]",
				getCharOf(getBlockModelTopLeft()), getCharOf(getBlockModelTop()), getCharOf(getBlockModelTopRight()),
				getCharOf(getBlockModelLeft()), getCharOf(getBlockModelCenter()), getCharOf(getBlockModelRight()),
				getCharOf(getBlockModelBottomLeft()), getCharOf(getBlockModelBottom()), getCharOf(getBlockModelBottomRight()));
		return rsl;
	}
}
