/**
 * 
 */
package data_structures;

import java.util.Iterator;


public class Hash<K, V> implements HashI<K, V> {

	@SuppressWarnings("hiding")
	class HashElement<K, V> implements Comparable<HashElement<K, V>>{
		K key;
		V value;

		public HashElement(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		public int compareTo(HashElement<K, V> o) {
			return (((Comparable<K>)o.key).compareTo(this.key));
		}
	}

	LinkedList<HashElement<K,V>>[] harray;
	int tablesize;
	int numElements;
	double maxLoadFactor;

	@SuppressWarnings("unchecked")
	public Hash(int tablesize) {
		this.tablesize= tablesize;
		harray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tablesize];
		for (int i=0; i<tablesize; i++)
			harray[i] = new LinkedList<HashElement<K,V>>();
		maxLoadFactor = 0.75;
		numElements = 0;
	}

	@Override
	public boolean add(K key, V value) {
		// do we need to resize
		if (loadFactor() > maxLoadFactor) {
			resize(tablesize * 2);
		}
		int hashval = (key.hashCode() & 0x7FFFFFFF) % tablesize;
		//hashCode = hashCode & tablesize - 1; // only works if tablesize is multiple of 2!
		HashElement<K,V> newhe = new HashElement<K,V>(key, value);
		harray[hashval].add(newhe);
		numElements++;
		return true;
	}

	@Override
	public boolean remove(K key) {
		int hashval = (key.hashCode() & 0x7FFFFFFF) % tablesize;
		for (HashElement<K,V> he : harray[hashval]) {
			if (((Comparable<HashElement<K, V>>)he).compareTo(
					new HashElement<K,V>(key, he.value)) == 0)
				if (harray[hashval].remove(he) != null) {
					numElements--;
					return true;
				}
		}
		return false;
	}
	
	@Override
	public boolean changeValue(K key, V value) {
		int hashval = (key.hashCode() & 0x7FFFFFFF) % tablesize;
		for (HashElement<K,V> he : harray[hashval])
			if (((Comparable<K>)he.key).compareTo(key) == 0) {
				he.value = value;
				return true;
			}
		return false;
	}
	
	@Override
	public boolean contains(K key) {
		int hashval = (key.hashCode() & 0x7FFFFFFF) % tablesize;
		for (HashElement<K,V> he : harray[hashval])
			if (((Comparable<K>)he.key).compareTo(key) == 0)
				return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		int hashval = (key.hashCode() & 0x7FFFFFFF) % tablesize;
		for (HashElement<K,V> he : harray[hashval]) {
			if (((Comparable<K>)he.key).compareTo(key) == 0) 
				return he.value;
		}
		return null;
	}

	@Override
	public int size() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		return numElements == 0;
	}

	@SuppressWarnings("unchecked")
	public void makeEmpty() {
		harray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tablesize];
		for (int i=0; i<tablesize; i++)
			harray[i] = new LinkedList<HashElement<K,V>>();
		maxLoadFactor = 0.75;
		numElements = 0;
	}
	
	
	/**
	 * Get the maximum load factor (at which point we need to resize)
	 * @return the maximum load factor of the hash
	 */
	public double getMaxLoadFactor() {
		return maxLoadFactor;
	}
	
	
	/**
	 * Set the maximum load factor (at which point we need to resize)
	 * @param loadfactor the maximum load factor
	 */
	public void setMaxLoadFActor(double loadfactor) {
		maxLoadFactor = loadfactor;
	}

	@Override
	public double loadFactor() {
		return (double) numElements/tablesize;
	}

	@Override
	public void resize(int newSize) {
		LinkedList<HashElement<K,V>>[] newarray = 
				(LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];
		for (int i=0; i< newSize; i++)
			newarray[i] = new LinkedList<HashElement<K,V>>();
		for (K key : this) {
			V val = getValue(key);
			HashElement<K,V> newhe = new HashElement<K,V>(key, val);
			int hashCode = key.hashCode();
			hashCode = hashCode & 0x7FFFFFFF;
			hashCode = hashCode % newSize;
			newarray[hashCode].add(newhe);
		}
		harray = newarray;
		tablesize = newSize;
	}

	@Override
	public Iterator<K> iterator() {
		return new KeyIteratorHelper<K>();
	}

	class KeyIteratorHelper<T> implements Iterator<T> {
		T[] keys;
		int currentposition;

		@SuppressWarnings("unchecked")
		public KeyIteratorHelper() {
			keys = (T[]) new Object[numElements];
			int p = 0;
			for (int i = 0; i<tablesize; i++) {
				LinkedList<HashElement<K,V>> llist = harray[i];
				for (HashElement<K,V> he : llist) {
					keys[p++] = (T) he.key;
				}
			}
			currentposition = 0;
		}

		public boolean hasNext() {
			return currentposition < keys.length;
		}

		public T next() {
			if (!hasNext())
				return null;
			return keys[currentposition++];
		}

		public void remove() {
			throw new UnsupportedOperationException("Cannot remove from the"
					+ " hash while iterating");

		}

	}







}
