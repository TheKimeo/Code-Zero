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
	
	public int teleportFrame = 0;
	
	//ANIMATION STATE
	//idle
    private static String[] leftIdlePaths = new String[]{"IdleAnimations/Subaru_LeftIdle1.png","IdleAnimations/Subaru_LeftIdle2.png"};
    private static String[] rightIdlePaths = new String[]{"IdleAnimations/Subaru_RightIdle1.png","IdleAnimations/Subaru_RightIdle2.png"};
    //walk
    private static String[] leftWalkPaths = new String[]{"WalkAnimations/Subaru_LeftWalk1.png","WalkAnimations/Subaru_LeftWalk2.png", "WalkAnimations/Subaru_LeftWalk3.png","WalkAnimations/Subaru_LeftWalk4.png"};
    private static String[] rightWalkPaths = new String[]{"WalkAnimations/Subaru_RightWalk1.png","WalkAnimations/Subaru_RightWalk2.png","WalkAnimations/Subaru_RightWalk3.png","WalkAnimations/Subaru_RightWalk4.png"};

    //death
    private static String[] leftDeath = new String[]{"DeathAnimations/Subaru_LeftDeath1.png","DeathAnimations/Subaru_LeftDeath2.png","DeathAnimations/Subaru_LeftDeath3.png","DeathAnimations/Subaru_LeftDeath4.png","DeathAnimations/Subaru_LeftDeath5.png","DeathAnimations/Subaru_LeftDeath6.png","DeathAnimations/Subaru_LeftDeath7.png","DeathAnimations/Subaru_LeftDeath8.png"}; //,"DeathAnimations/Subaru_LeftDeath9.png"
    private static String[] rightDeath = new String[]{"DeathAnimations/Subaru_RightDeath1.png","DeathAnimations/Subaru_RightDeath2.png","DeathAnimations/Subaru_RightDeath3.png","DeathAnimations/Subaru_RightDeath4.png","DeathAnimations/Subaru_RightDeath5.png","DeathAnimations/Subaru_RightDeath6.png","DeathAnimations/Subaru_RightDeath7.png","DeathAnimations/Subaru_RightDeath8.png"}; //,"DeathAnimations/Subaru_RightDeath9.png"

    //left right warp
    private static String[] leftWarp = new String[]{"TimeWarpAnimations/Subaru_LeftTime1.png", "TimeWarpAnimations/Subaru_LeftTime2.png", "TimeWarpAnimations/Subaru_LeftTime3.png", "TimeWarpAnimations/Subaru_LeftTime4.png", "TimeWarpAnimations/Subaru_LeftTime5.png", "TimeWarpAnimations/Subaru_LeftTime4.png", "TimeWarpAnimations/Subaru_LeftTime3.png", "TimeWarpAnimations/Subaru_LeftTime2.png", "TimeWarpAnimations/Subaru_LeftTime1.png",};
    private static String[] rightWarp = new String[]{"TimeWarpAnimations/Subaru_RightTime1.png", "TimeWarpAnimations/Subaru_RightTime2.png", "TimeWarpAnimations/Subaru_RightTime3.png", "TimeWarpAnimations/Subaru_RightTime4.png", "TimeWarpAnimations/Subaru_RightTime5.png", "TimeWarpAnimations/Subaru_RightTime4.png", "TimeWarpAnimations/Subaru_RightTime3.png", "TimeWarpAnimations/Subaru_RightTime2.png", "TimeWarpAnimations/Subaru_RightTime1.png",};

    //right left idle
	public static BufferedImage[] leftIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftIdlePaths)));
	public static BufferedImage[] rightIdleFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightIdlePaths)));
    //right left moving
    public static BufferedImage[] leftWalkFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftWalkPaths)));
	public static BufferedImage[] rightWalkFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightWalkPaths)));
    //left right death
    public static BufferedImage[] leftDeathFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftDeath)));
    public static BufferedImage[] rightDeathFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightDeath)));
    //left right warp
    public static BufferedImage[] leftWarpFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(leftWarp)));
    public static BufferedImage[] rightWarpFrames = ContentLoader.animationSprites(new ArrayList<>(Arrays.asList(rightWarp)));

	public BufferedImage[] frames;
	public int currentFrame;
	public int numFrames;
	
	
	public boolean facing = false;
	
	
	public int count;
	public int delay;
	
	public int timesPlayed = 0;
	public boolean replay = true;
	
	public boolean directionAI = true;
	
	public boolean warping = false;
	public double destx = 0.0;
	public double desty = 0.0;
	
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
