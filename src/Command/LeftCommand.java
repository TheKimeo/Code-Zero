package Command;

import Entity.Entity;

public class LeftCommand implements Command {
	private Entity e;
	
	public LeftCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.dx -= 0.2304;
	}
	
	public void undo() {
	}
}
