package Command;

import Entity.Entity;

public class RightCommand implements Command {
	private Entity e;
	
	public RightCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.dx += 0.15;
//		e.dx += 0.2304;
		if (e.frames != e.rightWalkFrames) {
			e.getGraphics().setFrames(e,e.rightWalkFrames, 7);
		}
		e.isWalkingRight = true;
	}
	
	public void undo() {
	}
}
