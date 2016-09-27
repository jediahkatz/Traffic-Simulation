//a final destination where a car leaves our road network
public class Exit implements Intersection {
	//not an intersection, but acts as one as far as roads are concerned
	private String name;
	
	public Exit(String name) {
		this.name = name;
	}

	@Override
	public Intersecting[] getRoads() {
		return null;
	}

	@Override
	public void addVehicle(Vehicle v, Direction d) {
		Log.writeLog(v + " exited at exit " + name + "!");
	}

	@Override
	public void setRoad(Intersecting road) {		
	}
	
	public boolean getVisited() {
		return false;
	}
	
	public void setVisited(boolean b) {
	}
	
	public String toString() {
		return name;
	}
}
