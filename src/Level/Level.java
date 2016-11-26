package Level;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entity.Entity;
import javafx.util.Pair;

public class Level {

	private int width;
	private int height;
	
	private ArrayList<Tile> map;
	
	private static Tile TILE_WALL = new Tile(true, true);
	private static Tile TILE_AIR = new Tile(false, false);
	
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
		return map.get(x + y * width);
	}
	
	private void generate() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (y == height - 1) {
					setTile(x, y, TILE_WALL);
				} else {
					setTile(x, y, TILE_AIR);
				}
			}
		}
	}
	
	public ArrayList<Tile> getTilesWithin(Entity e) {
		ArrayList<Tile> ret = new ArrayList<>();
		
		Rectangle bb = e.getBoundingBox();
		int startx = bb.x / Tile.TILE_SIZE;
		int starty = bb.y / Tile.TILE_SIZE;
		int endx = startx + bb.width /Tile.TILE_SIZE;
		int endy = starty + bb.height / Tile.TILE_SIZE;
		
		for (int y = starty; y < endy; ++y) {
			for (int x = startx; x < endx; ++x) {
				ret.add(getTile(x, y));
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
