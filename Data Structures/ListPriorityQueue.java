/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @author YOUR NAME HERE
 * @account BitBucket user name
 */

package edu.sdsu.cs.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;

/**
 * a List based priority queue implementation.
 *
 * @param <E>
 */
public class ListPriorityQueue<E> implements Queue<E> {

	private List<E> baseQueue;
	private Comparator<? super E> curComp;

	/**
	 * builds a list-based priority queue using the list elements' natural
	 * ordering. Consequently, the items which enter the queue must support the
	 * .compareTo interface.
	 * 
	 * @throws ClassCastException
	 *             if unable to cast to the natural order.
	 */
	@SuppressWarnings("unchecked")
	public ListPriorityQueue() {
		baseQueue = new ArrayList<E>();
		//baseQueue = new LinkedList<E>();
		curComp = (Comparator<? super E>) (Comparator.naturalOrder());
	}

	/**
	 * constructs a List-based priority queue from the java.util.Collection
	 * parameter object using the elements' natural ordering.
	 * 
	 * @param col
	 *            the collection object from which to create the queue
	 */
	public ListPriorityQueue(Collection<? extends E> col) {
		this();
		if (col.equals(null)) {
			throw new NullPointerException("The Specified Collection is null");
		}
		addAll(col);

	}

	/**
	 * constructs a List-based priority queue consisting of the elements in the
	 * parameter collection and using the provided comparator when establishing
	 * the elements' order.
	 * 
	 * @param col
	 *            The collection containing the objects to insert into the queue
	 * @param comp
	 *            The object to use to establish order for the elements within
	 *            the collection.
	 */
	public ListPriorityQueue(Collection<? extends E> col, Comparator<? super E> comp) {
		this(col);
		curComp = comp;
	}

	/**
	 * constructs a List-based priority queue using the parameter object to
	 * establish the queue's ordering.
	 * 
	 * @param comp
	 *            The object to use to establish order for the elements within
	 *            the collection.
	 */
	public ListPriorityQueue(Comparator<? super E> comp) {
		this();
		curComp = comp;
	}

	@Override
	public boolean add(E arg0) {
		if (baseQueue instanceof RandomAccess) {
			if (isEmpty()) {
				baseQueue.add(arg0);
				return true;
			} else {
				int index = iterativeBinaryInsertion(baseQueue, arg0, 0, size() - 1, (Comparator<E>) curComp);
				baseQueue.add(index, arg0);
				return true;
			}
		} else {
			if (isEmpty()) {
				baseQueue.add(arg0);
				return true;
			} else {
				ListIterator<E> it = baseQueue.listIterator();
				while (it.hasNext()) {
					E temp = it.next();
					if (curComp.compare(arg0, temp) < 0) {
						it.previous();
						it.add(arg0);
						return true;
					}
				}
				return baseQueue.add(arg0);

			}
		}
	}

	/**
	 * @return the baseQueue
	 */
	public List<E> getBaseQueue() {
		return baseQueue;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		boolean temp = baseQueue.addAll(arg0);
		baseQueue.sort(curComp);
		return temp;
	}

	@Override
	public void clear() {
		baseQueue.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		if (baseQueue instanceof RandomAccess) 
			return (this.iterativeBinarySearch(baseQueue, (E) arg0, 0, size() - 1, (Comparator<E>) curComp) != -1);
		return baseQueue.contains(arg0);

	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return baseQueue.containsAll(arg0);
	}

	@Override
	public E element() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return peek();
	}

	@Override
	public boolean isEmpty() {
		return baseQueue.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return baseQueue.iterator();
	}

	@Override
	public boolean offer(E arg0) {
		return add(arg0);
	}

	@Override
	public E peek() {
		return baseQueue.isEmpty() ? null : baseQueue.get(0);
	}

	@Override
	public E poll() {
		return baseQueue.isEmpty() ? null : baseQueue.remove(0);


	}

	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return baseQueue.remove(0);
	}

	@Override
	public boolean remove(Object arg0) {
		if (baseQueue instanceof RandomAccess) {
			int index = this.iterativeBinarySearch(baseQueue, (E) arg0, 0, size() - 1, (Comparator<E>) curComp);
			if (index != -1) {
				baseQueue.remove(index);
				return true;
			}
			return false;
		} else {
			return baseQueue.remove(arg0);
		}
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return baseQueue.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean temp = baseQueue.retainAll(arg0);
		baseQueue.sort(curComp);
		return temp;
	}

	@Override
	public int size() {
		return baseQueue.size();
	}

	@Override
	public Object[] toArray() {
		return baseQueue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return baseQueue.toArray(arg0);
	}

	private int iterativeBinarySearch(List<E> items, final E value, int low, int high, final Comparator<E> order) {
		while (low <= high) {
			int mid = (low + high) >> 1;
			int result = order.compare(value, items.get(mid));
			if (result > 0) {
				// value - array > 0: array value smaller, so in right half
				low = mid + 1;
			} else if (result < 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private int iterativeBinaryInsertion(List<E> items, final E value, int low, int high, final Comparator<E> order) {
		while (low <= high) {
			int mid = (low + high) >> 1;
			int result = order.compare(value, items.get(mid));
			if (result > 0) {
				// value - array > 0: array value smaller, so in right half
				low = mid + 1;
			} else if (result < 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return low;
	}
}