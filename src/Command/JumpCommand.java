package Command;

import Entity.Entity;

public class JumpCommand implements Command {
private Entity e;
	
	public JumpCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		if (e.onFloor) {
			if (e.frames == e.leftWalkFrames || e.frames == e.rightWalkFrames)
				e.delay = 2;
			e.jumpTime = 1; //31
		}
	}
	
	public void undo() {
	}
}
