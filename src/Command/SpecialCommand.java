package Command;

import Controller.GameManager;
import Entity.Entity;

public class SpecialCommand implements Command {
	private Entity e;
	
	public SpecialCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		GameManager.frameDelay++;
	}
	
	public void undo() {
	 
	}
}
