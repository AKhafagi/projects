import java.util.Arrays;

public class ArrayList<E> implements ListT<E> {
	private E[] arr;
	private int size;
/**
 * Constructs an empty Array List with a length of 10
 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.arr = (E[]) new Object[10];
		this.size = 0;
	}
/**
 * Constructs an empty ArrayList which allows the user to manipulate the length
 * @param num
 */
	@SuppressWarnings("unchecked")
	public ArrayList(int num) {
		this.arr = (E[]) new Comparable[num];
		this.size = 0;
	}
	/**
	* Appends the specified element to the end of this list
	* @param data
	* @return boolean
	*/
	@Override
	public boolean add(E data) {
		if (data != null) {
			if (size + 1 >= arr.length) {
				this.grow();
				this.arr[size++] = data;
				return true;
			}
			else {
				this.arr[size++] = data;
				return true;
			}
		}
		return false;
	}
	/**
	* Removes all of the elements from this list
	*/
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			this.arr[i] = null;
		}
		this.size = 0;
	}
	/**
	* Returns true if this list contains the specified element
	* @param data
	* @return boolean
	*/
	@Override
	public boolean contains(E data) {
		if (data != null) {
			for (int i = 0; i < size; i++) {
				if (((Comparable<E>)arr[i]).compareTo(data) == 0) {
					return true;
				}

			}

		}
		return false;
	}
	/**
	* Returns the element at the specified position in this list
	* @param index
	* @return E
	*/
	@Override
	public E get(int index) throws Exception {
		try {

			if (index >= size) {
				throw new IndexOutOfBoundsException("the index chosen is out of bound");
			}
			if (index < 0) {
				throw new IllegalArgumentException("the index chosen is invalid");
			}
		}
		catch (IllegalArgumentException | IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
		return arr[index];

	}
	/**
	* Returns the index of the first occurrence of the specified element in this list, * or -1 if this list does not contain the element
	* @param data
	* @return int
	*/
	@Override
	public int indexOf(E data) {
		if (data != null) {
			for (int i = 0; i < size; i++) {
				if (((Comparable<E>)arr[i]).compareTo(data) == 0) {
					return i;
				}

			}
		}
		return -1;
	}
	/**
	* Returns true if this list contains no elements
	* @return boolean
	*/
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	* Trims the capacity of this ArrayList instance to be the list's current size. * An application can use this operation to minimize the storage of an ArrayList instance.
	*/
	@Override
	public void trimToSize() {
		this.arr = Arrays.copyOf(arr, size);
	}
	/**
	* Returns the number of elements in this list
	* @return int size.
	*/
	@Override
	public int size() {
		return size;
	}

	/**
	 * double the length of the array
	 * @return the new sized array
	 */
	private E[] grow() {
		try {
			this.arr = Arrays.copyOf(arr, this.arr.length * 2);
		}
		catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return arr;
	}
	public void print(){
		for(int i =0; i<size;i++)
		System.out.print(arr[i] + ",");
		
	}
}