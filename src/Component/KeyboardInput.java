package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class KeyboardInput implements InputComponent {
	public void update(InputController input, Entity player, Level level) {
		player.getCommandStream().addCommand(0, CommandStream.Preset.UP);
	}

	public void reset() {
		
	}
}
