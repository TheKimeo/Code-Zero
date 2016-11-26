package Entity;
import Command.CommandStream;
import Component.GraphicsComponent;
import Component.InputComponent;
import Component.PhysicsComponent;

public class Entity {
	private InputComponent input;
	private PhysicsComponent physics;
	private GraphicsComponent graphics;
	
	private CommandStream commandStream = new CommandStream();
	
	public Entity(InputComponent input, PhysicsComponent physics, GraphicsComponent graphics) {
		this.input = input;
		this.physics = physics;
		this.graphics = graphics;
	}
	
	public void reset() {
		
	}
	
	public CommandStream getCommandStream() {
		return this.commandStream;
	}
}
