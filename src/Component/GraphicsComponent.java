package Component;

import java.awt.Graphics2D;

import Entity.Entity;

public interface GraphicsComponent {
	public void update(Entity e, Graphics2D g);
	public void reset();
}
