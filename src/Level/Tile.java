package Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tile {
	
	public static final int TILE_SIZE = 32;
	
	public boolean collidable;
	public double gravity;
	public double friction;
	
	private boolean visible;
	private Color c;
	
	public Tile(boolean col, boolean vis, double gravity, double friction, Color c) {
		collidable = col;
		visible = vis;
		this.gravity = gravity;
		this.friction = friction;
		this.c = c;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		//DRAW TILE
		//if (visible) {
			g.setColor(c);
			g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		//}
	}
}
