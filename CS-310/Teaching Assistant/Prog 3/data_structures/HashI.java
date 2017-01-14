
package data_structures;

import java.util.Iterator;  

public interface HashI<K, V> extends Iterable<K> {

	/*
	 * NOTE: next time!!!
	 * 
	 * Remove the value iterator (too confusing. Just return an array of values)
	 * Add a make empty method that is O(1)
	 */
	
	
	// The Dictionary constructor should accept a single parameter, an int, that
	// sets the initial size of the Dictionary.
	
	// Adds the given key/value pair to the dictionary.  Returns 
	// false if the dictionary is full, or if the key is a duplicate. 
	// Returns true if addition succeeded. 
	public boolean add(K key, V value);

	// Deletes the key/value pair identified by the key parameter. 
	// Returns true if the key/value pair was found and removed, 
	// otherwise false. 
	public boolean remove(K key);

	// Returns the value associated with the parameter key.  Returns 
	// null if the key is not found or the dictionary is empty. 
	public V getValue(K key);

	// Returns the number of key/value pairs currently stored 
	// in the dictionary 
	public int size();

	// Returns true if the dictionary is empty 
	public boolean isEmpty();
	
	// Returns the current load factor of the dictionary (lambda)
	public double loadFactor();
	
	// resizes the dictionary
	public void resize(int newSize);
	
	// Returns an Iterator of the keys in the dictionary, in ascending 
	// sorted order 
	public Iterator<K> iterator();
	
	// Returns an Iterator of the keys in the dictionary, in ascending 
	// sorted order 
	public Iterator<K> keys();

	// Returns an Iterator of the values in the dictionary.  The 
	// order of the values must match the order of the keys. 
	public Iterator<V> values();

}
