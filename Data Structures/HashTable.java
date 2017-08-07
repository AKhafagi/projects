package edu.sdsu.cs.datastructures;

import java.util.*;

/**
 * Created by Anas on 4/3/17.
 */
@SuppressWarnings("ALL")
public class HashTable<K extends Comparable<K>, V> extends AbstractMap<K, V> implements Iterable<K> {
    private int tableSize;
    private LinkedList<Node<K, V>>[] table;
    private final double maxLoadFactor;

    public HashTable(int size) {
        this(size, 0.75);
    }

    public HashTable() {
        this(11, 0.75);
    }

    public HashTable(int size, double loadFactor) {
        this.tableSize = size;
        table = (LinkedList<Node<K, V>>[]) new LinkedList[tableSize];
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<>();
        maxLoadFactor = loadFactor;
        currentSize = 0;
    }

    public HashTable(Map<? extends K, ? extends V> t) {
        this();
        if (t == null) {
            throw new NullPointerException("The Specified map is null");
        }
        putAll(t);
    }


    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("The Specified key is null and this map does not permit null keys");
        int index = key.hashCode() & 0x7FFFFFFF % tableSize;
        for (Node<K, V> k : table[index]) {
            if (k.key.compareTo((K) key) == 0)
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null)
            throw new NullPointerException("The Specified value is null and this map does not permit null values");
        for (LinkedList<Node<K, V>> LL : table) {
            for (Node<K, V> k : LL) {
                if (k.value.equals(value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null)
            throw new NullPointerException("The Specified key is null and this map does not permit null keys");
        int index = key.hashCode() & 0x7FFFFFFF % tableSize;
        Node<K, V> temp = new Node<>((K) key, null);
        for (Node<K, V> k : table[index]) {
            if (k.compareTo(temp) == 0)
                return k.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new NullPointerException("The Specified key is null and this map does not permit null keys");
        if (value == null)
            throw new NullPointerException("The Specified value is null and this map does not permit null values");
        if (getLoadFactor() >= maxLoadFactor)
            resize();
        Node<K, V> node = new Node<>(key, value);
        V temp = null;
        int index = key.hashCode() & 0x7FFFFFFF % tableSize;
        for (Node<K, V> k : table[index]) {
            if (k.compareTo(node) == 0) {
                temp = k.setValue(value);
                return temp;
            }
        }
        table[index].add(node);
        currentSize++;
        return null;
    }

    private void resize() {
        int newTableSize = tableSize * 2;
        LinkedList<Node<K, V>>[] temp = (LinkedList<Node<K, V>>[]) new LinkedList[newTableSize];
        for (int i = 0; i < temp.length; i++)
            temp[i] = new LinkedList<>();
        for (K key : this) {
            V value = get(key);
            Node<K, V> node = new Node<>(key, value);
            int index = key.hashCode() & 0x7FFFFFFF % newTableSize;
            temp[index].add(node);
        }
        table = temp;
        tableSize = newTableSize;
    }

    private double getLoadFactor() {
        return (double) currentSize / tableSize;
    }

    @Override
    public V remove(Object key) {
        if (key == null)
            throw new NullPointerException("The Specified key is null and this map does not permit null keys");
        int index = key.hashCode() & 0x7FFFFFFF % tableSize;
        for (Node<K, V> k : table[index])
            if (k.key.compareTo((K) key) == 0) {
                if (table[index].remove(k))
                    currentSize--;
                return k.value;
            }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null)
            throw new NullPointerException("The Specified map is null");
        for (Entry<? extends K, ? extends V> k : m.entrySet()) {
            put(k.getKey(), k.getValue());
        }
    }

    @Override
    public void clear() {
        for (LinkedList<Node<K, V>> ll : table)
            ll.clear();
        currentSize = 0;

    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<>();
        for (LinkedList<Node<K, V>> ll : table)
            for (Node<K, V> k : ll)
                set.add(k.key);
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> col = new LinkedList<>();
        for (LinkedList<Node<K, V>> ll : table)
            for (Node<K, V> k : ll)
                col.add(k.value);
        return col;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new LinkedHashSet<>();
        for (LinkedList<Node<K, V>> ll : table)
            for (Node<K, V> k : ll)
                set.add(k);
        return set;
    }

    @Override
    public Iterator<K> iterator() {
        return keyIterator();
    }

    private class Node<K extends Comparable<K>, V> implements Map.Entry<K, V>, Comparable<Node<K, V>> {
        final K key;
        V value;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key +
                    ", value=" + value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return this.key.compareTo(o.key);
        }

    }

    private class KeyIteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int index;

        public KeyIteratorHelper() {
            keys = (T[]) new Object[currentSize];
            keys = (T[]) keySet().toArray();
            Arrays.sort(keys);
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < keys.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return keys[index++];

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported by this iterator");

        }
    }

    private class ValueIteratorHelper<T> implements Iterator<T> {
        Iterator<K> iter;

        public ValueIteratorHelper() {
            iter= keyIterator();
        }

        @Override
        public boolean hasNext() {
           return iter.hasNext();
        }

        @Override
        public T next() {
           return (T) get(iter.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported by this iterator");

        }
    }

    private class NodeIterator<T> implements Iterator<T> {
        final T[] nodes;
        int index;

        public NodeIterator() {
            nodes = (T[]) entrySet().toArray();
            index = 0;
            Arrays.sort(nodes);
        }

        @Override
        public boolean hasNext() {
            return index < nodes.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return nodes[index++];

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported by this iterator");

        }
    }

    public Iterator<Node<K, V>> nodeIterator() {
        return new NodeIterator<>();
    }

    public Iterator<K> keyIterator() {
        return new KeyIteratorHelper<>();
    }

    public Iterator<V> valueIterator() {
        return new ValueIteratorHelper<>();
    }

    public static void main(String[] args) {
        int n = 100;

        HashTable<String, Integer> ht = new HashTable<>(n);
        ht.put("Anas",21);
        ht.put("Danila",20);
        ht.put("jack",40);
        System.out.println(ht.get("Anas"));
        System.out.println(ht.containsValue(21));

  }
}
