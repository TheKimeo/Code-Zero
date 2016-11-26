package Entity.Component;

import Entity.Entity;
import Level.Level;

public interface PhysicsComponent {
	public void update(int frame, Entity e, Level level);
	public void reset();
}
