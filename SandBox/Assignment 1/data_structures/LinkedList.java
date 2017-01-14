package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E> {
	class Node<E> {
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head, tail;
	private int numElements;

	public LinkedList() {
		head = tail = null;
		numElements = 0;
	}

	@Override
	public void addFirst(E obj) {
		Node<E> k = new Node<E>(obj);
		if (isEmpty())
			tail = k;
		k.next = head;
		head = k;
		this.numElements++;
	}

	@Override
	public void addLast(E obj) {
		if (isEmpty()) {
			addFirst(obj);
			return;
		}
		Node<E> k = new Node<E>(obj);
		tail.next = k;
		tail = k;
		numElements++;
	}

	@Override
	public E removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("the list is empty");
		E tmp = head.data;
		if (head == tail)
			head = tail = null;
		else
			head = head.next;
		numElements--;
		return tmp;
	}

	@Override
	public E removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("the list is empty");
		if (head == tail)
			return removeFirst();
		E tmp = tail.data;
		Node<E> current = head;
		Node<E> prev = null;
		while (current.next != null) {
			prev = current;
			current = current.next;
		}
		prev.next = null;
		tail = prev;
		numElements--;
		return tmp;
	}

	@Override
	public E peekFirst() {
		return head.data;
	}

	@Override
	public E peekLast() {
		return tail.data;
	}

	@Override
	public void makeEmpty() {
		head = tail = null;
		numElements = 0;

	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return numElements;
	}

	@Override
	public boolean contains(E obj) {
		if (isEmpty())
			return false;
		Node<E> current = head;
		while (current != null) {
			if (current.data.equals(obj))
				return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper<E>();
	}

	class IteratorHelper<K> implements Iterator<E> {
		Node<E> index;

		public IteratorHelper() {
			index = head;
		}

		@Override
		public boolean hasNext() {
			return index != null;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException("The iteration has no more elements");
			E tmp = index.data;
			index = index.next;
			return tmp;
		}
	}

}