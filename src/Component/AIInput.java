package Entity.Component;

import Entity.Command.CommandStream;
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
