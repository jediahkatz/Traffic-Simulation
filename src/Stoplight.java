//gives a green light for either north/south or east/west
public class Stoplight {
	private int length; //number of turns between light changes
	private int countdown; //counts down the length
	private boolean northSouthGo; //true when the light is green for north/south, false when it is red
	
	public Stoplight(int length, boolean northSouthGo) {
		this.length = length;
		countdown = length;
		this.northSouthGo = northSouthGo;
	}
	
	//have the stoplight start a number of turns into the countdown
	public Stoplight(int length, boolean northSouthGo, int offset) {
		this(length, northSouthGo);
		if(offset > length - 1 || offset < 0) offset = 0; //default - no offset
		countdown = length - offset;
	}
	
	//decrement the countdown, change light when necessary
	//return true if light changed
	public boolean countdown() { 
		if(--countdown <= 0) {
			northSouthGo = !northSouthGo;
			countdown = length;
			return true;
		}
		return false;
	}
	
	public boolean northSouthGo() {
		return northSouthGo;
	}
	
	//number of turns until light changes
	public int turnsLeft() {
		return countdown;
	}
}
