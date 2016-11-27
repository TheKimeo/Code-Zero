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
	
	public boolean isWalkingLeft = false;
	public boolean isWalkingRight = false;
	
	public boolean isRewind = false;
	
	//ANIMATION STATE
	//Left right image arrays
    private String[] leftIdlePaths = new String[]{"IdleAnimations/Subaru_LeftIdle1.png","IdleAnimations/Subaru_LeftIdle2.png"};
    private String[] rightIdlePaths = new String[]{"IdleAnimations/Subaru_RightIdle1.png","IdleAnimations/Subaru_RightIdle2.png"};
    private String[] leftWalkPaths = new String[]{"WalkAnimations/Subaru_LeftWalk1.png","WalkAnimations/Subaru_LeftWalk2.png", "WalkAnimations/Subaru_LeftWalk3.png","WalkAnimations/Subaru_LeftWalk4.png"};
    private String[] rightWalkPaths = new String[]{"WalkAnimations/Subaru_RightWalk1.png","WalkAnimations/Subaru_RightWalk2.png","WalkAnimations/Subaru_RightWalk3.png","WalkAnimations/Subaru_RightWalk4.png"};
    //Jumping
    private String[] leftjumpStartPaths = new String[]{"JumpAnimations/Subaru_LeftJump1.png","JumpAnimations/Subaru_LeftJump2.png","JumpAnimations/Subaru_LeftJump3.png","JumpAnimations/Subaru_LeftJump4.png"};
    private String[] leftjumpEndPaths = new String[]{"JumpAnimations/Subaru_LeftJump5.png","JumpAnimations/Subaru_LeftJump6.png", "JumpAnimations/Subaru_LeftJump7.png", "JumpAnimations/Subaru_LeftJump8.png", "JumpAnimations/Subaru_LeftJump9.png"};
    private String[] rightJumpStartPaths = new String[]{"JumpAnimations/Subaru_RightJump1.png","JumpAnimations/Subaru_RightJump2.png","JumpAnimations/Subaru_RightJump3.png","JumpAnimations/Subaru_RightJump4.png"};
    private String[] rightjumpEndPaths = new String[]{"JumpAnimations/Subaru_RightJump5.png","JumpAnimations/Subaru_RightJump6.png", "JumpAnimations/Subaru_RightJump7.png", "JumpAnimations/Subaru_RightJump8.png", "JumpAnimations/Subaru_RightJump9.png"};
    //right left idle
	public BufferedImage[] leftIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftIdlePaths)));
	public BufferedImage[] rightIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightIdlePaths)));
    //right left moving
    public BufferedImage[] leftWalkFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftWalkPaths)));
	public BufferedImage[] rightWalkFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightWalkPaths)));
    //left right jumps
    public BufferedImage[] leftJumpStartFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftjumpStartPaths)));
    public BufferedImage[] rightJumpStartFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightJumpStartPaths)));
    public BufferedImage[] leftJumpEndFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftjumpEndPaths)));
    public BufferedImage[] rightJumpEndFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightjumpEndPaths)));

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
		x = 30.0 * Tile.TILE_SIZE;
		y = 30.0 * Tile.TILE_SIZE;
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
