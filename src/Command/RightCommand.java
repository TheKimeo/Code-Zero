package Command;

import Entity.Entity;

public class RightCommand implements Command {
	private Entity e;
	
	public RightCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.dx += 0.15;
		if (e.onFloor && e.delay != 7)
			e.delay = 7;
		if (e.frames != e.rightWalkFrames) {
			e.getGraphics().setFrames(e,e.rightWalkFrames, 7);
			if (!e.onFloor)
				e.delay = 2;
		}
		e.facing = true;
		e.isWalkingRight = true;
	}
	
	public void undo() {
	}
}
