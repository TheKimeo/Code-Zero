package Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Camera;
import Entity.Entity;

public class EntityGraphics implements GraphicsComponent {
	
	public void setFrames(Entity e, BufferedImage[] frames) {
		e.frames = frames;
		e.currentFrame = 0;
		e.count = 0;
		e.timesPlayed = 0;
		e.delay = 2;
		e.numFrames = frames.length;
	}
	
	public void setDelay(Entity e, int i) { e.delay = i; }
	public void setFrame(Entity e, int i) { e.currentFrame = i; }
	public void setNumFrames(Entity e, int i) { e.numFrames = i; }
	
	public void update(Entity e, Graphics2D g, Camera c) {
		
		if(e.delay == -1) return;
		
		e.count++;
		
		if(e.count == e.delay) {
			e.currentFrame++;
			e.count = 0;
		}
		if(e.currentFrame == e.numFrames) {
			e.currentFrame = 0;
			e.timesPlayed++;
		}
		
		g.setColor(new Color(0x00FF00));
		g.fillRect(e.boundingBox.x - (int) c.x, e.boundingBox.y - (int) c.y, e.boundingBox.width, e.boundingBox.height);
//		g.fillRect(10,10,15,15);
		/*g.drawImage(
				getImage(e),
				e.boundingBox.x + xmap - e.boundingBox.width / 2,
				e.boundingBox.y + ymap - e.boundingBox.height / 2,
				null
			); */
		
	}
	
	public int getFrame(Entity e) { return e.currentFrame; }
	public int getCount(Entity e) { return e.count; }
	public BufferedImage getImage(Entity e) { return e.frames[e.currentFrame]; }
	public boolean hasPlayedOnce(Entity e) { return e.timesPlayed > 0; }
	public boolean hasPlayed(Entity e, int i) { return e.timesPlayed == i; }
	
	public void reset() {
		
	}
	
}
