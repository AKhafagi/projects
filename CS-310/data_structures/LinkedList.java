/**
 * 
 */
package data_structures;

import java.util.Iterator;

/**
 * @author redwards
 *
 */
public class LinkedList<E> implements ListI<E> {

	Node<E> head, tail;
	int currentSize;

	class Node<E> {
		Node<E> next;
		E data;
		public Node(E obj) {
			this.data = obj;
		}
	}

	class IteratorHelper implements Iterator<E> {

		Node<E> index;
		public IteratorHelper() {
			index = head;
		}

		public boolean hasNext() {
			return index != null;
		}

		public E next() {
			E tmp = index.data;
			index=index.next;
			return tmp;
		}

		public void remove() {
			throw new UnsupportedOperationException("You can't remove while iterating.");
		}
	}

	/**
	 * 
	 */
	public LinkedList() {
		head = tail = null;
		currentSize = 0;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#addFirst(java.lang.Object)
	 */
	@Override
	public void addFirst(E obj) {
		Node<E> node = new Node<E>(obj);

		if (head == null) 
			tail = node;

		node.next = head;
		head = node;
		currentSize++;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#addLast(java.lang.Object)
	 */
	@Override
	public void addLast(E obj) {
		if (head == null) {
			addFirst(obj);
			return;
		}

		Node<E> node = new Node<E>(obj);
		tail.next = node;
		tail = node;
		currentSize++;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#removeFirst()
	 */
	@Override
	public E removeFirst() {
		if (head == null)
			return null;
		E tmp = head.data;
		if (head == tail) 
			head = tail = null;
		else
			head = head.next;
		currentSize--;
		return tmp;
	}


	/* (non-Javadoc)
	 * @see data_structures.ListI#removeLast()
	 */
	@Override
	public E removeLast() {
		if (head==null)
			return null;
		if (head == tail) // single element list
			return removeFirst();

		E tmp=tail.data;
		Node<E> current = head, prev = null;
		while (current.next != null) {
			prev = current;
			current = current.next;
		}

		prev.next = null;
		tail = prev;
		currentSize--;
		return tmp;

	}



	/* (non-Javadoc)
	 * @see data_structures.ListI#peekFirst()
	 */
	@Override
	public E peekFirst() {
		if (head == null)
			return null;
		return head.data;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#peekLast()
	 */
	@Override
	public E peekLast() {
		if (tail == null)
			return null;
		return tail.data;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#makeEmpty()
	 */
	@Override
	public void makeEmpty() {
		head = tail = null;
		currentSize = 0;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#isFull()
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#size()
	 */
	@Override
	public int size() {
		return currentSize;
	}

	/* (non-Javadoc)
	 * @see data_structures.ListI#contains(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(E obj) {
		if (head == null) 
			return false;
		
		Node<E> curr = head;
		while (curr != null) {
			if (((Comparable<E>)curr.data).compareTo(obj) == 0)
				return true;
			curr = curr.next;
		}
		return false;
		
	}



	/* (non-Javadoc)
	 * @see data_structures.ListI#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

}
