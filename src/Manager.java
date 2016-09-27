import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Manager {
	public static StoplightIntersection[] intersections;
	
	public static void main(String[] args) {
		TakesTurn[] agents = setupTown();
		int nTurns = nTurns();
		for(int i=0; i<nTurns; i++) {
			for(TakesTurn a : agents) {
				a.takeTurn();
			}
			Log.printLog();
			Log.clearLog();
			System.out.println("------------------------------");
		}
	}
	
	public static TakesTurn[] setupTown() {
		TakesTurn[] turnTakers = new TakesTurn[8 + 7 + 8 + 2];
		
		Road grove = new Road(7, Direction.WEST, "Grove");
		Road yew = new Road(4, Direction.WEST, "Yew");
		Road pew = new Road(4, Direction.WEST, "Pew");
		Road quarry = new Road(6, Direction.SOUTH, "Quarry");
		Road york = new Road(5, Direction.EAST, "York");
		Road downey = new Road(5, Direction.EAST, "Downey");
		Road church = new Road(6, Direction.EAST, "Church");
		Road park = new Road(5, Direction.WEST, "Park");
		Road[] roads = {grove, yew, pew, quarry, york, downey, church, park};
		
		DoubleRoad cook = new DoubleRoad(3, Direction.NORTH, "Cook");
		DoubleRoad bit = new DoubleRoad(3, Direction.EAST, "Bit");
		DoubleRoad harlem = new DoubleRoad(5, Direction.NORTH, "Harlem");
		DoubleRoad market = new DoubleRoad(6, Direction.NORTH, "Market");
		DoubleRoad peach = new DoubleRoad(6, Direction.NORTH, "Peach");
		DoubleRoad bay = new DoubleRoad(4, Direction.EAST, "Bay");
		DoubleRoad main = new DoubleRoad(5, Direction.EAST, "Main");
		DoubleRoad[] doubleRoads = {cook, bit, harlem, market, peach, bay, main};
		
		Exit A = new Exit("A"), B = new Exit("B"), C = new Exit("C"), D = new Exit("D");
		Exit[] exits = {A,B,C,D};
		
		StartingPoint zero = new StartingPoint(2, exits);
		StartingPoint one = new StartingPoint(2, exits);
		StartingPoint[] spawns = {zero, one};
		
		StoplightIntersection alpha = new StoplightIntersection("Alpha", new Stoplight(3, true, 1));
		StoplightIntersection beta = new StoplightIntersection("Beta", new Stoplight(3, true));
		StoplightIntersection gamma = new StoplightIntersection("Gamma", new Stoplight(3, false, 1));
		StoplightIntersection omega = new StoplightIntersection("Omega", new Stoplight(3, false, 2));
		StoplightIntersection sigma = new StoplightIntersection("Sigma", new Stoplight(3, true, 2));
		StoplightIntersection kappa = new StoplightIntersection("Kappa", new Stoplight(3, true));
		StoplightIntersection delta = new StoplightIntersection("Delta", new Stoplight(3, false));
		StoplightIntersection mu = new StoplightIntersection("Mu", new Stoplight(3, false));
		StoplightIntersection[] intersections = {alpha, beta, gamma, omega, sigma, kappa, delta, mu};
		
		york.setIntersection(alpha);
		pew.setIntersection(B);
		grove.setIntersection(B);
		quarry.setIntersection(alpha);
		park.setIntersection(A);
		downey.setIntersection(mu);
		church.setIntersection(D);
		yew.setIntersection(omega);
		
		cook.setIntersections(kappa, sigma);
		bit.setIntersections(kappa, gamma);
		harlem.setIntersections(C, omega);
		main.setIntersections(omega, sigma);
		market.setIntersections(omega, mu);
		peach.setIntersections(alpha, beta);
		bay.setIntersections(beta, delta);
		
		sigma.setRoad(pew);
		sigma.setRoad(quarry);
		kappa.setRoad(grove);
		mu.setRoad(church);
		alpha.setRoad(downey);
		beta.setRoad(park);
		
		zero.setRoad(york);
		one.setRoad(yew);
		
		for(int i=0; i<2; i++) turnTakers[i] = spawns[i];
		for(int i=0; i<8; i++) turnTakers[i+2] = intersections[i];
		for(int i=0; i<8; i++) turnTakers[i+10] = roads[i];
		for(int i=0; i<7; i++) turnTakers[i+18] = doubleRoads[i];
		
		Manager.intersections = intersections;
		
		return turnTakers;
	}
	
	public static int nTurns() {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		System.out.println("How many turns would you like to run?");
		
		boolean goodNumber = false;
		int nTurns = 0;
		String response = "!";
		
		while (!goodNumber) {
			try {
				response = reader.readLine();
			} catch (IOException e) {
				// terminate the program in the case of an IOException
				System.out.println("Sorry, an error occurred. Please re-run the program.");
				System.exit(0);
			}
			
			try {
				nTurns = Integer.parseInt(response);
				if(nTurns < 1) throw new NumberFormatException();
				goodNumber = true;
			} catch(NumberFormatException e) {
				System.out.println("Sorry, that was an invalid value. Please input a positive integer.");
			}
		} // end of while block
		
		return nTurns;
	}
}
