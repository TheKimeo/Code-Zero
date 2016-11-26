package Level;

import java.awt.Color;
import java.awt.Graphics2D;

import Controller.Camera;

public class Tile {
	
	public static final int TILE_SIZE = 32;
	
	public boolean collidable;
	public double gravity;
	public double friction;
	
	private boolean visible;
	private Color color;
	
	private char ch;
	
	public Tile(boolean col, boolean vis, double gravity, double friction, Color color, char c) {
		collidable = col;
		visible = vis;
		this.gravity = gravity;
		this.friction = friction;
		this.color = color;
		this.ch = c;
	}
	
	public void draw(Graphics2D g, Camera c, int x, int y) {
		//DRAW TILE
		//if (visible) {
			g.setColor(color);
			g.fillRect(x * TILE_SIZE - (int) c.x, y * TILE_SIZE - (int) c.y, TILE_SIZE, TILE_SIZE);
			//g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		//}
	}
	
	public char getChar() {
		return ch;
	}
}
