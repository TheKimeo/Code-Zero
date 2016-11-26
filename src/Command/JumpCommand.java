package Command;

import Entity.Entity;

public class JumpCommand implements Command {
private Entity e;
	
	public JumpCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		if (e.onFloor) {
			e.jumpTime = 31;
		}
	}
	
	public void undo() {
	}
}
