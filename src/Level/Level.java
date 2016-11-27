package Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.Camera;
import Entity.Entity;

public class Level {

	private int width;
	private int height;
	
	private ArrayList<Tile> map;
	
	private static Tile TILE_WALL = new Tile(true, true, 0.1152, 0.4, new Color(150, 100, 0), '#', "Textures/Dirt.png"); // 0.1152
	private static Tile TILE_AIR = new Tile(false, false, 0.1152, 0.03456, new Color(200, 200, 255), '.', "");
	private static Tile TILE_STONE = new Tile(true, true, 0.1152, 0.4, new Color(100, 100, 100), '#', "Textures/Stone.png"); // 0.1152
	private static Tile TILE_GRASS = new Tile(true, true, 0.1152, 0.03456, new Color(100, 255, 100), '.', "Textures/Grass.png");
	private static Tile TILE_WOOD = new Tile(true, true, 0.1152, 0.03456, new Color(70, 30, 0), '.', "Textures/WoodV.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new ArrayList<>(width * height);
		for (int i = 0; i < width * height; ++i) {
			map.add(TILE_AIR);
		}
		
		//generate();
		generateFromFile("Map2.txt");
		//printMapToConsole();
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
				if (y > height / 2.0) {
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
		for (int x = xPosStart; x <= xPosEnd; ++x) {
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
	
	public void draw(Graphics2D g, Camera c) {
		int sx = (int) c.x / Tile.TILE_SIZE;
		int ex = (int) (c.x + c.width) / Tile.TILE_SIZE;
		int sy = (int) c.y / Tile.TILE_SIZE;
		int ey = (int) (c.y + c.height) / Tile.TILE_SIZE + 1;
		
		if (sx < 0) sx = 0;
		if (sy < 0) sy = 0;
		if (ex > width - 1) ex = width - 1;
		if (ey > height - 1) ey = height - 1;
		
		for (int y = sy; y <= ey; ++y) {
			for (int x = sx; x <= ex; ++x) {
				map.get(x + y * width).draw(g, c, x, y);
			}
		}
	}
	
	public void generateFromFile(String path) {
		try {
			FileInputStream inputStream = new FileInputStream(path);
	        Scanner in = new Scanner(inputStream);

	        //Loop through the width and height
	        for (int y = 0; y < height; ++y) {
	            String line = in.nextLine();
	            for (int x = 0; x < width; ++x) {
	                char t = line.charAt(x);
	                switch (t) {
	                    case '#': //Wall tile
	                        setTile(x, y, Level.TILE_WALL);
	                        break;
	                    case '.': //Floor tile
	                    	setTile(x, y, Level.TILE_AIR);
	                        break;
	                    case '1': //Floor tile
	                    	setTile(x, y, Level.TILE_GRASS);
	                        break;
	                    case '2': //Floor tile
	                    	setTile(x, y, Level.TILE_WOOD);
	                        break;
	                    case '3': //Floor tile
	                    	setTile(x, y, Level.TILE_STONE);
	                        break;
	                    default: //Everything else
	                        System.out.println("ERROR: Tile not recognised (" + t + ")");
	                        setTile(x, y, Level.TILE_WALL);
	                        break;
	                }
	            }
	        }
	        in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void printMapToConsole() {
		for (int i = 0; i < width * height; ++i) {
			System.out.print(map.get(i).getChar());
			if ((i + 1) % width == 0)
				System.out.println();
		}
	}
}



















