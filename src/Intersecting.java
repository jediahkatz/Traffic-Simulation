//either a road or a dead end - can be added to an intersection
public interface Intersecting {
	Vehicle getNext();
	boolean hasNext();
	void addVehicle(Vehicle v);
	Direction getDirection();
	Intersection getIntersection();
	String getName();
}
