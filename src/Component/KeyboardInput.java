package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class KeyboardInput implements InputComponent {
	public void update(int frame, Entity e, Level level) {
		CommandStream cs = e.getCommandStream();
		InputController input = InputController.getInstance();
		
		if (input.isDown(InputController.K1)) {
			cs.addCommand(frame, CommandStream.Preset.UP);
		}
		if (input.isDown(InputController.K2)) {
			cs.addCommand(frame, CommandStream.Preset.LEFT);
		}
		if (input.isDown(InputController.K3)) {
			cs.addCommand(frame, CommandStream.Preset.DOWN);
		}
		if (input.isDown(InputController.K4)) {
			cs.addCommand(frame, CommandStream.Preset.RIGHT);
		}
		if (input.isDown(InputController.K5)) {
			cs.addCommand(frame, CommandStream.Preset.SPECIAL);
		}
	}

	public void reset() {
		
	}
}
