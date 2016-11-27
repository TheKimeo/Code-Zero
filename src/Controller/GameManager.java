package Controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		for (int i = 0; i < 20; ++i)
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
			e.getInput().update(frame, e, level);
		player.getInput().update(frame, player, level);
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
		
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT2, null);
		
		g2.dispose();
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
