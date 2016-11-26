package Controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Component.AIInput;
import Component.EntityGraphics;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.LevelPhysics;
import Component.PhysicsComponent;
import Entity.Entity;
import Level.Level;

@SuppressWarnings("serial")
public class GameManager extends JPanel implements Runnable, KeyListener {

	private static long GAME_START_TIME = System.currentTimeMillis();
	
	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int HEIGHT2 = HEIGHT + 16;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int TARGET_FPS = 60;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;
	
	private Level level;
	
	private Entity player;
	
	public GameManager() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT2));
		setFocusable(true);
		requestFocus();
		
		
		
		level = new Level(12, 9);
		
		InputComponent input = new AIInput();
		PhysicsComponent physics = new LevelPhysics();
		GraphicsComponent graphics = new EntityGraphics();
		player = new Entity(input, physics, graphics);
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
		//STUFF
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT2, 1);
		g = (Graphics2D) image.getGraphics();
		
		//Constants for semi-fixed timestep
		int UPDATES_PER_SECOND = 125;
		int CURRENT_FRAME = 0;
		long START_TICKS = getTicks();
		
		//Run until the state switches or program is exited
		while (true) {
			//Start frame timer
			//m_fpsLimiter.beginFrame();

			//Update loop
			int REALTIME_FRAME = (int) Math.floor((getTicks() - START_TICKS) * (UPDATES_PER_SECOND / 1000.0f));
			for (int i = 0, n = REALTIME_FRAME - CURRENT_FRAME; i < n; ++i) {
				//Process Input to window
				//switch (m_input.update()) {
				//	case 1: //'Big red x' close button pressed
				//		exitProgram = true;
				//		break;
				//}

				//Handle input for state
				handleInput(CURRENT_FRAME);

				//Update physics for state
				update(CURRENT_FRAME);

				//Increase current frame
				++CURRENT_FRAME;
			}

			//Draw state
			draw();

			//Limit & calculate fps then draw fps
			//float fps = m_fpsLimiter.endFrame();
			//if (m_fpsLimiter.isTick())
			//	m_fps = (int) floor(fps);
		}
	}
	
	//HANDLE INPUT TO APPLICATION
	private void handleInput(int frame) {
		//player.getInput().update(frame, player, level);
	}
	
	//HANDLE UPDATING ALL OBJECTS
	private void update(int frame) {
		//player.getPhysics().update();
	}
	
	//HANDLE DRAWING ALL OBJECTS
	private void draw() {
		level.draw(g);
		player.getGraphics().update(player, g);

		//Draw to screen
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT2, null);
		g2.dispose();
	}
	
	public static long getTicks() {
		return System.currentTimeMillis() - GAME_START_TIME;
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public int getInputKey(char c) {
		switch (c) {
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
