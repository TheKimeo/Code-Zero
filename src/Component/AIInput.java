package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class AIInput implements InputComponent {
	public void update(InputController input, Entity player, Level level) {
		CommandStream cs = player.getCommandStream();
		
		
		cs.addCommand(0, CommandStream.Preset.UP);
	}

	public void reset() {
		
	}
}
