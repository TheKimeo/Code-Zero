package Level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import java.awt.Color;

import Entity.Entity;
import javafx.util.Pair;

public class Level {

	private int width;
	private int height;
	
	private ArrayList<Tile> map;
	
	private static Tile TILE_WALL = new Tile(true, true, 0.1152, 0.1152, new Color(100, 200, 100));
	private static Tile TILE_AIR = new Tile(false, false, 0.1152, 0.03456, new Color(200, 200, 255));
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new ArrayList<>(width * height);
		for (int i = 0; i < width * height; ++i) {
			map.add(TILE_AIR);
		}
		
		generate();
	}
	
	public void setTile(int x, int y, Tile t) {
		map.set(x + y * width, t);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return map.get(x + y * width);
	}
	
	private void generate() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (y >= height - 1) {
					setTile(x, y, TILE_WALL);
				} else {
					setTile(x, y, TILE_AIR);
				}
			}
		}
	}
	
	public ArrayList<TileStorage> getTilesBelow(Entity e) {
		ArrayList<TileStorage> ret = new ArrayList<>();
		
		int xPosStart = (int) e.x / Tile.TILE_SIZE;
		int xPosEnd = (int) (e.x + e.boundingBox.width - 1) / Tile.TILE_SIZE;
		int yPos = (int) (e.y + e.boundingBox.height) / Tile.TILE_SIZE;
		for (int x = xPosStart; x < xPosEnd; ++x) {
			Tile t = getTile(x, yPos);
			if (t != null) {
				ret.add(new TileStorage(t, x, yPos));
			}
		}
		
		return ret;
	}
	
	public ArrayList<TileStorage> getTilesWithin(Entity e) {
		ArrayList<TileStorage> ret = new ArrayList<>();
		
		int startx = (int) e.x / Tile.TILE_SIZE;
		int starty = (int) e.y / Tile.TILE_SIZE;
		int endx = (int) (e.x + e.width - 1) / Tile.TILE_SIZE;
		int endy = (int) (e.y + e.height - 1) / Tile.TILE_SIZE;
		
		for (int y = starty; y <= endy; ++y) {
			for (int x = startx; x <= endx; ++x) {
				Tile t = getTile(x, y);
				if (t != null) {
					ret.add(new TileStorage(t, x, y));
				}
			}
		}
		return ret;
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.size(); ++i) {
			map.get(i).draw(g, i % width, i / width);
		}
	}
}
