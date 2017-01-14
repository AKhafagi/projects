package edu.sdsu.cs.datastructures;

public class bSTNode<K, V> {
	protected K key;
	protected V value;
	bSTNode<K, V> next;
	bSTNode<K, V> prev;
	public bSTNode(K key, V value) {
		this.value = value;
		this.key = key;
		this.next = null;
		this.prev = null;
	}
	
	public bSTNode(K key, V value, bSTNode<K, V> next,
	bSTNode<K, V> prev) {
		this.value = value;
		this.key = key;
		this.next = next;
		this.prev = prev;
	}
	public int CompareTo(bSTNode<K,V> node){
		return ((Comparable<K>)key).compareTo((K) node.key);
	}

}
	
