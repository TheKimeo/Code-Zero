package Component;

import Controller.InputController;
import Entity.Entity;
import Level.Level;

public interface InputComponent {
	public void update(int frame, Entity e, Level level);
	public void reset();
}
