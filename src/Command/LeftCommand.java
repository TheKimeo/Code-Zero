package Command;

import Entity.Entity;

public class LeftCommand implements Command {
	private Entity e;
	
	public LeftCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.dx -= 0.2304;
		if (e.frames != e.leftIdleFrames) {
			e.getGraphics().setFrames(e, e.leftIdleFrames);
		}
		e.isWalkingLeft = true;
	}
	
	public void undo() {
	}
}
