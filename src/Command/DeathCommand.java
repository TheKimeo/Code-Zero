package Command;

import Entity.Entity;
import Utils.Pair;

public class DeathCommand implements Command {
	private Entity e;
	private int frame;
	
	public DeathCommand(Entity e) {
		this.e = e;
	}
	
	public void execute() {
		if (e.facing)
			e.getGraphics().setFrames(e, e.rightDeathFrames, 6);
		else
			e.getGraphics().setFrames(e, e.leftDeathFrames, 6);
		e.replay = false;
		e.lockinput = true;
	}
	
	public void undo() {
		
	}
}
