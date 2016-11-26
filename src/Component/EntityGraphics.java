package Component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Entity;

public class EntityGraphics implements GraphicsComponent {
	
	public void setFrames(Entity e, BufferedImage[] frames) {
		frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}
	
	public void setDelay(int i) { delay = i; }
	public void setFrame(int i) { currentFrame = i; }
	public void setNumFrames(int i) { numFrames = i; }
	
	public void update(Entity e, Graphics2D g) {
		
		if(delay == -1) return;
		
		count++;
		
		if(count == delay) {
			currentFrame++;
			count = 0;
		}
		if(currentFrame == numFrames) {
			currentFrame = 0;
			timesPlayed++;
		}
		
		g.drawImage(
				getImage(),
				x + xmap - width / 2,
				y + ymap - height / 2,
				null
			);
		
	}
	
	public int getFrame() { return currentFrame; }
	public int getCount() { return count; }
	public BufferedImage getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() { return timesPlayed > 0; }
	public boolean hasPlayed(int i) { return timesPlayed == i; }
	
	public void reset() {
		
	}
	
}
