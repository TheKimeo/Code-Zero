package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class AIInput implements InputComponent {
	public void update(int frame, InputController input, Entity player, Level level) {
		CommandStream cs = player.getCommandStream();
		
		
		cs.addCommand(frame, CommandStream.Preset.UP);
	}

	public void reset() {
		
	}
}
