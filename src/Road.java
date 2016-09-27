//a one-way, one-lane road that exists between two intersections or an exit & an intersection
public class Road implements Intersecting, TakesTurn {
	private Intersection next; // the intersection that this vehicle points to
	public final int length; // the number of turns for a car to get to the end
								// of a road
	private CircularArray<Vehicle> vehicles;
	private Direction direction; // whether the road intersects from the north, south,
							// east, or west
	
	private String name;

	public Road(int length, Direction d, String name) {
		this.length = length;
		direction = d;
		this.name = name;
		vehicles = new CircularArray<Vehicle>(length);
	}

	// the intersection that this road feeds into
	public void setIntersection(Intersection i) {
		next = i;
	}

	public Intersection getIntersection() {
		return next;
	}

	public void addVehicle(Vehicle v) {
		//add the vehicle to the road
		//and give it directions to the correct exit if it has none
		if(!v.hasRoute()) {
			v.setRoute(getRoute(v.getDestination()));
		} else {
			Log.writeLog(v + " is now travelling on " + getName() + ".");
		}
		vehicles.enqueue(v);
	}

	@Override
	public Vehicle getNext() {
		Object o = vehicles.dequeueAndMoveUp();
		return (o == null) ? null : ((Vehicle) o);
	}

	@Override
	public boolean hasNext() {
		return vehicles.hasNext();
	}

	//move all the Vehicles up one place in the queue
	//if a vehicle is at the top of the queue move it to the intersection
	public void takeTurn() {
		if (hasNext()) {
			Vehicle v = getNext();
			next.addVehicle(v, direction);
			Log.writeLog(v + " reached " + next + ".");
		} else {
			getNext();
		}
	}

	// calculate a route from this road to the specified destination
	public Stack<Intersecting> getRoute(Exit destination) {
		return RouteCalculator.getRoute(this, destination);
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public String getName() {
		return name + " Street";
	}
	
	public Object[] getVehicles() {
		return vehicles.getArray();
	}
	
	public String toString() {
		return getName();
	}
}
