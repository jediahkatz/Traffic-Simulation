//basic fixed-size array backed circular queue
public class CircularArray<E> {
	private Object[] array = null;
	private int front, back;
	public final int size; //maximum size
	
	public CircularArray(int size) {
		if(size < 1) {
			this.size = 10; //default size = 10
		} else {
			this.size = size;
		}
		array = new Object[size];
		//start by filling the array with null
		for(int i=0; i<size; i++) {
			array[i] = null;
		}
		front = 0;
		back = size-1;
	}
	
	public boolean isFull() {
		return array[back] != null;
	}
	
	//returns true if the front value is not null
	public boolean hasNext() {
		return array[front] != null;
	}
	
	//add to the end of the queue
	public void enqueue(E element) {
		if(isFull()) {
			//this should never happen in the program
			throw new StackOverflowError("The stack is full.");
		}
		
		array[back] = element;
	}
	
	//the first element leaves the queue
	//all other elements move up
	public Object dequeueAndMoveUp() {
		Object temp = array[front];
		array[front] = null;
		front = ++front % size;
		back = ++back % size;
		return temp;
	}
	
	public Object[] getArray() {
		return array;
	}

}
