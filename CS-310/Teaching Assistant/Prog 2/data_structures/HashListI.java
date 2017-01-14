package data_structures;

import java.util.Iterator;


/**
 * The HashList is an abbreviated Linked List that has 
 * only those methods we need for the Hash. We on;y
 * need a single add method, and we don't need remove
 * first and remove last! This interface describes
 * just those methods we need.
 *
 * @author CS310
 * 
 *
 *
 */
public interface HashListI<E> extends Iterable<E> {
	
	/**
	 * Add an object to the list at this position
	 * @param obj - the thing to be added
	 */
	public void add(E obj);
	
	/**
	 * Remove an object from the list
	 * @param obj The object to remove
	 * @return The object removed
	 */
	public E remove(E obj);

	/**
	 * Make the list empty
	 */
	public void makeEmpty();

	/**
	 * Is the list empty?
	 * @return true if the list is empty
	 */
	public boolean isEmpty();

	/**
	 * The current number of elements in the list
	 * @return the size of the llist
	 */
	public int size();

	/**
	 * Does the list contain this object
	 * @param obj The object to look for
	 * @return True if the list contains it.
	 */
	public boolean contains(E obj);

	/**
	 * An iterator for the list
	 */
	public Iterator<E> iterator();
}
