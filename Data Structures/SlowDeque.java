/**
 * @author Anas Khafagi
 * @account Ace240
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * Date: March 17th 2016<br>
 * Creates a Vector Deque using composition
 * Nested class DescendingIterator Creates a <code> Iterator </code> that returns the elements in reverse sequential order. 
 * The elements will be returned in order from last (tail) to first (head).

 */


import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * SlowDeque class Creates a Deque that uses a Vector 
 * @param <E> the type of Object that is going to be added to the Deque
 */
public class SlowDeque<E> implements Deque<E> {
	protected Vector<E> deque;

	/**
	 * Constructs an empty Deque so that its internal data array has size 10
	 * and its standard capacity increment is zero.
	 */
	public SlowDeque() {
		deque = new Vector<E>();
	}

	/**
	 * Constructs a Deque containing the elements of the specified collection,
	 * in the order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this
	 *            Deque
	 * @throws NullPointerException
	 *             - if the specified collection is null
	 */
	public SlowDeque(Collection <? extends E> c){
		this();
		if(c.equals(null)){
			throw new NullPointerException("the Specified collection is empty");
		}
		addAll(c);
	}

	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return deque.addAll(c);
	}

	@Override
	public void clear() {
		deque.clear();

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return deque.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return deque.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return deque.retainAll(c);
	}

	@Override
	public Object[] toArray() {
		return deque.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return deque.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return deque.add(e);
	}

	@Override
	public void addFirst(E e) {
		deque.add(0, e);
	}

	@Override
	public void addLast(E e) {
		deque.add(size(), e);

	}

	@Override
	public boolean contains(Object o) {
		return deque.contains(o);
	}

	@Override
	public Iterator<E> descendingIterator() {
		return (new DescendingIterator());

	}

	@Override
	public E element() {
		return this.getFirst();
	}

	@Override
	public E getFirst() {
		throwNoSuchElement();
		return deque.get(0);

	}

	@Override
	public E getLast() {
		throwNoSuchElement();
		return deque.get(size() - 1);
	}

	@Override
	public Iterator<E> iterator() {
		return deque.iterator();
	}

	@Override
	public boolean offer(E e) {
		return this.offerLast(e);
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E peek() {
		return this.peekFirst();
	}

	@Override
	public E peekFirst() {
		if (size() == 0) {
			return null;
		}

		return deque.get(0);
	}

	@Override
	public E peekLast() {
		if (size() == 0) {
			return null;
		}

		return deque.get(size() - 1);
	}

	@Override
	public E poll() {
		return this.pollFirst();
	}

	@Override
	public E pollFirst() {
		if (size() == 0) {
			return null;
		}
		return deque.remove(0);
	}

	@Override
	public E pollLast() {
		if (size() == 0) {
			return null;
		}
		return deque.remove(size() - 1);
	}

	@Override
	public E pop() {
		return this.removeFirst();
	}

	@Override
	public void push(E e) {
		this.addFirst(e);

	}

	@Override
	public E remove() {
		return this.removeFirst();
	}

	@Override
	public boolean remove(Object o) {
		return deque.remove(o);
	}

	@Override
	public E removeFirst() {
		throwNoSuchElement();
		return pollFirst();
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		return deque.remove(o);
	}

	@Override
	public E removeLast() {
		throwNoSuchElement();
		return deque.remove(size() - 1);
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		int index = deque.lastIndexOf(o);
		if(index != -1 ){
			deque.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return deque.size();
	}

	/**
	 * Creates an <code> Iterator </code> the iterates in reverse order
	 *
	 */
	protected class DescendingIterator implements Iterator<E> {
		protected ListIterator<E> it = deque.listIterator(size());
		
		@Override
		public boolean hasNext() {
			return it.hasPrevious();
		}

		@Override
		public E next() {
			return it.previous();
		}
		
		@Override
		public void remove() {
			it.remove();

		}

		

	}
	/**
	 * throws a noSuchElementException if the <code> Deque </code> is empty
	 */
	private void throwNoSuchElement() {
		if (size() == 0) {
			throw new NoSuchElementException("this deque is empty");
		}
	}
}