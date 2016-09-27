//Cars can move on the north/south or west/east roads at the same time
//north[0] is the north road coming to the intersection,
//and north[1] is the north road going from the intersection, and so on
public class StoplightIntersection implements Intersection, TakesTurn {
	// the roads are all dead ends until otherwise specified by setNorth, etc.
	private Intersecting north = DeadEnd.get();
	private Intersecting south = DeadEnd.get();
	private Intersecting east = DeadEnd.get();
	private Intersecting west = DeadEnd.get();
	private Intersecting[] roads = { north, south, east, west };

	private Queue<Vehicle> northQueue = new Queue<Vehicle>();
	private Queue<Vehicle> southQueue = new Queue<Vehicle>();
	private Queue<Vehicle> eastQueue = new Queue<Vehicle>();
	private Queue<Vehicle> westQueue = new Queue<Vehicle>();

	private Stoplight stoplight;
	
	private boolean visited; //used by RouteCalculator to prevent going in circles
	private String name;

	public StoplightIntersection(String name) {
		stoplight = null;
		this.name = name;
	}

	public StoplightIntersection(String name, Stoplight s) {
		stoplight = s;
		this.name = name;
	}

	public void setStoplight(Stoplight s) {
		stoplight = s;
	}

	// set the north, south, east, or west road
	public void setRoad(Intersecting i) {
		switch (i.getDirection()) {
		case NORTH:
			north = i;
			roads[0] = i;
			break;
		case SOUTH:
			south = i;
			roads[1] = i;
			break;
		case EAST:
			east = i;
			roads[2] = i;
			break;
		case WEST:
			west = i;
			roads[3] = i;
			break;
		}
	}

	// add a vehicle to the north, south, east, or west queue
	// if it's a siren-ing ambulance, it can skip the line
	public void addVehicle(Vehicle v, Direction d) {
		boolean skip = v.sirensOn();
		
		if(skip) Log.writeLog("The other vehicles let " + v + " take priority at " + this);
		
		switch (d) {
		case NORTH:
			if(skip) northQueue.prepend(v);
			else northQueue.enqueue(v);
			break;
		case SOUTH:
			if(skip) southQueue.prepend(v);
			else southQueue.enqueue(v);
			break;
		case EAST:
			if(skip) eastQueue.prepend(v);
			else eastQueue.enqueue(v);
			break;
		case WEST:
			if(skip) westQueue.prepend(v);
			else westQueue.enqueue(v);
			break;
		}
	}

	// each turn, one vehicle from each side can go
	// can only go if they have the light
	// also, update stoplight countdown
	public void takeTurn() {
		if (stoplight.northSouthGo()) {
			Vehicle v;
			Intersecting next;
			if (!northQueue.isEmpty()) {
				v = northQueue.dequeue();
				next = v.getNextRoad();
				
				boolean s = v.stopping();
				
				if(s) {
					Log.writeLog(v + " is stopping to pick up passengers at " + this + ".");
				}
				
				for (Intersecting r : roads) {
					if (r != north && r == next && !s) { //check if the bus is stopping
						r.addVehicle(v);
					}
				}
			}

			if (!southQueue.isEmpty()) {
				v = southQueue.dequeue();
				next = v.getNextRoad();
				
				boolean s = v.stopping();
				if(s) {
					Log.writeLog(v + " is stopping to pick up passengers at " + this + ".");
				}
				
				for (Intersecting r : roads) {
					if (r != south && r == next && !s) { //check if the bus is stopping
						r.addVehicle(v);
					}
				}
			}
		} else {
			Vehicle v;
			Intersecting next;
			if (!eastQueue.isEmpty()) {
				v = eastQueue.dequeue();
				next = v.getNextRoad();
				boolean s = v.stopping();
				if(s) {
					Log.writeLog(v + " is stopping to pick up passengers at " + this + ".");
				}
				for (Intersecting r : roads) {
					if (r != east && r == next && !s) { //check if the bus is stopping
						r.addVehicle(v);
					}
				}
			}

			if (!westQueue.isEmpty()) {
				v = westQueue.dequeue();
				next = v.getNextRoad();
				boolean s = v.stopping();
				if(s) {
					Log.writeLog(v + " is stopping to pick up passengers at " + this + ".");
				}
				for (Intersecting r : roads) {
					if (r != west && r == next && !s) { //check if the bus is stopping
						r.addVehicle(v);
					}
				}
			}
		} //end of else block
		
		boolean changed = stoplight.countdown();
		if(changed) { 
			Log.writeLog("The stoplight at Intersection " + name + " changed. It is now allowing " + (stoplight.northSouthGo() ? "north/south traffic." : "east/west traffic."));
		}
	}

	@Override
	public Intersecting[] getRoads() {
		return roads;
	}

	public Queue<Vehicle> getEastQueue() {
		return eastQueue;
	}
	
	public void setVisited(boolean b) {
		visited = b;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public String toString() {
		return "Intersection " + name;
	}
}
