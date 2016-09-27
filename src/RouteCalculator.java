//uninstantiable class that calculates routes from a road to an exit
public class RouteCalculator {
	private RouteCalculator() {
	}
	
	// calculate a route from this road to the specified destination
	public static Stack<Intersecting> getRoute(Intersecting road, Exit destination) {
		Stack<Intersecting> route = new Stack<Intersecting>();
		//mark all intersections as unvisited
		for(StoplightIntersection i : Manager.intersections) {
			i.setVisited(false);
		}
		calcNextRoad(road, destination, route);
		//get rid of the starting road since we're already on it
		//if this throws a null pointer then the destination is unreachable
		//but that should never happen
		route.pop();
		
		return route;
	}
	
	private static boolean calcNextRoad(Intersecting thisRoad, Exit destination, Stack<Intersecting> route) {
		Intersection next = thisRoad.getIntersection();
		
		next.setVisited(true); //mark this intersection as visited so we don't traverse it again
		
		if (next == destination) {
			route.push(thisRoad);
			return true;
		} else {
			Intersecting[] roads = next.getRoads();
						
			if(roads == null) {
				return false;
			}
			
			for (Intersecting road : roads) {
				if (road != DeadEnd.get() && !road.getIntersection().getVisited()) {
					boolean b = calcNextRoad(road, destination, route);
					if(b) {
						route.push(thisRoad);
						return true;
					}
				}
			}
			return false;
		} //end of else block
	}
}
