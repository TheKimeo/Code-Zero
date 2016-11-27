package Command;

import Entity.Entity;
import Utils.Pair;

public class DeathCommand implements Command {
	private Entity e;
	private int frame;
	
	public DeathCommand(Entity e, int frame) {
		this.e = e;
		this.frame = frame;
	}
	
	public void execute() {
		int destFrame = frame - (int) (125.0 * 3.0);
		if (destFrame - e.teleportFrame < 0) destFrame = e.teleportFrame;
		Pair<Double, Double> pos = e.positions.get(destFrame);
		e.x = pos.first();
		e.y = pos.second();
		e.dx = 0.0;
		e.dy = 0.0;
		e.jumpTime = 0;
		e.teleportFrame = frame;
	}
	
	public void undo() {
		
	}
}
