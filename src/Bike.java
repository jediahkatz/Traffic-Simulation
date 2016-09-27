//like cars
public class Bike implements Vehicle {
	private static int nBikes = 0;
	private Exit dest;
	private final int ID;
	private Stack<Intersecting> route = new Stack<Intersecting>();
	private boolean hasRoute = false;
	
	public Bike(Exit destination) {
		dest = destination;
		nBikes++;
		ID = nBikes;
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
		return "Bike " + ID;
	}

	@Override
	public boolean stopping() {
		return false;
	}

	@Override
	public boolean sirensOn() {
		return false;
	}

}
