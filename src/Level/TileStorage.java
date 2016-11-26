package Level;

public class TileStorage {
	public double centerx;
	public double centery;
	
	public Tile tile;
	
	public TileStorage(Tile t, int x, int y) {
		this.tile = t;
		this.centerx = centerx * Tile.TILE_SIZE + Tile.TILE_SIZE / 2.0;
		this.centery = centery * Tile.TILE_SIZE + Tile.TILE_SIZE / 2.0;
	}
}
