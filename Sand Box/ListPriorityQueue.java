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
   *           if unable to cast to the natural order.
   */
  public ListPriorityQueue() {
    baseQueue = new ArrayList<E>();
    curComp = (Comparator<? super E>) (Comparator.naturalOrder());
  }

  /**
   * constructs a List-based priority queue from the java.util.Collection
   * parameter object using the elements' natural ordering.
   * 
   * @param col
   *          the collection object from which to create the queue
   */
  public ListPriorityQueue(Collection<? extends E> col) {
   this();
   if(col.equals(null)){
	   throw new
   }

  }

  /**
   * constructs a List-based priority queue consisting of the elements in the
   * parameter collection and using the provided comparator when establishing
   * the elements' order.
   * 
   * @param col
   *          The collection containing the objects to insert into the queue
   * @param comp
   *          The object to use to establish order for the elements within the
   *          collection.
   */
  public ListPriorityQueue(Collection<? extends E> col,
      Comparator<? super E> comp) {
    // TODO: code this
  }

  /**
   * constructs a List-based priority queue using the parameter object to
   * establish the queue's ordering.
   * 
   * @param comp
   *          The object to use to establish order for the elements within the
   *          collection.
   */
  public ListPriorityQueue(Comparator<? super E> comp) {
    // TODO: code this
  }

  @Override
  public boolean add(E arg0) {
    if (baseQueue instanceof RandomAccess) {
      // TODO: code this
    }

    // TODO: code this

    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public void clear() {
    // TODO: code this
  }

  @Override
  public boolean contains(Object arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public E element() {
    // TODO: code this
    return null;
  }

  @Override
  public boolean isEmpty() {
    // TODO: code this
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    // TODO: code this (hint: it should be about one line)
    return null;
  }

  @Override
  public boolean offer(E arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public E peek() {
    // TODO: code this
    return null;
  }

  @Override
  public E poll() {
    // TODO: code this
    return null;
  }

  @Override
  public E remove() {
    // TODO: code this
    return null;
  }

  @Override
  public boolean remove(Object arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> arg0) {
    // TODO: code this
    return false;
  }

  @Override
  public int size() {
    // TODO: code this
    return -1;
  }

  @Override
  public Object[] toArray() {
    // TODO: code this
    return null;
  }

  @Override
  public <T> T[] toArray(T[] arg0) {
    // TODO: code this
    return null;
  }

}