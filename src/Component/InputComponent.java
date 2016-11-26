package Component;

import Controller.InputController;
import Entity.Entity;
import Level.Level;

public interface InputComponent {
	public void update(int frame, Entity player, Level level);
	public void reset();
}
