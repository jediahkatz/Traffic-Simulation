//like cars, but they stop for one turn during a green light to pick up passengers at intersections
public class Bus implements Vehicle {
	private static int nBuses = 0;
	private Exit dest;
	private final int ID;
	private Stack<Intersecting> route = new Stack<Intersecting>();
	private boolean hasRoute = false;
	private boolean stopping = true; //bus stop
	
	public Bus(Exit destination) {
		dest = destination;
		nBuses++;
		ID = nBuses;
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
		return "Bus " + ID;
	}
	
	public boolean stopping() { //if the bus is stopping at the intersection
		boolean temp = stopping;
		stopping = !stopping;
		return temp;
	}

	@Override
	public boolean sirensOn() {
		return false;
	}

}
