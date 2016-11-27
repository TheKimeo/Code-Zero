package Component;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class KeyboardInput implements InputComponent {
	public void update(int frame, Entity e, Level level) {
		if (e.warping)
			return;
		CommandStream cs = e.getCommandStream();
		InputController input = InputController.getInstance();
		
		if (input.isDown(InputController.K1)) {
			cs.addCommand(frame, CommandStream.JUMP);
		}
		if (input.isReleased(InputController.K1)) {
			cs.addCommand(frame,  CommandStream.STOP_JUMP);
		}	
		if (input.isDown(InputController.K2)) {
			cs.addCommand(frame, CommandStream.LEFT);
		}
		if (input.isDown(InputController.K3)) {
			cs.addCommand(frame, CommandStream.DOWN);
		}
		if (input.isDown(InputController.K4)) {
			cs.addCommand(frame, CommandStream.RIGHT);
		}
		if (input.isPressed(InputController.K5) && e.sanitiy >= 10) {
			e.sanitiy -= 10;
			cs.addCommand(frame, CommandStream.SPECIAL);
		}
	}

	public void reset() {
		
	}
}
