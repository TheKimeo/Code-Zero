package Command;

import Entity.Entity;

public class RightCommand implements Command {
	private Entity e;
	
	public RightCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.dx += 0.2304;
	}
	
	public void undo() {
	}
}