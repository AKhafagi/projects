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

	LinkedList<HashElement<K,V>>[] array;
	int size;
	int numElements;
	double maxLoadFactor;

	@SuppressWarnings("unchecked")
	public Hash(int size) {
		this.size= size;
		array = (LinkedList<HashElement<K,V>>[]) new LinkedList[size];
		for (int i=0; i<size; i++)
			array[i] = new LinkedList<HashElement<K,V>>();
		maxLoadFactor = 0.75;
	}

	@Override
	public boolean add(K key, V value) {
		// do we need to resize
		if (loadFactor() > maxLoadFactor) {
			int newSize = size * 2;
			resize(newSize);
		}
		int hashCode = key.hashCode();
		hashCode = hashCode & 0x7FFFFFFF;
		hashCode = hashCode % size;
		//hashCode = hashCode & size - 1; // only works if size is multiple of 2!
		HashElement<K,V> newhe = new HashElement<K,V>(key, value);
		array[hashCode].add(newhe);
		numElements++;
		return true;
	}

	@Override
	public boolean remove(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode & 0x7FFFFFFF;
		hashCode = hashCode % size;
		for (HashElement<K,V> he : array[hashCode]) {
			if (((Comparable<HashElement<K, V>>)he).compareTo(
					new HashElement<K,V>(he.key, he.value)) == 0)
				if (array[hashCode].remove(he) != null) {
					numElements--;
					return true;
				}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode & 0x7FFFFFFF;
		hashCode = hashCode % size;
		for (HashElement<K,V> he : array[hashCode]) {
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

	@Override
	public double loadFactor() {
		return (double) numElements/size;
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
		array = newarray;
		size = newSize;
	}

	@Override
	public Iterator<K> keys() {
		return new KeyIteratorHelper<K>();
	}

	@Override
	public Iterator<K> iterator() {
		return new KeyIteratorHelper<K>();
	}
	
	@Override
	public Iterator<V> values() {
		return new ValueIteratorHelper<V>();
	}

	class KeyIteratorHelper<T> implements Iterator<T> {
		T[] keys;
		int currentposition;

		@SuppressWarnings("unchecked")
		public KeyIteratorHelper() {
			keys = (T[]) new Object[numElements];
			int p = 0;
			for (int i = 0; i<size; i++) {
				LinkedList<HashElement<K,V>> llist = array[i];
				for (HashElement<K,V> he : llist) {
					keys[p++] = (T) he.key;
				}
				currentposition = 0;
			}
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
	
	
	class ValueIteratorHelper<T> implements Iterator<T> {
		T[] values;
		int currentposition;

		@SuppressWarnings("unchecked")
		public ValueIteratorHelper() {
			values = (T[]) new Object[numElements];
			int p = 0;
			for (int i = 0; i<size; i++) {
				LinkedList<HashElement<K,V>> llist = array[i];
				for (HashElement<K,V> he : llist) {
					values[p++] = (T) he.value;
				}
				currentposition = 0;
			}
		}

		public boolean hasNext() {
			return currentposition < values.length;
		}

		public T next() {
			if (!hasNext())
				return null;
			return values[currentposition++];
		}

		public void remove() {
			throw new UnsupportedOperationException("Cannot remove from the"
					+ " hash while iterating");

		}

	}




}
