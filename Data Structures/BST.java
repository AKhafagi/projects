package edu.sdsu.cs.datastructures;

import java.util.*;
/**
 * Created by Anas Khafagi on 4/3/17.
 */
/**
 * @author: Anas Khafagi
 * Date: Monday April 3rd 2017
 * Purpose: A Fully Optimized Un-balanced Binary Search Tree That implements the Map Interface.
 * Interfaces: Map, Iterator, Iterable, Comparable, Map.Entry
 *
 * <Code>edu.sdsu.cs.datastructures.BST</Code> Class Creates a Binary Search Tree implementing the Map Interface.
 * Inserts elements by mapping <code>K</code> Keys to <code>V</code> Values.
 * This Binary Search Tree is Un-balanced and does not allow Null <code>K,V</code> values.
 * @param <K> The type of the key to map the values to.
 * @param <V> the type of the value to be mapped to a key.

 */
@SuppressWarnings("ALL")
public class BST<K extends Comparable<K>, V> extends AbstractMap<K, V> implements Iterable<K> {
    private TreeNode<K, V> root;

    /**
     * Creates an empty <code>edu.sdsu.cs.datastructures.BST</code> object with <code>TreeNode</code> root set to null
     * <code>currentSize</code> equal to 0.
     */
    public BST() {
        root = null;
        currentSize = 0;
    }

    /**
     * Creates a new <code>edu.sdsu.cs.datastructures.BST</code> object the same mappings as the given <code>Map</code> t.
     * @param t the map whose mappings are to be placed in this map.
     */
    public BST(Map<? extends K, ? extends V> t) {
        this();
        if (t == null)
            throw new NullPointerException("the specified map is null.");
        putAll(t);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if(isEmpty())
            return false;

        return containsVal(root, (V) value);
    }

    /**
     * Recursive Helper Method to check if Binary Search Tree Contains Value in O(N) time.
     *
     * @param node  The current Node
     * @param value the Value to search For
     * @return True: if value found.
     * False: if value not found.
     */
    private boolean containsVal(TreeNode<K, V> node, V value) {
        if (node == null)
            return false;
        if (node.value.equals(value))
            return true;
        if (containsVal(node.left, value))
            return true;
        return containsVal(node.right, value);
    }

    /**
     * Recursively traverses and prints the Nodes in the Binary Search Tree.
     * Prints the Nodes by the level they are in the Tree
     *
     * @param node  The Node to print starting from the root of the Tree.
     * @param width the level of the node in the Tree.
     */
    private void InorderTraversal(TreeNode<K, V> node, int width) {
        if (node == null)
            return;
        InorderTraversal(node.left, width + 1);
        for (int i = 0; i < width; i++)
            System.out.print(".");
        System.out.println(node);
        InorderTraversal(node.right, width + 1);
    }

    /**
     * Prints the nodes of the Binary Search Tree in order.
     */
    public void display() {
        System.out.println();
        InorderTraversal(root, 0);
        System.out.println();
    }

    @Override
    public V get(Object key) {
        if (isEmpty())
            return null;
        TreeNode<K, V> temp = new TreeNode<>((K) key, null);
        return get(root, temp);
    }

