//simple dynamic queue which can add to the back and leave from the front
public class Queue<E> {
	private Node<E> head, tail;
	private int size;
	
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	//add to back of queue
	public void enqueue(E element) {
		if(isEmpty()) {
			head = new Node<E>(element);
			tail = head;
		} else {
			tail.setNext(new Node<E>(element));
			tail = tail.getNext();
		}
		size++;
	}
	
	//add to front of queue
	public void prepend(E element) {
		Node<E> temp = new Node<E>(element);
		temp.setNext(head);
		head = temp;
	}
	
	//take first element on queue
	public E dequeue() throws ElementNotFoundException {
		if(isEmpty()) throw new ElementNotFoundException("There was nothing on the queue!");
		Node<E> temp = head;
		head = head.getNext();
		size--;
		return temp.getData();
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
}
