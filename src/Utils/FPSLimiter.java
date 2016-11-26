package Utils;

public class FPSLimiter {
	

	private static long GAME_START_TIME = System.currentTimeMillis();
	
	private double targetFPS;
	private long startTime;
	private long frameTime;
	private long lastFrameTime;
	private long lastTick;
	
	public static long getTicks() {
		return System.currentTimeMillis() - GAME_START_TIME;
	}
	
	public FPSLimiter(double targetFPS) {
		this.targetFPS = targetFPS;
		lastTick = getTicks();
	}
	
	public void begin() {
		this.startTime = getTicks();
	}
	
	public void end() {
		if (targetFPS > 1.0f) {
			long delayNeeded = (long) (1000.0 / targetFPS + (double) (startTime - getTicks()));
			if (delayNeeded > 0) {
				try {
					Thread.sleep(delayNeeded);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		//Store how long the last frame was
		frameTime = getTicks() - lastFrameTime;
		lastFrameTime = getTicks();
	}
	
	public boolean isTick() {
		long thisTick = getTicks();

		if (thisTick - lastTick < 1000)
			return false;

		lastTick = thisTick;
		return true;
	}
}
