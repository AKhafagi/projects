package edu.sdsu.cs.datastructures;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashTable<K,V> implements Map<K,V> {
	public class MapNode<K, V> {
		protected K key;
		protected V value;
		MapNode<K, V> next;
		MapNode<K, V> prev;
		public MapNode(K key, V value) {
			this.value = value;
			this.key = key;
			this.next = null;
			this.prev = null;
		}
		
		public MapNode(K key, V value, MapNode<K, V> next,
		MapNode<K, V> prev) {
			this.value = value;
			this.key = key;
			this.next = next;
			this.prev = prev;
		}
		public int CompareTo(MapNode<K,V> node){
			return ((Comparable<K>)key).compareTo((K) node.key);
		}

	}
	private LinkedList<MapNode<K,V>>[] list;
	private float maxLoadFactor;
	private int tableSize;
	private int currSize;
	
	
	public HashTable(){
		this(11,0.75f);
	}
	public HashTable(int intialCapactiy, float loadFactor){
		if(intialCapactiy <0 || loadFactor <0){
			throw new IllegalArgumentException("invalid input");
		}
		 tableSize = intialCapactiy;
		list = new LinkedList[tableSize];
		for(int i =0; i< tableSize;i++){
			list[i] = new LinkedList<MapNode<K,V>>();
		}
		
		this.maxLoadFactor = loadFactor;
	}
	public HashTable(int intialCapacity){
		this(11,0.75f);
	}
	public HashTable(Map<? extends K,? extends V> t){
		if(t.equals(null)){
			throw new NullPointerException("if the specified map is null.");
		}
		putAll(t);
	}
	public KeyIteratorHelper<K> keys(){
		return new KeyIteratorHelper<K>();
	}
	
	public ValueIteratorHelper<V> elements(){
		return new ValueIteratorHelper<V>();
	}

	@Override
	public int size() {
		return currSize;
	}

	@Override
	public boolean isEmpty() {
		return currSize==0;
	}

	@Override
	public boolean containsKey(Object key) {
		throwNull(key);
		int index = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(MapNode<K,V> node : list[index]){
			if(node.key.equals(key)){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		throwNull(value);
		for(int i =0; i<tableSize;i++){
			for(MapNode<K,V> node : list[i]){
				if(node.value.equals(value)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		throwNull(key);
		int index = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(MapNode<K,V> node : list[index]){
			if(node.key.equals(key)){
				return node.value;
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		throwNull(key);
		throwNull(value);
		if(isFull()|| loadFactor() >= maxLoadFactor){
			rehash();
		}
		MapNode<K, V> element = new MapNode<K,V>(key,value);
		int index = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		list[index].add(element);
		currSize++;
		return null;
		
	}
	private void rehash(){
		int newCapacity = tableSize *2 +1;
		LinkedList<MapNode<K,V>>[] newTable = new LinkedList[newCapacity];
		for(int i=0; i<newTable.length;i++){
			newTable[i]= new LinkedList<MapNode<K,V>>();
		}
		list = newTable;
		for(int i= 0; i< tableSize;i++){
			for(MapNode node: list[i]){
				MapNode<K,V> element = node;
				int index = (node.key.hashCode() & 0x7FFFFFFF) % newCapacity;
				newTable[index].add(element);
			}
		}
		tableSize = newCapacity;
		list = newTable;
	}
	public boolean isFull(){
		return currSize == tableSize;
	}
	protected double loadFactor(){
		return currSize/tableSize;
	}

	@Override
	public V remove(Object key) {
		throwNull(key);
		int index = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(MapNode<K,V> node : list[index]){
			if(node.key.equals(key)){
				list[index].remove(node);
				currSize--;
				return node.value;
				
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throwNull(m);
		for(Map.Entry<? extends K, ? extends V> element : m.entrySet()){
			put(element.getKey(),element.getValue());
		}
		
	}

	@Override
	public void clear() {
		list= new LinkedList[11];
		for(LinkedList<MapNode<K,V>> LL : list){
			LL = new LinkedList<MapNode<K,V>>();
		}
		
	}


	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	private void throwNull(Object o){
		if(o.equals(null)){
			throw new NullPointerException("the specified input is null");
		}
	}
	protected class KeyIteratorHelper<K> extends IteratorHelper<K>{
		public KeyIteratorHelper(){
			super();
		}

		@Override
		public K next() {
			return (K) nodes[idx++].key;
		}
		
		
		
		}
	protected class ValueIteratorHelper<V> extends IteratorHelper<V>{
		public ValueIteratorHelper(){
			super();
		}
		public V next() {
			return (V) nodes[idx++].value;
		}
		
	}
	abstract class IteratorHelper<E> implements Iterator<E>{
		protected MapNode<K,V> [] nodes;
		protected int idx;
		public IteratorHelper(){
			nodes = new MapNode[currSize];
			idx= 0;
			int j =0;
			for(int i =0; i< tableSize;i++){
				for(MapNode<K,V> n : list[i]){
					nodes[j++] = n;
				}
			}
				nodes =(MapNode<K,V>[]) Sorters.quickSort(nodes,new MapNodeComparator<K,V>());
				
		}
		@Override
		public boolean hasNext() {
			return idx < currSize;
		}

		@Override
		public abstract E next();
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
	}
	protected class MapNodeComparator<K,V> implements Comparator<MapNode<K,V>>{

		@Override
		public int compare(MapNode<K, V> o1, MapNode<K, V> o2) {
			return o1.CompareTo(o2);
		}
		
	}
	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	}
	

