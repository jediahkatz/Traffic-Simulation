
public class Car implements Vehicle {
	private static int nCars = 0;
	private final int ID;
	private Stack<Intersecting> route = new Stack<Intersecting>();
	private Exit destination;
	private boolean hasRoute = false;
	
	Car(Exit destination) {
		this.destination = destination;
		nCars++;
		ID = nCars;
	}

	@Override
	public Exit getDestination() {
		return destination;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public Intersecting getNextRoad() {
		return route.pop();
	}
	
	public void setRoute(Stack<Intersecting> r) {
		route = r;
		hasRoute = true;
	}
	
	public boolean hasRoute() {
		return hasRoute;
	}
	
	public String toString() {
		return "Car " + ID;
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
