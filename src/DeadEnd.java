//a dead end that can be added to an intersection
//this is a singleton - basically like null but I can call hasNext and getNext on it
public class DeadEnd implements Intersecting {
	private static DeadEnd singleton = null;
	
	private DeadEnd() {
	}
	
	//get the dead end singleton
	public static DeadEnd get() {
		if(singleton == null) {
			singleton = new DeadEnd();
		}
		return singleton;
	}

	@Override
	public Vehicle getNext() {
		return null;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public void addVehicle(Vehicle v) {		
	}

	@Override
	public Direction getDirection() {
		return null;
	}

	@Override
	public Intersection getIntersection() {
		return null;
	}

	@Override
	public String getName() {
		return "Dead End";
	}

}
