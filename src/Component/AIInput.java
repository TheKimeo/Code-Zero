package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class AIInput implements InputComponent {
	public void update(int frame, Entity e, Level level) {
		CommandStream cs = e.getCommandStream();
		
		
		cs.addCommand(frame, CommandStream.RIGHT);
	}

	public void reset() {
		
	}
}
