//starting point that spawns vehicles
public class StartingPoint implements TakesTurn {
	private Road next;
	private final int interval; //number of turns between spawning
	private int countdown;
	private Exit[] destinations; //list of vehicle destinations to choose from
	
	public StartingPoint(int interval, Exit[] destinations) {
		if(interval < 1) {
			this.interval = 3; //default interval = 3
		} else {
			this.interval = interval;
		}
		countdown = 1;
		this.destinations = destinations;
	}
	
	public void setRoad(Road road) {
		this.next = road;
	}

	@Override
	public void takeTurn() {
		if(--countdown <= 0) {
			spawnVehicle();
			countdown = interval;
		}
	}
	
	//spawn a random vehicle
	public void spawnVehicle() {
		int whichExit = (int) (Math.random() * destinations.length);
		double whichVehicle = Math.random(); //for vehicle probabilities
		
		Vehicle v = null;
		if(whichVehicle < 0.40) { //40% chance of car
			v = new Car(destinations[whichExit]);
		} else if(whichVehicle >= 0.40 && whichVehicle < 0.70) { //30% chance of bus
			v = new Bus(destinations[whichExit]);
		} else if(whichVehicle >= 0.70 && whichVehicle < 0.90) { //20% chance of ambulance
			boolean sirens = true; //50% chance of sirens being on/off
			if(Math.random()*2 < 1) {
				sirens = true;
			} else {
				sirens = false;
			}
			v = new Ambulance(destinations[whichExit], sirens);
		} else { //10% chance of bike
			v = new Bike(destinations[whichExit]);
		}
		next.addVehicle(v);
		Log.writeLog(v + " entered on " + next.getName() + ". It is heading for Exit " + destinations[whichExit] + ".");
	}
}
