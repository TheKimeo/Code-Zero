package Command;

import Entity.Entity;

public class JumpCommand implements Command {
private Entity e;
	
	public JumpCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		if (e.onFloor) {
			e.getGraphics().setFrames(e,e.isWalkingLeft ? e.leftJumpStartFrames : e.rightJumpStartFrames, 20);
			e.jumpTime = 1; //31
		}
	}
	
	public void undo() {
	}
}
