package Component;

import Controller.InputController;
import Entity.Entity;
import Level.Level;

public interface InputComponent {
	public void update(InputController input, Entity player, Level level);
	public void reset();
}
