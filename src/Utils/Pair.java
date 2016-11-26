package Utils;

public class Pair<O1, O2> {
	private O1 o1;
	private O2 o2;
	
	public Pair(O1 first, O2 second) {
		o1 = first;
		o2 = second;
	}
	
	public O1 first() {
		return o1;
	}
	
	public O2 second() {
		return o2;
	}
	
	public void setFirst(O1 first) {
		o1 = first;
	}
	
	public void setSecond(O2 second) {
		o2 = second;
	}
}
