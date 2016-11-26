package Entity.Command;

import Entity.Entity;

public class StopJumpCommand implements Command {
	private Entity e;
	
	public StopJumpCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.jumpTime = 0;
	}
	
	public void undo() {
	}
}
