package Command;

import Entity.Entity;

public class LeftCommand implements Command {
	private Entity e;
	
	public LeftCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
//		e.dx -= 0.2304;
		e.dx -= 0.15;
		if (e.frames != e.leftWalkFrames) {
			e.getGraphics().setFrames(e, e.leftWalkFrames, 7);
		}
		e.isWalkingLeft = true;
	}
	
	public void undo() {
	}
}
