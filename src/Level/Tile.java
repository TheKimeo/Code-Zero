package Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tile {
	
	public static final int TILE_SIZE = 32;
	
	public boolean collidable;
	private boolean visible;
	
	public Tile(boolean col, boolean vis) {
		collidable = col;
		visible = vis;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		//DRAW TILE
		if (visible) {
			g.setColor(new Color(0xFF0000));
			g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
	}
}