    /**
     * Recursive Helper method to return the Value mapped to a certain key if found.
     *
     * @param node the current node.
     * @param temp the node searching for in the Tree.
     * @return the value mapped to the key
     * Null: if the key does not map to any value.
     */
    private V get(TreeNode<K, V> node, TreeNode<K, V> temp) {
        if(node == null)
            return null;
        if (node.compareTo(temp) > 0) {
            return get(node.left, temp);
        } else if (node.compareTo(temp) < 0) {
            return get(node.right, temp);
        }
        return node.value;
    }

    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new NullPointerException("Map does not allow null keys");
        if (isEmpty()) {
            root = new TreeNode<>(key, value);
            root.parent = null;
            currentSize++;
            return null;
        }
        TreeNode<K, V> toAdd = new TreeNode<>(key, value);
        V temp = put(root, toAdd);
        currentSize++;
        return temp;
    }

    /**
     * Recursive Helper method to add a Node to the Tree.
     *
     * @param node  The Current node.
     * @param toAdd The node to add to the Tree.
     * @return the value mapped to the key.
     * Null: if the tree does not contain that key.
     */
    private V put(TreeNode<K, V> node, TreeNode<K, V> toAdd) {
        if (node.compareTo(toAdd) > 0) {
            if (node.left == null) {
                node.left = toAdd;
                toAdd.isLeftChild = true;
                toAdd.parent = node;

            } else
                return put(node.left, toAdd);
        } else if (node.compareTo(toAdd) < 0) {
            if (node.right == null) {
                node.right = toAdd;
                toAdd.isLeftChild = false;
                toAdd.parent = node;

            } else
                return put(node.right, toAdd);
        } else {
            currentSize--;
            return node.setValue(toAdd.value);
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        if (isEmpty())
            return null;
        V temp = removeNode(root, (K) key);
        if (currentSize == 0)
            root = null;
        return temp;
    }

    /**
     * Recursive Helper method to remove the value mapped to a key in the Tree.
     *
     * @param node The Current node.
     * @param key  the key which maps to the value to be removed
     * @return the value mapped to the key which was removed.
     * Null: if the tree does not contain that key.
     */
    private V removeNode(TreeNode<K, V> node, K key) {
        V temp = null;
        if (node.key.compareTo(key) > 0) {
            if (node.left != null)
                return removeNode(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            if (node.right != null)
                return removeNode(node.right, key);
        } else {
            temp = node.value;
            TreeNode<K, V> parent = node.parent;
            if (node.kids() == 1) {
                if (node.left != null) {
                    node.left.parent = parent;
                    if (!node.isLeftChild && parent != null) {
                        parent.right = node.left;
                        node.left.isLeftChild = false;
                    } else if (node.isLeftChild && parent != null) {
                        parent.left = node.left;
                        node.left.isLeftChild = true;
                    }
                    node = node.left;
                } else {
                    node.right.parent = node.parent;
                    if (!node.isLeftChild && parent != null) {
                        parent.right = node.right;
                        node.right.isLeftChild = false;
                    } else if (node.isLeftChild && parent != null) {
                        parent.left = node.right;
                        node.right.isLeftChild = true;
                    }
                    node = node.right;
                }
            } else if (node.kids() == 2) {
                TreeNode<K, V> min = findMinimumNode(node.right);
                node.set(min);
                if (!min.isLeftChild && min.parent != null)
                    min.parent.right = null;
                else if (min.isLeftChild && min.parent != null)
                    min.parent.left = null;
            } else {
                if (!node.isLeftChild && parent != null)
                    parent.right = null;
                else if (node.isLeftChild && parent != null)
                    parent.left = null;
                node = null;
            }
        }
        if (node != null && node.parent == null) {
            root = node;
            root.isLeftChild = false;
        }
        currentSize--;
        return temp;

    }

    /**
     * Recursively finds the Inorder Succesor of the current Node
     *
     * @param node the node to find the In order Successor for.
     * @return the In order Successor for the node.
     */
    private TreeNode<K, V> findMinimumNode(TreeNode<K, V> node) {
        if (node.left == null)
            return node;
        else {
            return findMinimumNode(node.left);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> k : m.entrySet()) {
            put(k.getKey(), k.getValue());
        }
    }

    @Override
    public void clear() {
        root = null;
        currentSize = 0;

    }

    /**
     * Recursively adds all the keys in the Tree to a <code>Set</code>
     *
     * @param node the Node of which the key will be added to the set.
     * @param set  the <code>Set</code> which contains all the keys.
     */
    private void addKeysSet(TreeNode<K, V> node, Set<K> set) {
        if (node == null)
            return;
        addKeysSet(node.left, set);
        set.add(node.key);
        addKeysSet(node.right, set);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<>();
        addKeysSet(root, set);
        return set;
    }

    /**
     * Recursively adds all the values mapped to a key in the Tree to a <code>Collection</code>
     *
     * @param node the Node of which the value will be added to the set.
     * @param col  the <code>Collection</code> which contains all the values mapped to keys in the Tree.
     */
    private void addValsCollec(TreeNode<K, V> node, Collection<V> col) {
        if (node == null)
            return;
        addValsCollec(node.left, col);
        col.add(node.value);
        addValsCollec(node.right, col);
    }

    @Override
    public Collection<V> values() {
        Collection<V> ll = new LinkedList<>();
        addValsCollec(root, ll);
        return ll;
    }


    /**
     * Recursively adds all pair of keys and values in the Tree to a <code>Set</code>
     *
     * @param node the Node of which  pair of keys and values will be added to the set.
     * @param set  the <code>Collection</code> which contains all the  pair of keys and values in the Tree.
     */
    private void addNodesSet(TreeNode<K, V> node, Set<Entry<K, V>> set) {
        if (node == null)
            return;
        addNodesSet(node.left, set);
        set.add(new TreeNode<>(node.key, node.value));
        addNodesSet(node.right, set);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new LinkedHashSet<>();
        addNodesSet(root, set);
        return set;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIteratorHelper<>();
    }

    /**
     * Returns a new <code>Tree Node</code> <code>Iterator</code>
     *
     * @return <code>Tree Node</code> <code>Iterator</code>
     */
    public Iterator<TreeNode<K, V>> nodeIterator() {
        return new NodeIterator<>();
    }

    /**
     * Returns a new <code>K</code> keys <code>Iterator</code>
     *
     * @return <code>K</code> keys <code>Iterator</code>
     */
    public Iterator<K> keyIterator() {
        return new KeyIteratorHelper<>();
    }

    /**
     * Returns a new <code>V</code> values <code>Iterator</code>
     *
     * @return <code>V</code> values <code>Iterator</code>
     */
    public Iterator<V> valueIterator() {
        return new ValueIteratorHelper<>();
    }

    /**
     * Creates a <code>TreeNode</code> object to hold the pair of keys in values in the Tree
     *
     * @param <K> The key to be stored in the Tree
     * @param <V> the value to be mapped to the key in the Tree.
     */
    private class TreeNode<K extends Comparable<K>, V> implements Map.Entry<K, V>, Comparable<TreeNode<K, V>> {
        K key;
        V value;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> parent;
        boolean isLeftChild;

        public TreeNode(K key, V value, TreeNode<K, V> left, TreeNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
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

        /**
         * Returns the number of children the current node has
         *
         * @return the number of children the current node has
         */
        public int kids() {
            int kids = 0;
            if (right != null)
                kids++;
            if (left != null)
                kids++;
            return kids;
        }

        @Override
        public int compareTo(TreeNode<K, V> o) {
            int comp = this.key.compareTo(o.key);
            return this.key.compareTo(o.key);
        }

        /**
         * Copies the information of the <code>TreeNode</code> temp into @this Node.
         */
        public void set(TreeNode<K, V> temp) {
            this.key = temp.key;
            this.value = temp.value;
            this.isLeftChild = temp.isLeftChild;
        }
    }

    /**
     * Creates an <code>Iterator</code> to Iterate through the keys in this Tree
     *
     * @param <T> The type of value this <code>Iterator</code> will iterate through
     */
    private class KeyIteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int index;

        public KeyIteratorHelper() {
            keys = (T[]) new Object[currentSize];
            keys = (T[]) keySet().toArray();
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


    /**
     * Creates an <code>Iterator</code> to Iterate through the values in this Tree
     *
     * @param <T> The type of value this <code>Iterator</code> will iterate through
     */
    private class ValueIteratorHelper<T> implements Iterator<T> {
        final T[] vals;
        int index;

        public ValueIteratorHelper() {
            vals = (T[]) values().toArray();
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < vals.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return vals[index++];

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported by this iterator");

        }
    }

    /**
     * Creates an <code>Iterator</code> to Iterate through the <code>TreeNodes</code> in this Tree
     *
     * @param <T> The type of value this <code>Iterator</code> will iterate through
     */
    private class NodeIterator<T> implements Iterator<T> {
        final T[] nodes;
        int index;

        public NodeIterator() {
            nodes = (T[]) entrySet().toArray();
            index = 0;
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
}