package Level;

import java.awt.Graphics2D;

public class Tile {
	
	public static final int TILE_SIZE = 64;
	
	public boolean collidable;
	private boolean visible;
	
	public Tile(boolean col, boolean vis) {
		collidable = col;
		visible = vis;
	}
	
	public void draw(Graphics2D g) {
		//DRAW TILE
		//if (visible)
		//	g.drawImage(s);
	}
}
