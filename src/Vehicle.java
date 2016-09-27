
public interface Vehicle {
	Exit getDestination();
	int getID();
	Intersecting getNextRoad();
	void setRoute(Stack<Intersecting> route);
	boolean hasRoute();
	boolean stopping(); //for buses
	boolean sirensOn(); //for ambulances
}
