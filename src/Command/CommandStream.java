package Command;

import java.util.ArrayList;
import java.util.Comparator;

import Entity.Entity;
import Utils.Pair;

public class CommandStream {

	public static int JUMP = 0x1;
	public static int STOP_JUMP = 0x2;
	public static int DOWN = 0x4;
	public static int LEFT = 0x8;
	public static int RIGHT = 0x10;
	public static int SPECIAL = 0x20;
	public static int DEATH = 0x40;
	
	private ArrayList<Pair<Integer, Integer>> commandList = new ArrayList<>();
	private boolean isSorted = true;
	
	private Entity e;
	
	public CommandStream(Entity e) {
		this.e = e;
	}
	
	public void addCommand(int frame, int moveCommand) {
		int index = getIndex(frame);
		if (index == -1) {
			commandList.add(new Pair<Integer, Integer>(frame, moveCommand));
			isSorted = false;
		} else {
			Pair<Integer, Integer> c = commandList.get(index);
			c.setSecond(c.second() | moveCommand);
		}
	}
	
	public ArrayList<Command> getCommands(int frame) {
		int index = getIndex(frame);
		if (index == -1) {
			return new ArrayList<>();
		} else {
			return bitwiseReverse(commandList.get(index).second(), frame);
		}
	}
	
	@SuppressWarnings("unchecked")
	private int getIndex(int frame) {
		//Sort list
		sortList();
		
		//Binary Search
		int current;
		int average, min = 0, max = commandList.size() - 1;
		while (min <= max) {
			average = (int) Math.floor((min + max) / 2.0f);
			current = commandList.get(average).first();
			if (current == frame)
				return average;
			else if (current > frame)
				max = average - 1;
			else
				min = average + 1;
		}
		return -1;
	}
	
	public ArrayList<Command> bitwiseReverse(int i, int frame) {
		ArrayList<Command> ret = new ArrayList<>();
		if ((i & LEFT) == LEFT) {
			ret.add(new LeftCommand(e));
		}
		if ((i & RIGHT) == RIGHT) {
			ret.add(new RightCommand(e));
		}
		if ((i & JUMP) == JUMP) {
			ret.add(new JumpCommand(e));
		}
		if ((i & STOP_JUMP) == STOP_JUMP) {
			ret.add(new StopJumpCommand(e));
		}
		if ((i & SPECIAL) == SPECIAL) {
			ret.add(new TimeCommand(e, frame));
		}
		if ((i & DEATH) == DEATH) {
			ret.add(new DeathCommand(e));
		}
		return ret;
	}
	
	public void sortList() {
		if (isSorted) {
			return;
		}
		
		commandList.sort(new Comparator<Pair<Integer, Integer>>() {
			@Override
			public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
				return o1.first() - o2.first();
			}
		});
		
		isSorted = true;
	}
}
