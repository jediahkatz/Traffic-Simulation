
public class Log {
	private static Queue<String> log = new Queue<String>();
	
	private Log() {
		//this class cannot be instantiated and its methods are all static
	}
	
	public static void writeLog(String line) {
		log.enqueue(line);
	}
	
	public static void printLog() {
		while(!log.isEmpty()) {
			String line = log.dequeue();
			System.out.println(line);
		}
	}
	
	public static void clearLog() {
		log.clear();
	}
}
