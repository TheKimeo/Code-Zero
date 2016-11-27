package Entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.util.Arrays;

import Command.CommandStream;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.PhysicsComponent;
import Level.Tile;
import Utils.Pair;
import Utils.ContentLoader;

public class Entity {
	private InputComponent input;
	private PhysicsComponent physics;
	private GraphicsComponent graphics;
	
	private CommandStream commandStream = new CommandStream(this);
	public ArrayList<Pair<Double, Double>> positions = new ArrayList<>();
	
	//PHYSICS STATE
	public double width = 75.0; //50
	public double height = 112.0; //75

	public double renderOffsetX = 14.0; //4.0
	public double renderOffsetY = 0.0; //0.0
	
	public Rectangle boundingBox;
	
	public double x = 0.0;
	public double y = 0.0;
	public double dx = 0.0;
	public double dy = 0.0;
	public double maxdx = 3.36;
	public double maxdy = 4.8;
	
	public int jumpTime = 0;
	
	public boolean onCeiling = false;
	public boolean onFloor = false;
	
	public boolean isRewind = false;
	
	//ANIMATION STATE
	//Left right image arrays
    private String[] leftIdlePaths = new String[]{"Subaru_LeftIdle1.png","Subaru_LeftIdle2.png"};
    private String[] rightIdlePaths = new String[]{"Subaru_RightIdle1.png","Subaru_RightIdle2.png"};

	public BufferedImage[] leftIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftIdlePaths)));
	public BufferedImage[] rightIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightIdlePaths)));
	
	public BufferedImage[] frames;
	public int currentFrame;
	public int numFrames;
	
	public int count;
	public int delay;
	
	public int timesPlayed = 0;
	
	
	
	
	public Entity(double width, double height, InputComponent input, PhysicsComponent physics, GraphicsComponent graphics) {
		this.input = input;
		this.physics = physics;
		this.graphics = graphics;
		
		this.width = width;
		this.height = height;
		
		boundingBox = new Rectangle(75, 112);
	}
	
	public void reset() {
		input.reset();
		physics.reset();
		graphics.reset(this);
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
