/**
 * 
 */
package data_structuresH;

import java.util.Iterator;

/**
 * @author redwards
 *
 */
public class LinkedList<E> implements HashListI<E> {

	Node<E> head, tail;
	int currentSize;

	class Node<T> {
		Node<T> next;
		T data;
		public Node(T obj) {
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


	@Override
	public void add(E obj) {
		Node<E> node = new Node<E>(obj);

		if (head == null) 
			tail = node;

		node.next = head;
		head = node;
		currentSize++;
	}

	@Override
	public E remove(E obj) {
		if (head == null)
			return null;
		E tmp = head.data;
		if (head == tail) {
			head = tail = null;
		}
		else {
			head = head.next;
		}
		currentSize--;
		return tmp;
	}

	@Override
	public void makeEmpty() {
		head = tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return currentSize;
	}

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

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
}
