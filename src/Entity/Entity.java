package Entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Command.CommandStream;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.PhysicsComponent;
import Level.Tile;
import javafx.util.Pair;

public class Entity {
	private InputComponent input;
	private PhysicsComponent physics;
	private GraphicsComponent graphics;
	
	private CommandStream commandStream = new CommandStream(this);
	
	//PHYSICS STATE
	public final static int width = 32;
	public final static int height = 48;
	
	public Rectangle boundingBox = new Rectangle(width, height);
	
	public double x = 0.0;
	public double y = 0.0;
	public double dx = 0.0;
	public double dy = 0.0;
	public double maxdx = 3.36;
	public double maxdy = 4.8;
	
	public int jumpTime = 0;
	
	public boolean onCeiling = false;
	public boolean onFloor = false;
	
	//ANIMATION STATE (MAKE GETTERS AND SETTERS)
	public Rectangle renderBox = new Rectangle(width, height);
	public BufferedImage[] frames;
	public int currentFrame;
	public int numFrames;
	
	public int count;
	public int delay;
	
	public int timesPlayed = 0;
	
	
	
	
	public Entity(InputComponent input, PhysicsComponent physics, GraphicsComponent graphics) {
		this.input = input;
		this.physics = physics;
		this.graphics = graphics;
	}
	
	public void reset() {
		input.reset();
		physics.reset();
		graphics.reset();
		x = 3.0 * Tile.TILE_SIZE;
		y = 3.0 * Tile.TILE_SIZE;
	}
	
	public InputComponent getInput() {
		return input;
	}
	
	public PhysicsComponent getPhysics() {
		return physics;
	}
	
	public GraphicsComponent getGraphics() {
		return graphics;
	}
	
	public CommandStream getCommandStream() {
		return this.commandStream;
	}
	
	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}
}
