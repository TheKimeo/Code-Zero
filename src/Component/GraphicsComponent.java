package Component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Camera;
import Entity.Entity;

public abstract class GraphicsComponent {
	public abstract void update(Entity e, Graphics2D g, Camera c);
	public abstract void reset(Entity e);
	public void setFrames(Entity e, BufferedImage[] frames) {
		e.frames = frames;
		e.currentFrame = 0;
		e.count = 0;
		e.timesPlayed = 0;
		e.delay = 20;
		e.numFrames = frames.length;
	}
}
