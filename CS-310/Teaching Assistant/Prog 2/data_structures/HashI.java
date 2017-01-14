
package data_structures;

import java.util.Iterator;  

/**
 * The Hash interface that describes the methods that our hash will
 * implement.
 */



public interface HashI<K, V> extends Iterable<K> {
	
	/**
	 *  The Dictionary constructor should accept a single parameter, an int, that
	 *  sets the initial size of the Dictionary.
	 */
	
	
	 /**  
	 * Adds the given key/value pair to the dictionary.  Returns 
	 * false if the dictionary is full, or if the key is a duplicate. 
	 * Returns true if addition succeeded. 
	 *  
	 * @param key the key to add
	 * @param value the value associated with the key
	 * @return true if the key/value are added to the hash.
	 */
	public boolean add(K key, V value);


	/**
	 * Deletes the key/value pair identified by the key parameter. 
	 * Returns true if the key/value pair was found and removed, 
	 * otherwise returns false.
	 *  
	 * @param key
	 * @return
	 */
	public boolean remove(K key);
	
	/**
	 * Change the value associated with an existing key.
	 * @param key The key to change
	 * @param value
	 * @return
	 */
	public boolean changeValue(K key, V value);
	
	
	/**
	 * Test whether the hash has the entry associated with the key
	 * @param key the key to look for
	 * @return whether it is there.
	 */
	public boolean contains(K key);
	
	/**
	 * Returns the value associated with the parameter key. 
	 * Returns null if the key is not found or the dictionary is empty. 
	 * @param key the key to find the value for
	 * @return the value
	 */
	public V getValue(K key);

	/**
	 * Returns the number of key/value pairs currently stored in the dictionary 
	 * @return
	 */
	public int size();

	/**
	 * Returns true if the dictionary is empty 
	 * @return whether the dictionary is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Make the dictionary empty
	 */
	public void makeEmpty();
	
	/**
	 * Returns the current load factor of the dictionary (lambda)
	 * @return the loadFactor
	 */
	public double loadFactor();
	
	/**
	 * Get the maximum load factor (at which point we need to resize)
	 * @return the maximum load factor of the hash
	 */
	public double getMaxLoadFactor();
		
	/**
	 * Set the maximum load factor (at which point we need to resize)
	 * @param loadfactor the maximum load factor
	 */
	public void setMaxLoadFActor(double loadfactor);
	
	/**
	 * Resizes the dictionary
	 * @param newSize the size of the new dictionary
	 */
	public void resize(int newSize);
	
	/**
	 * Returns an Iterator of the keys in the dictionary, in ascending 
	 * sorted order 
	 */
	public Iterator<K> iterator();


}
