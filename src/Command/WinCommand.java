package Command;

import Entity.Entity;

public class WinCommand implements Command {
	private Entity e;
	
	public WinCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		e.getGraphics().setFrames(e, e.rightJumpStartFrames, 5);
		e.lockinput = true;
		e.won = true;//yay
	}
	
	public void undo() {
		
	}

}
