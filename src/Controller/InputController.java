package Controller;

public class InputController {
	private static InputController instance = null;
	
	public final int NUM_KEYS = 8;
	public boolean keyState[] = new boolean[NUM_KEYS];
	public boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public final static int K1 = 0; //w
	public final static int K2 = 1; //a
	public final static int K3 = 2; //s
	public final static int K4 = 3; //d
	public final static int K5 = 4; //e
	public final static int K6 = 5;
	public final static int K7 = 6;
	public final static int K8 = 7;
	
	protected InputController() {}
	
	public static InputController getInstance() {
		if (instance == null)
			instance = new InputController();
		return instance;
	}
	
	public void update() {
		for (int i = 0; i < NUM_KEYS; ++i) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public boolean isDown(int key) {
		return keyState[key];
	}
	
	public boolean isPressed(int key) {
		return (keyState[key] && !prevKeyState[key]);
	}
	
	public boolean isReleased(int key) {
		return (!keyState[key] && prevKeyState[key]);
	}
	
	public void pressKey(int key) {
		keyState[key] = true;
	}
	
	public void releaseKey(int key) {
		keyState[key] = false;
	}
	
	public boolean anyKeyPressed() {
		for (int i = 0; i < NUM_KEYS; ++i)
			if (keyState[i])
				return true;
		return false;
	}
}
