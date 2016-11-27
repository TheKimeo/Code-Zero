package Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Controller.Camera;
import Utils.ContentLoader;

import javax.imageio.ImageIO;

public class Tile {
	
	public static final int TILE_SIZE = 36; //32
	
	public boolean collidable;
	public double gravity;
	public double friction;
	
	private boolean visible;
	private Color color;
	private char ch;
	private BufferedImage bufferedImage;
	
	public Tile(boolean col, boolean vis, double gravity, double friction, Color color, char c, String tilePath) {
		collidable = col;
		visible = vis;
		this.gravity = gravity;
		this.friction = friction;
		this.color = color;
		this.ch = c;
		if (!tilePath.isEmpty()) {
            try {
                bufferedImage = ImageIO.read(new FileInputStream(tilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void draw(Graphics2D g, Camera c, int x, int y) {
		//DRAW TILE
		if (visible) {
			g.drawImage(bufferedImage, (int) (x * TILE_SIZE - c.x), (int) (y * TILE_SIZE - c.y), TILE_SIZE, TILE_SIZE, null);
			//g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		} else {
			//g.setColor(new Color((int) (200.0 * y / 100.0), (int) (200.0 * y / 100.0), (int) (255.0 * y / 100.0)));
			g.setColor(new Color(200, 200, 255));
			g.fillRect(x * TILE_SIZE - (int) c.x, y * TILE_SIZE - (int) c.y, TILE_SIZE, TILE_SIZE);
		}
	}
	
	public char getChar() {
		return ch;
	}
}
