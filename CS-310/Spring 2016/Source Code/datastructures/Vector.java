
/**
 * Vector Class 
 * designs a generic dynamic Vector that expands and contracts when needed.
 * monday Febuary 22nd, 2016
 * Computer Science 310
 * San Diego State University
 *
 */
package edu.sdsu.cs.datastructures;

import java.util.AbstractList;
import java.util.Collection;

/**
 * @param <E>
 *  The data Type to be added to the Vector.
 *  @author Anas Khafagi         
 *  
 */
public class Vector<E> extends AbstractList<E> {
	protected E[] elementData;
	protected int capacity;
	protected int elementCount;
	protected int capacityIncerement;
	protected final static int DEFAULT_CAPACITY = 10;

	/**
	 * Constructs an empty vector with the specified initial capacity and with
	 * its capacity increment equal to zero.
	 * 
	 * @param intialCapacity
	 *            the intial size for the vector.
	 * @throws IllegalArgumentException
	 *             - if the specified initial capacity is negative
	 */
	public Vector(int intialCapacity) {
		this(intialCapacity, 0);
	}

	/**
	 * Constructs an empty vector so that its internal data array has size 10
	 * and its standard capacity increment is zero.
	 */
	public Vector() {
		this(DEFAULT_CAPACITY, 0);
	}

	/**
	 * Constructs a vector containing the elements of the specified collection,
	 * in the order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this
	 *            vector
	 * @throws NullPointerException
	 *             - if the specified collection is null
	 */
	public Vector(Collection<? extends E> c) {
		this();
		if (!c.equals(null)) {
			throw new NullPointerException(" The Specified collection is null ");
		}
		addAll(c);

	}

	/**
	 * Constructs a Vector with size IntialCapacity and capacityIncrement
	 * blockSize.
	 * 
	 * @param intialCapacity
	 *            the initial capacity of the vector.
	 * @param blockSize
	 *            the amount by which the capacity is increased when the vector
	 *            overflows.
	 * @throws IllegalArgumentException
	 *             - if the specified initial capacity is negative
	 */
	@SuppressWarnings("unchecked")
	public Vector(int intialCapacity, int blockSize) {
		super();
		if (intialCapacity < 0)
			throw new IllegalArgumentException("Invalid Size");
		this.elementData = (E[]) new Object[intialCapacity];
		this.capacityIncerement = blockSize;
		this.capacity = intialCapacity;
	}

	public boolean add(E element) {
		if (element.equals(null)) {
			throw new NullPointerException(
					"The specified element is null and this Vector does not permit null elements");
		}
		if (isNearCapacity()) {
			this.expand();
		}
		this.elementData[elementCount++] = element;
		modCount++;
		return true;

	}

	public E get(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("the index is out of range");
		}
		return this.elementData[index];
	}

	public E set(int index, E element) {
		if (element.equals(null)) {
			throw new NullPointerException(
					"The specified element is null and this Vector does not permit null elements");
		}
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("the index is out of range");
		}
		E tempVal = this.elementData[index];
		this.elementData[index] = element;
		modCount++;
		return tempVal;
	}

	public void add(int index, E element) {
		if (element.equals(null)) {
			throw new NullPointerException(
					"The specified element is null and this Vector does not permit null elements");
		}
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException("the index is out of range");
		}
		if (isNearCapacity()) {
			this.expand();
		}
		this.shift("shiftRight", index);
		this.elementData[index] = element;
		this.elementCount++;
		modCount++;
	}

	public E remove(int index) {
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException("the index is out of range");
		}
		E tempVal = elementData[index];
		elementData[index] = null;
		this.shift("shiftLeft", index);
		this.elementCount--;
		if (this.elementCount < this.capacity() / 2) {
			this.contract();
		}
		modCount++;
		return tempVal;
	}

	public boolean isEmpty() {
		return elementCount == 0;
	}
	/**
	 * returns the capacity of the vector
	 * 
	 * @return the capaicty of the vector.
	 */
	public int capacity() {
		return elementData.length;
	}


	public void clear() {
		for(int i =0; i<elementCount;i++){
			elementData[i]=null;
		}
		this.elementCount=0;
		modCount++;

	}

	public int indexOf(Object element) {
		if (element.equals(null)) {
			throw new NullPointerException(
					"The specified element is null and this Vector does not permit null elements");
		}
		for (int i = 0; i < size(); i++) {
			if (elementData[i].equals(element)) {
				return i;
			}

		}
		return -1;
	}

	public int size() {
		return this.elementCount;
	}

	/**
	 * Expands the capacity of the Vector to either double the intial size, or
	 * padded with <code>capacityIncerement</code>
	 */
	protected void expand() {
		if (capacityIncerement == 0) {
			elementData = copyOf(elementData, elementData.length * 2);
		} 
		else {
			elementData = copyOf(elementData, (elementData.length + capacityIncerement));
		}

	}

	/**
	 * Contracts the capacity of the Vector to the number of elements in the
	 * Vector
	 */
	protected void contract() {
		 if(size()==0){
			elementData = copyOf(elementData, DEFAULT_CAPACITY);
		}
		 else{
		this.elementData = copyOf(elementData, elementCount);
		 }
	}

	/**
	 * shifts all the elements of the Vector 1 spot to the right or left.
	 * 
	 * @param argument
	 *            wether to shift right or left.
	 * @param index
	 *            the index where the shift should start from.
	 */
	protected void shift(String argument, int index) {
		if (argument.equals("shiftRight")) {
			for (int i = elementCount; i >= index; i--) {

				if (i == index) {
					this.elementData[i] = null;
					return;
				}
				this.elementData[i] = this.elementData[i - 1];
			}
		}
		if (argument.equals("shiftLeft")) {
			for (int j = index; j < elementCount; j++) {
				if (j == elementCount - 1) {
					this.elementData[j] = null;
					return;
				}
				this.elementData[j] = this.elementData[j + 1];
			}
		}
	}

	/**
	 * Copies the elements of the array into a copy array of size
	 * <code> newSize </code>
	 * 
	 * @param arrayToCopy
	 *            the array to copy from.
	 * @param newSize
	 *            the size of the new array.
	 * @return the copy array with all the elements from the old array.
	 */
	@SuppressWarnings("unchecked")
	protected E[] copyOf(E[] arrayToCopy, int newSize) {
		int count = 0;
		E[] arrayCopy = (E[]) new Object[newSize];
		for (Object element : arrayToCopy) {
			if (count >= newSize) {
				return arrayCopy;
			}
			arrayCopy[count++] = (E) element;
		}
		return arrayCopy;
	}

	/**
	 * checks wether the array is near the capacity
	 * 
	 * @return true - if the array is near the capacity false - otherwise
	 */
	protected boolean isNearCapacity() {
		return ((this.capacity() == this.elementCount) || (size()==0));

	}
}
