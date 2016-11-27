package Component;

import java.util.concurrent.ThreadLocalRandom;

import Command.CommandStream;
import Controller.InputController;
import Entity.Entity;
import Level.Level;

public class AIInput implements InputComponent {
	public void update(int frame, Entity e, Level level) {
		CommandStream cs = e.getCommandStream();
		
		if (frame % 10 <= 1)
			cs.addCommand(frame, CommandStream.JUMP);
		
		///if (frame % 100 <= 10)
		//	cs.addCommand(frame, CommandStream.SPECIAL);
		
		if (ThreadLocalRandom.current().nextInt(0, 100) == 0)
			e.directionAI = !e.directionAI;
		
		if (e.directionAI)
			cs.addCommand(frame, CommandStream.RIGHT);
		else
			cs.addCommand(frame, CommandStream.LEFT);
	}

	public void reset() {
		
	}
}
