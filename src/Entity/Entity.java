package Entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Command.CommandStream;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.PhysicsComponent;
import javafx.util.Pair;

public class Entity {
	private InputComponent input;
	private PhysicsComponent physics;
	private GraphicsComponent graphics;
	
	private CommandStream commandStream = new CommandStream();
	
	//PHYSICS STATE
	public final static int width = 32;
	public final static int height = 48;
	
	public Rectangle boundingBox = new Rectangle(width, height);
	public Rectangle renderBox = new Rectangle(width, height);
	
	//ANIMATION STATE (MAKE GETTERS AND SETTERS)
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
