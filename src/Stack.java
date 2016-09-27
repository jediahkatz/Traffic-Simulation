//a simple pointer-based stack data structure
public class Stack<E> {
	private int size;
	private Node<E> top; //most recently added
	
	public Stack() {
		size = 0;
		top = null;
	}
	
	public void push(E element) {
		if(isEmpty()) {
			top = new Node<E>(element);
		} else {
			Node<E> temp = new Node<E>(element);
			temp.setNext(top);
			top = temp;
		}
		size++;
	}
	
	public E pop() {
		E temp = top.getData();
		top = top.getNext();
		size--;
		return temp;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}
}
