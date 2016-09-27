
public interface Intersection {
	Intersecting[] getRoads();
	void addVehicle(Vehicle v, Direction d);
	void setRoad(Intersecting road);
	void setVisited(boolean b); //for use by RouteCalculator
	boolean getVisited();
}
