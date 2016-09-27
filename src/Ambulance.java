//like cars, but when sirens are on it gets priority in the queue at an intersection
public class Ambulance implements Vehicle {
	private static int nAmb = 0;
	private Exit dest;
	private final int ID;
	private Stack<Intersecting> route = new Stack<Intersecting>();
	private boolean hasRoute = false;
	private boolean sirensOn;
	
	public Ambulance(Exit destination, boolean sirensOn) {
		dest = destination;
		nAmb++;
		ID = nAmb;
		this.sirensOn = sirensOn;
	}

	@Override
	public Exit getDestination() {
		return dest;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public Intersecting getNextRoad() {
		return route.pop();
	}

	@Override
	public void setRoute(Stack<Intersecting> route) {
		this.route = route;
		hasRoute = true;
	}

	@Override
	public boolean hasRoute() {
		return hasRoute;
	}
	
	public String toString() {
		return "Ambulance " + ID + (sirensOn ? " (Sirens On)" : " (Sirens Off)");
	}
	
	public boolean stopping() {
		return false;
	}

	@Override
	public boolean sirensOn() {
		return sirensOn;
	}

}
