package Level;

public class TileStorage {
	public double centerx;
	public double centery;
	
	public Tile tile;
	
	public TileStorage(Tile t, int x, int y) {
		this.tile = t;
		this.centerx = x * Tile.TILE_SIZE + Tile.TILE_SIZE / 2.0;
		this.centery = y * Tile.TILE_SIZE + Tile.TILE_SIZE / 2.0;
	}
}
