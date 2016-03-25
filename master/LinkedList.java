/**
 * @author Anas Khafagi
 * @account Ace240
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * Date: March 17th 2016<br>
 * <code> Deque </code> Program #2
 * LinkedList: Creates a <code> doublyLinkedList </code> <code> Deque </code> using the ListIterator
 * Nested Classes: <code> Node<T> </code> : Creates a Node Container for a doublyLinkedList
 * IterHelper: Creates a <code> ListIterator </code> to iterate through the elements of the <code> Deque </code>
 * DescendingIterator: Creates a <code> Iterator </code> that returns the elements in reverse sequential order. 
 * The elements will be returned in order from last (tail) to first (head).
 */
package edu.sdsu.cs.datastructures;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * LinkedList class: creates a doublylinkedList <code> Deque </code> Using the
 * list Iterator
 * 
 * @param <E>
 *            the type of elements held in this <code> Deque </code>
 */

public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E> {

	protected int elementCount;
	protected Node<E> head, tail;

	/**
	 * Constructs an empty LinkedList.
	 */
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.elementCount = 0;
	}

	/**
	 * Constructs a LinkedList containing the elements of the specified
	 * collection, in the order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this
	 *            LinkedList
	 * @throws NullPointerException
	 *             - if the specified collection is null
	 */
	public LinkedList(Collection<? extends E> c) {
		this();
		if (c.equals(null)) {
			throw new NullPointerException("The Specified Collection is null");
		}
		addAll(c);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 * 
	 * @param index:
	 *            the specified position in the list where the ListIterator
	 *            should start.
	 * @return the ListIterator at the specified position.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return (new ItrHelper(index));
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return number of elements of the list.
	 */
	@Override
	public int size() {
		return this.elementCount;
	}

	/**
	 * Inserts the specified element at the front of this <code> Deque </code>
	 * 
	 * @param e
	 *            the element to add
	 */
	@Override
	public void addFirst(E e) {
		super.add(0, e);
	}

	/**
	 * Inserts the specified element at the end of this <code> Deque </code>
	 * 
	 * @param e
	 *            the element to add
	 */
	@Override
	public void addLast(E e) {
		super.add(e);
	}

	/**
	 * Inserts the specified element at the front of this <code> Deque </code>
	 * 
	 * @param e
	 *            the element to add
	 * @return true
	 */
	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	/**
	 * Inserts the specified element at the end of this <code> Deque </code>
	 * 
	 * @param e
	 *            the element to add
	 * @return true
	 */
	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	/**
	 * Retrieves and removes the first element of this <code> Deque </code>.
	 * This method differs from pollFirst only in that it throws an exception if
	 * this <code> Deque </code> is empty.
	 * 
	 * @return the head of this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E removeFirst() {
		throwNoSuchElem();
		return super.remove(0);

	}

	/**
	 * Retrieves and removes the last element of this <code> Deque </code>. This
	 * method differs from pollLast only in that it throws an exception if this
	 * <code> Deque </code> is empty.
	 * 
	 * @return the tail of this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E removeLast() {
		throwNoSuchElem();
		return super.remove(size() - 1);
	}

	/**
	 * Retrieves and removes the first element of this <code> Deque </code>, or
	 * returns null if this <code> Deque </code> is empty.
	 * 
	 * @return the head of this <code> Deque </code>, or null if this
	 *         <code> Deque </code> is empty
	 */
	@Override
	public E pollFirst() {
		if (size() == 0) {
			return null;
		}
		return super.remove(0);
	}

	/**
	 * Retrieves and removes the last element of this <code> Deque </code>, or
	 * returns null if this <code> Deque </code> is empty.
	 * 
	 * @return the tail of this <code> Deque </code>, or null if this
	 *         <code> Deque </code> is empty
	 */
	@Override
	public E pollLast() {
		if (size() == 0) {
			return null;
		}
		return super.remove(size() - 1);

	}

	/**
	 * Retrieves, but does not remove, the first element of this
	 * <code> Deque </code>. This method differs from peekFirst only in that it
	 * throws an exception if this <code> Deque </code> is empty.
	 * 
	 * @return the head of this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E getFirst() {
		throwNoSuchElem();
		return super.get(0);

	}

	/**
	 * Retrieves, but does not remove, the last element of this
	 * <code> Deque </code>. This method differs from peekFirst only in that it
	 * throws an exception if this <code> Deque </code> is empty.
	 * 
	 * @return the tail of this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E getLast() {
		throwNoSuchElem();
		return super.get(size() - 1);
	}

	/**
	 * Retrieves, but does not remove, the first element of this
	 * <code> Deque </code>, or returns null if this <code> Deque </code> is
	 * empty.
	 * 
	 * @return the head of this <code> Deque </code>, or null if this
	 *         <code> Deque </code> is empty
	 */
	@Override
	public E peekFirst() {
		if (size() == 0) {
			return null;
		}
		return super.get(0);

	}

	/**
	 * Retrieves, but does not remove, the last element of this
	 * <code> Deque </code>, or returns null if this <code> Deque </code> is
	 * empty.
	 * 
	 * @return the tail of this <code> Deque </code>, or null if this
	 *         <code> Deque </code> is empty
	 */
	@Override
	public E peekLast() {
		if (size() == 0) {
			return null;
		}
		return super.get(size() - 1);

	}

	/**
	 * Removes the first occurrence of the specified element from this
	 * <code> Deque </code>. If the <code> Deque </code> does not contain the
	 * element, it is unchanged.
	 * 
	 * @param o
	 *            element to be removed from this <code> Deque </code>, if
	 *            present
	 * @return true if an element was removed as a result of this call
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		return super.remove(o);

	}

	/**
	 * Removes the last occurrence of the specified element from this
	 * <code> Deque </code>. If the <code> Deque </code> does not contain the
	 * element, it is unchanged.
	 * 
	 * @param o
	 *            element to be removed from this <code> Deque </code>, if
	 *            present
	 * @return true if an element was removed as a result of this call
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		int index = super.lastIndexOf(o);
		if (index != -1) {
			super.remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Inserts the specified element into the queue represented by this
	 * <code> Deque </code> (in other words, at the tail of this
	 * <code> Deque </code>).
	 * 
	 * @param e
	 *            the element to add
	 * @return true
	 */
	@Override
	public boolean offer(E e) {
		return this.offerLast(e);
	}

	/**
	 * Retrieves and removes the head of the queue represented by this
	 * <code> Deque </code> (in other words, the first element of this
	 * <code> Deque </code>).
	 * 
	 * @return the head of the queue represented by this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E remove() {
		return this.removeFirst();
	}

	/**
	 * Retrieves and removes the head of the queue represented by this
	 * <code> Deque </code> (in other words, the first element of this
	 * <code> Deque </code>), or returns null if this <code> Deque </code> is
	 * empty.
	 * 
	 * @return the first element of this <code> Deque </code>, or null if this
	 *         <code> Deque </code> is empty.
	 */
	@Override
	public E poll() {
		return this.pollFirst();

	}

	/**
	 * Retrieves, but does not remove, the head of the queue represented by this
	 * <code> Deque </code> (in other words, the first element of this
	 * <code> Deque </code>).
	 * 
	 * @return the head of the queue represented by this <code> Deque </code>
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E element() {
		return this.getFirst();
	}

	/**
	 * Retrieves, but does not remove, the head of the queue represented by this
	 * <code> Deque </code> (in other words, the first element of this
	 * <code> Deque </code>), or returns null if this <code> Deque </code> is
	 * empty.
	 * 
	 * @return the head of the queue represented by this <code> Deque </code>,
	 *         or null if this <code> Deque </code> is empty
	 */
	@Override
	public E peek() {
		return this.peekFirst();
	}

	/**
	 * Pushes an element onto the stack represented by this list. In other
	 * words, inserts the element at the front of this list. This method is
	 * equivalent to addFirst(E).
	 * 
	 * @param e
	 *            the element to be added to the list
	 */
	@Override
	public void push(E e) {
		this.addFirst(e);
	}

	/**
	 * Pops an element from the stack represented by this <code> Deque </code>.
	 * In other words, removes and returns the first element of this
	 * <code> Deque </code>.
	 * 
	 * @return the element at the front of this <code> Deque </code> (which is
	 *         the top of the stack represented by this <code> Deque </code>)
	 * @throws NoSuchElementException
	 *             - if this <code> Deque </code> is empty
	 */
	@Override
	public E pop() {
		return this.removeFirst();
	}

	/**
	 * Returns an iterator over the elements in this <code> Deque </code> in
	 * reverse sequential order. The elements will be returned in order from
	 * last (tail) to first (head).
	 * 
	 * @return an iterator over the elements in this <code> Deque </code> in
	 *         reverse sequence
	 */
	@Override
	public Iterator<E> descendingIterator() {
		return (new reverseIterator());
	}

	/**
	 * Creates a Node<T> container to use in the linked list
	 * 
	 * @param <T>
	 *            the type of the object that will be stored in the Node<T>
	 */
	protected class Node<T> {
		protected T data;
		protected Node<T> next, prev;

		/**
		 * Creates a Node with next and prev fields set to null and the data
		 * field set to <code> data </code>
		 * 
		 * @param data
		 *            the object to be stored in the node.
		 */
		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		/**
		 * 
		 * @param data
		 *            the object to be stored in the node.
		 */

		/**
		 * Creates a Node with next set <code> next </code> and prev set
		 * <code> prev </code> field set to <code> data </code>
		 * 
		 * @param data
		 *            the object to be stored in the node.
		 * @param next
		 *            the next <code> Node </code>
		 * @param prev
		 *            the prev <code> Node </code>
		 */
		public Node(T data, Node<T> next, Node<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

	}

	/**
	 * Creates a <code> ListIterator </code> that iterates the LinkedList and
	 * can modify it.
	 */
	protected class ItrHelper implements ListIterator<E> {
		protected Node<E> cursor;
		protected int cursorIndex;
		protected boolean shouldRemove;
		protected Node<E> lastReturned;
		protected boolean nextCalled;

		/**
		 * Creates a <code> ListIterator </code> that starts from the head of
		 * the list.
		 */
		public ItrHelper() {
			this.cursor = head;
			this.cursorIndex = 0;
			this.shouldRemove = false;
			lastReturned = null;

		}

		/**
		 * Creates a <code> ListIterator </code> that starts from the specified
		 * position of the list.
		 * 
		 * @param index
		 *            the specified position to start the
		 *            <code> ListIterator </code> from
		 */
		public ItrHelper(int index) {
			this.checkBounds(index);
			cursor = (index == size()) ? null : node(index);
			cursorIndex = index;
			this.shouldRemove = false;
			this.lastReturned = null;

		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the forward direction. (In other words, returns true if
		 * next() would return an element rather than throwing an exception.)
		 * 
		 * @return true if the list iterator has more elements when traversing
		 *         the list in the forward direction
		 */
		@Override
		public boolean hasNext() {
			return (cursorIndex != size());
		}

		/**
		 * Returns the next element in the list and advances the cursor
		 * position.
		 * 
		 * @return the next element in the list
		 * @throws NoSuchElementException
		 *             - if the iteration has no next element.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There are no more elements to iterate through");
			}
			this.lastReturned = cursor;
			cursor = cursor.next;
			shouldRemove = true;
			cursorIndex++;
			nextCalled = true;
			return lastReturned.data;

		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the reverse direction. (In other words, returns true if
		 * previous() would return an element rather than throwing an exception.
		 * 
		 * @return true if the list iterator has more elements when traversing
		 *         the list in the reverse direction
		 */
		@Override
		public boolean hasPrevious() {
			return (cursorIndex != 0);
		}

		/**
		 * Returns the previous element in the list and moves the cursor
		 * position backwards.
		 * 
		 * @return the previous element in the list NoSuchElementException - if
		 *         the iteration has no previous element
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("There are no more elements to iterate through");
			}
			if (cursor == null) {
				lastReturned = tail;
				cursor = tail;
			} else {
				cursor = cursor.prev;
				this.lastReturned = cursor;
			}
			nextCalled = false;
			shouldRemove = true;
			cursorIndex--;

			return lastReturned.data;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to next(). (Returns list size if the list iterator is
		 * at the end of the list.)
		 * 
		 * @return the index of the element that would be returned by a
		 *         subsequent call to next, or list size if the list iterator is
		 *         at the end of the list
		 */
		@Override
		public int nextIndex() {
			return cursorIndex;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to previous(). (Returns -1 if the list iterator is at
		 * the beginning of the list.)
		 * 
		 * @return the index of the element that would be returned by a
		 *         subsequent call to previous, or -1 if the list iterator is at
		 *         the beginning of the list
		 */
		@Override
		public int previousIndex() {
			return cursorIndex - 1;

		}

		/**
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). This call can only be made once per
		 * call to next or previous. It can be made only if add(E) has not been
		 * called after the last call to next or previous.
		 * 
		 * @throws IllegalStateException
		 *             - if neither next nor previous have been called, or
		 *             remove or add have been called after the last call to
		 *             next or previous
		 */
		@Override
		public void remove() {
			if (!shouldRemove)
				throw new IllegalStateException("Cannot remove from List");
			if (size() == 1) {
				head = tail = null;
			} else if (lastReturned == head) {
				head = lastReturned.next;
			} else if (lastReturned == tail) {
				tail = lastReturned.prev;

			} else {
				lastReturned.prev.next = lastReturned.next;
				lastReturned.next.prev = lastReturned.prev;
			}
			if (nextCalled) {
				cursorIndex--;
			}
			elementCount--;
			shouldRemove = false;

		}

		/**
		 * Replaces the last element returned by next() or previous() with the
		 * specified element (optional operation). This call can be made only if
		 * neither remove() nor add(E) have been called after the last call to
		 * next or previous.
		 * 
		 * @param e
		 *            the element with which to replace the last element
		 *            returned by next or previous
		 * @throws IllegalStateException
		 *             - if neither next nor previous have been called, or
		 *             remove or add have been called after the last call to
		 *             next or previous
		 */
		@Override
		public void set(E e) {
			if (!shouldRemove) {
				throw new IllegalStateException("Cannot set the element in the list to specified element");
			}
			this.lastReturned.data = e;

		}

		/**
		 * Inserts the specified element into the list (optional operation). The
		 * element is inserted immediately before the element that would be
		 * returned by next()
		 * 
		 * @param element
		 *            the element to insert
		 */
		@Override
		public void add(E element) {
			cursor = addBeforeNode(element, cursor);
			cursorIndex++;
			elementCount++;
			lastReturned = null;
		}

		/**
		 * checks whether the indicated index is valid and in range of the list
		 * 
		 * @param index
		 *            the bound to check
		 * @throws IndexOutOfBoundsException-
		 *             if the index is not in the range of the list or is
		 *             invalid
		 */
		private void checkBounds(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException("the index " + "index" + " is out of bounds");
			}

		}
		/**
		 * returns the <code> Node </code> at the specified index
		 * 
		 * @param index
		 *            the specified position to return the <code> node </code>
		 *            from
		 */
		private Node<E> node(int index) {
			if (index < (size() >> 1)) {
				Node<E> temp = head;
				for (int i = 0; i < index; i++)
					temp = temp.next;
				return temp;
			} else {
				Node<E> temp = tail;
				for (int i = size() - 1; i > index; i--)
					temp = temp.prev;
				return temp;
			}
		}

		/**
		 * adds the element before the given <code> Node </code>.
		 * 
		 * @param node
		 *            the <code> Node </code> to add before.
		 * @param element
		 *            the element to be added
		 */
		private Node<E> addBeforeNode(E element, Node<E> node) {
			if (size() == 0) {
				Node<E> newNode = new Node<E>(element);
				head = tail = newNode;
			} else if (node == null) {
				Node<E> newNode = new Node<E>(element, node, tail);
				tail.next = newNode;
				tail = newNode;
			} else {
				Node<E> newNode = new Node<E>(element, node, node.prev);
				if (node == head) {
					node.prev = newNode;
					head = newNode;
					return node;
				}
				node.prev.next = newNode;
				node.prev = newNode;
			}
			return node;
		}

	}

	/**
	 * Creates an <code> Iterator </code> the iterates in reverse order
	 *
	 */
	protected class reverseIterator implements Iterator<E> {
		protected ListIterator<E> it = listIterator(size());

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
			throw new UnsupportedOperationException("the remove operation is not supported by this iterator");
		}

	}

	/**
	 * throws a noSuchElementException if the <code> Deque </code> is empty
	 */
	private void throwNoSuchElem() {
		if (size() == 0) {
			throw new NoSuchElementException("The Deque is empty");
		}
	}
}
