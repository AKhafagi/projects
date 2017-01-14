public interface ListT<E> {
/**
* Appends the specified element to the end of this list
* @param data
* @return boolean
*/
boolean add(E data);
/**
* Removes all of the elements from this list
*/
void clear();
/**
* Returns true if this list contains the specified element
* @param data
* @return boolean
*/
boolean contains(E data);
/**
* Returns the element at the specified position in this list
* @param index
* @return E
*/
E get(int index) throws Exception;
/**
* Returns the index of the first occurrence of the specified element in this list, * or -1 if this list does not contain the element
* @param data
* @return int
*/
int indexOf(E data);
/**
* Returns true if this list contains no elements
* @return boolean
*/
boolean isEmpty();
/**
* Trims the capacity of this ArrayList instance to be the list's current size. * An application can use this operation to minimize the storage of an ArrayList instance.
*/
void trimToSize();
/**
* Returns the number of elements in this list
* @return int
*/
int size();
}