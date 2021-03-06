package Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Component.AIInput;
import Component.EntityGraphics;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.KeyboardInput;
import Component.LevelPhysics;
import Component.PhysicsComponent;
import Entity.Entity;
import Level.Level;
import Utils.FPSLimiter;

@SuppressWarnings("serial")
public class GameManager extends JPanel implements Runnable, KeyListener {

	
	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int HEIGHT2 = HEIGHT + 16;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int TARGET_FPS = 60;
	public static int frameDelay = 0;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;
	
	private Camera camera;
	
	private Level level;
	
	private Entity player;
	private ArrayList<Entity> ai = new ArrayList<>();
	
	public GameManager() {
		
		int NUMBER_OF_AI = 0;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT2));
		setFocusable(true);
		requestFocus();
		
		camera = new Camera(WIDTH, HEIGHT);
		

		level = new Level(480, 90);
		
		InputComponent input = new KeyboardInput(); //new AIInput();
		PhysicsComponent physics = new LevelPhysics();
		GraphicsComponent graphics = new EntityGraphics();
		player = new Entity(47, 107, input, physics, graphics); //67, 107
		
		InputComponent aiinput = new AIInput();
		for (int i = 0; i < NUMBER_OF_AI; ++i)
			ai.add(new Entity(47, 107, aiinput, physics, graphics));
		for (Entity e : ai)
			e.reset();
	}
	
	// ready to display
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		
		FPSLimiter fpsLimiter = new FPSLimiter(60.0);
		
		
		
		//STUFF
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT2, 1);
		g = (Graphics2D) image.getGraphics();
		
		while (true) {
			fpsLimiter.begin();
			
			g.setColor(new Color(230, 230, 230));
			g.fillRect(0, 0, WIDTH, HEIGHT2);
			
			g.setFont(new Font("Ariel", Font.BOLD, 35));
			
			g.setColor(Color.BLACK);
			g.drawString("Welcome to Code-Zero, a HackNotts 2016 project!", 100, 200);
			g.drawString("This is a side-scrolling platformer that uses", 100, 300);
			g.drawString("W, A, S, D controlls and E to go back in time.", 100, 340);
			g.drawString("Try to reach the end (To the right) before your", 100, 400);
			g.drawString("sanity runs out. Time traveling reduces sanity", 100, 440);
			g.drawString("by a lot so don't use it too much!", 100, 480);
			
			g.drawString("Press SPACE to play!", 100, 650);
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, WIDTH, HEIGHT2, null);
			
			g2.dispose();
			
			InputController.getInstance().update();
			if (InputController.getInstance().anyKeyPressed())
				break;
			
			fpsLimiter.end();
		}
		
		int UPDATES_PER_SECOND = 125;
		int CURRENT_FRAME = 0;
		long START_TICKS = FPSLimiter.getTicks();
		
		player.reset();
		int count = 0;
		while (true) {
			fpsLimiter.begin();

			int REALTIME_FRAME = (int) Math.floor((FPSLimiter.getTicks() - START_TICKS) * (UPDATES_PER_SECOND / 1000.0f));
			int n = (REALTIME_FRAME - CURRENT_FRAME) - frameDelay;
			for (int i = 0; i < n; ++i) {
				handleInput(CURRENT_FRAME);
				
				update(CURRENT_FRAME);

				++CURRENT_FRAME;
			}
			
			draw();

			count++;
			fpsLimiter.end();
			if (fpsLimiter.isTick()) {
				System.out.println("FPS: " + count);
				count = 0;
			}
		}
	}
	
	//HANDLE INPUT TO APPLICATION
	private void handleInput(int frame) {
		for (Entity e : ai)
			if (!e.lockinput) e.getInput().update(frame, e, level);
		if (!player.lockinput) player.getInput().update(frame, player, level);
		InputController.getInstance().update();
	}
	
	//HANDLE UPDATING ALL OBJECTS
	private void update(int frame) {
		for (Entity e : ai)
			e.getPhysics().update(frame, e, level);
		player.getPhysics().update(frame, player, level);
		camera.setPosition(player.x + player.width / 2.0, player.y + player.height / 2.0);
	}
	
	//HANDLE DRAWING ALL OBJECTS
	private void draw() {
		//g.setColor(Color.BLACK);
		//g.fillRect(0,  0, WIDTH, HEIGHT2);
		level.draw(g, camera);
		
		for (Entity e : ai)
			e.getGraphics().update(e, g, camera);
		player.getGraphics().update(player, g, camera);
		drawGUI(g);
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT2, null);
		
		g2.dispose();
	}
	
	
	private void drawGUI(Graphics2D g){
		
		
		g.setFont(new Font("Ariel", Font.BOLD, 16));
		
		g.setColor(Color.GRAY);
		g.drawString("Santity:", 37, 22);
		g.setColor(Color.WHITE);
		g.drawString("Santity:", 35, 20);
		
		
		
	    g.setColor(new Color(0x757575));
	    g.fillRect(30,30, (int)player.maxSanitiy*2,20);
        g.setColor(new Color(0x6013AE));
        g.fillRect(30,30, (int)player.sanitiy*2,20);
        g.setColor(new Color(255,255,255,50));
        g.fillRect(30,30, (int)player.maxSanitiy*2,5);
        if(player.won) {
            g.setFont(new Font("Ariel",Font.BOLD,32));
            g.setColor(Color.GRAY);
            g.drawString("You won with " + (int)player.sanitiy + " sanity remaining!",WIDTH/2 - 248,HEIGHT/2 - 48);
            g.setColor(new Color(255,255,255));
            g.drawString("You won with " + (int)player.sanitiy + " sanity remaining!",WIDTH/2 - 250,HEIGHT/2 - 50);
        }
        if(player.lost) {
            g.setFont(new Font("Ariel",Font.BOLD,32));
            g.setColor(Color.GRAY);
            g.drawString("You lost!",WIDTH/2 - 68,HEIGHT/2 - 48);
            g.setColor(new Color(255,255,255));
            g.drawString("You lost!",WIDTH/2 - 70,HEIGHT/2 - 50);
        }
    }
	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public int getInputKey(char c) {
		switch (Character.toString(c).toLowerCase().charAt(0)) {
			case ' ':
			case 'w':
				return InputController.K1;
			case 'a':
				return InputController.K2;
			case 's':
				return InputController.K3;
			case 'd':
				return InputController.K4;
			case 'e':
				return InputController.K5;
			default:
				return -1;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = getInputKey(e.getKeyChar());
		if (key == -1) {
			return;
		} else {
			InputController.getInstance().pressKey(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = getInputKey(e.getKeyChar());
		if (key == -1) {
			return;
		} else {
			InputController.getInstance().releaseKey(key);
		}
	}
}
