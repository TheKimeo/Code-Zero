package Entity.Component;

import java.awt.Graphics2D;

import Controller.Camera;
import Entity.Entity;

public interface GraphicsComponent {
	public void update(Entity e, Graphics2D g, Camera c);
	public void reset();
}
