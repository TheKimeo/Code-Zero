package Command;

import Entity.Entity;
import Utils.Pair;

public class TimeCommand implements Command {
	private Entity e;
	private int frame;
	
	public TimeCommand(Entity e, int frame) {
		this.e = e;
		this.frame = frame;
	}
	
	public void execute() {
		int destFrame = frame - (int) (125.0 * 3.0);
		if (destFrame - e.teleportFrame < 0) destFrame = e.teleportFrame;
		
		if (e.facing)
			e.getGraphics().setFrames(e, e.rightWarpFrames, 4);
		else
			e.getGraphics().setFrames(e, e.leftWarpFrames, 4);
		
		e.replay = false;
		Pair<Double, Double> pos = e.positions.get(destFrame);
		e.destx = pos.first();
		e.desty = pos.second();
		e.teleportFrame = frame;
		e.warping = true;
	}
	
	public void undo() {
		
	}
}
