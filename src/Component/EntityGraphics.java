package Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Camera;
import Entity.Entity;

public class EntityGraphics extends GraphicsComponent {
	

	
	public void setDelay(Entity e, int i) { e.delay = i; }
	public void setFrame(Entity e, int i) { e.currentFrame = i; }
	public void setNumFrames(Entity e, int i) { e.numFrames = i; }
	
	@SuppressWarnings("unused")
	public void update(Entity e, Graphics2D g, Camera c) {

		if(e.delay != -1) {
			e.count++;
			
			if (e.replay || e.currentFrame != e.numFrames - 1) {
				if(e.count >= e.delay) {
					e.currentFrame++;
					e.count = 0;
				}
				
				if(e.currentFrame >= e.numFrames) {
					e.currentFrame = 0;
					e.timesPlayed++;
				}
			}
		}



		if (false) {
			g.setColor(new Color(0x00FF00));
			g.fillRect((int) (e.x - c.x), (int) (e.y - c.y), (int) e.width, (int) e.height);
		}
//		g.fillRect(10,10,15,15);
        g.setColor(new Color(0xE500FF));
		g.drawString(e.toString(), (int)(e.x - c.x), (int)(e.y - c.y));
        g.drawImage(getImage(e), (int) (e.x - c.x - e.renderOffsetX), (int) (e.y - c.y - e.renderOffsetY), e.boundingBox.width, e.boundingBox.height, null);
		
	}
	
	public int getFrame(Entity e) { return e.currentFrame; }
	public int getCount(Entity e) { return e.count; }
	public BufferedImage getImage(Entity e) { return e.frames[e.currentFrame]; }
	public boolean hasPlayedOnce(Entity e) { return e.timesPlayed > 0; }
	public boolean hasPlayed(Entity e, int i) { return e.timesPlayed == i; }
	
	public void reset(Entity e) {
		e.getGraphics().setFrames(e, e.rightIdleFrames, 20);
	}

	
}
