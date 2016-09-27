//contains two single Roads going opposite directions
//very helpful for organization & reducing clutter
//it does not implement Intersecting because it is
//functionally not a road itself - just a container
public class DoubleRoad implements TakesTurn {
	private boolean eastWest; //true if roads run east & west; false if north & south
	private Road northOrEast, southOrWest; //the two roads
	public final int length; //number of turns for a car to traverse the road
	private String name;
	
	//directly set eastWest boolean
	public DoubleRoad(int length, boolean eastWest, String name) {
		this.length = length;
		this.name = name;
		this.eastWest = eastWest;
		makeRoads();
	}
	
	//automatically set eastWest based on direction
	public DoubleRoad(int length, Direction d, String name) {
		if(d == Direction.EAST || d == Direction.WEST) {
			eastWest = true;
		} else {
			eastWest = false;
		}
		this.length = length;
		this.name = name;
		makeRoads();
	}
	
	private void makeRoads() {
		if(eastWest) {
			northOrEast = new Road(length, Direction.EAST, name);
			southOrWest = new Road(length, Direction.WEST, name);
		} else {
			northOrEast = new Road(length, Direction.NORTH, name);
			southOrWest = new Road(length, Direction.SOUTH, name);
		}
	}
	
	//make sure you put intersections that are north or east of this road in the first parameter
	//and intersections that are south or west of this road in the second parameter
	public void setIntersections(Intersection northOrEast, Intersection southOrWest) {
		this.northOrEast.setIntersection(northOrEast);
		this.southOrWest.setIntersection(southOrWest);
		
		northOrEast.setRoad(this.southOrWest);
		southOrWest.setRoad(this.northOrEast);
	}
	
	public void takeTurn() {
		northOrEast.takeTurn();
		southOrWest.takeTurn();
	}
	
	public Object[][] getVehicles() {
		Object[] arr1 = northOrEast.getVehicles();
		Object[] arr2 = southOrWest.getVehicles();
		Object[][] combined = {arr1, arr2};
		return combined;
	}
	
	public Road getEastOrNorth() {
		return northOrEast;
	}
	
	public Road getWestOrSouth() {
		return southOrWest;
	}
}
