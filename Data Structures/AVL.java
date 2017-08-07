import java.util.*;
/**
 * Created by Anas Khafagi on 4/3/17.
 */
/**
 * @author: Anas Khafagi
 * Date: Monday April 3rd 2017
 * Purpose: A Fully Optimized balanced AVL Tree That implements the Map Interface.
 * Interfaces: Map, Iterator, Iterable, Comparable, Map.Entry
 *
 * <Code>AVL</Code> Class Creates an AVL balanced Binary Search Tree implementing the Map Interface.
 * Inserts elements by mapping <code>K</code> Keys to <code>V</code> Values.
 * This Binary Search Tree is balanced by AVL and does not allow Null <code>K,V</code> values.
 * @param <K> The type of the key to map the values to.
 * @param <V> the type of the value to be mapped to a key.
 */
@SuppressWarnings("ALL")
public class AVL<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private TreeNode<K, V> root;

    /**
     * Creates an empty <code>AVL</code> object with <code>TreeNode</code> root set to null
     * <code>currentSize</code> equal to 0.
     */
    public AVL() {
        root = null;
        currentSize = 0;
    }

    /**
     * Creates a new <code>AVL</code> object the same mappings as the given <code>Map</code> m.
     * @param t  the map whose mappings are to be placed in this map.
     */
    public AVL(Map<? extends K, ? extends V> t) {
        this();
        if (t == null)
            throw new NullPointerException("the specified map is null.");
        putAll(t);
    }

    /**
     * Recursively Returns the max height of this Tree.
     *
     * @return the Height of the tree plus one for the root.
     */
    public int height() {
        return max(height(root.left), height(root.left)) + 1;
    }

    /**
     * helper method that Returns height of the node.
     *
     * @return the Height of the node
     * 0: if the node is null.
     */
    private int height(TreeNode<K, V> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    /**
     * returns the max of two integers
     *
     * @param lhs parameter 1
     * @param rhs parameter 2
     * @return returns max of the two @params.
     */
    private int max(int lhs, int rhs) {
        return Math.max(lhs, rhs);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
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
        boolean temp;
        if (node == null)
            return false;
        if (node.value.equals(value))
            return true;
        temp = containsVal(node.left, value);
        if (temp)
            return true;
        temp = containsVal(node.right, value);
        return temp || temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
        return containsVal(root, (V) value);
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

    /**
     * {@inheritDoc}
     */
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
     * @param root the current node.
     * @param temp the node searching for in the Tree.
     * @return the value mapped to the key
     * Null: if the key does not map to any value.
     */
    private V get(TreeNode<K, V> root, TreeNode<K, V> temp) {

        if (root.compareTo(temp) > 0) {
            if (root.left == null)
                return null;
            return get(root.left, temp);
        } else if (root.compareTo(temp) < 0) {
            if (root.right == null)
                return null;
            return get(root.right, temp);
        }
        return root.value;
    }

    /**
     * {@inheritDoc}
     */
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
     * Updates the height of the nodes in the tree after insertion
     * Checks if the tree is unbalanced after the insertion.
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
        updateHeight(node);
        checkBalance(node);
        return null;
    }

    /**
     * Checks if the tree is unbalanced and fixes it by rotating the nodes to perserve
     * the balance of the tree.
     *
     * @param node the node to check the balance of.
     */
    private void checkBalance(TreeNode<K, V> node) {
        if (node == null)
            return;
        int balance = -height(node.left) + height(node.right);
        if (Math.abs(balance) > 1) {
            if (balance < 0) {
                balance = -height(node.left.left) + height(node.left.right);
                if (balance > 0)
                    leftRightRotate(node);
                else
                    rightRotate(node);
            } else if (balance > 0) {
                balance = -height(node.right.left) + height(node.right.right);
                if (balance < 0)
                    rightLeftRotate(node);
                else
                    leftRotate(node);
            }
        }
        checkBalance(node.parent);
    }

    /**
     * right rotates the nodes at the median
     * left rotates the nodes to balance the tree.
     *
     * @param node the node to rotate at.
     */
    private void rightLeftRotate(TreeNode<K, V> node) {
        rightRotate(node.right);
        leftRotate(node);
    }

    /**
     * left rotates the nodes at the median
     * right rotates the nodes to balance the tree.
     *
     * @param node the node to rotate at.
     */
    private void leftRightRotate(TreeNode<K, V> node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    /**
     * right rotates the nodes to balance the tree.
     *
     * @param node the node to rotate at.
     */
    private void rightRotate(TreeNode<K, V> node) {
        TreeNode<K, V> temp = node.left;
        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
            node.left.isLeftChild = true;
        }
        if (node.parent == null) {
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
                temp.isLeftChild = false;
            }
        }
        temp.right = node;
        node.isLeftChild = false;
        node.parent = temp;
        updateHeight(node);
    }

    /**
     * left rotates to balance the tree.
     *
     * @param node the node to rotate at.
     */
    private void leftRotate(TreeNode<K, V> node) {
        TreeNode<K, V> temp = node.right;
        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        if (node.parent == null) {
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
                temp.isLeftChild = false;
            }
        }
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
        updateHeight(node);
    }

    /**
     * Updates the height of the nodes after insertion and removal.
     *
     * @param node the node to update the height of.
     */
    private void updateHeight(TreeNode<K, V> node) {
        if (node == null)
            return;
        node.height = max(height(node.left), height(node.right)) + 1;
        updateHeight(node.parent);
    }

    /**
     * {@inheritDoc}
     */
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
            updateHeight(parent);
            checkBalance(parent);
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


    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> k : m.entrySet()) {
            put(k.getKey(), k.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
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


    /**
     * {@inheritDoc}
     */
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


    /**
     * {@inheritDoc}
     */
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
    private void addNodesSet(TreeNode<K, V> node, Set<Map.Entry<K, V>> set) {
        if (node == null)
            return;
        addNodesSet(node.left, set);
        set.add(new TreeNode<>(node.key, node.value));
        addNodesSet(node.right, set);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new LinkedHashSet<>();
        addNodesSet(root, set);
        return set;
    }

    /**
     * Checks if the tree is balanced in the current state.
     *
     * @return true: if the tree is balanced.
     * false: if the tree is unbalanced.
     */
    public boolean isBalanced() {
        return checkTreeBalance(root);
    }

    /**
     * Checks if the tree is balanced in the current state
     *
     * @param root the root of the tree
     * @return true: if the tree is balanced.
     * false: if the tree is unbalanced.
     */
    private boolean checkTreeBalance(TreeNode<K, V> root) {
        int result = isBalanced(root);
        return result >= 0;
    }

    /**
     * checks if the tree is balanced in current state by comparing the height of
     * the left and right subtrees.
     *
     * @param root the root of the tree.
     * @return 0, positive: if the tree is balanced.
     * negative: if the tree is unbalanced.
     */
    private int isBalanced(TreeNode<K, V> root) {
        if (root == null) {
            return 0;
        }
        int leftH = isBalanced(root.left);
        if (leftH == -1) {
            return -1;
        }
        int rightH = isBalanced(root.right);
        if (rightH == -1) {
            return -1;
        }
        int diff = leftH - rightH;
        if (Math.abs(diff) > 1) {
            return -1;
        }
        return 1 + Math.max(leftH, rightH);
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
    class TreeNode<K extends Comparable<K>, V> implements Map.Entry<K, V>, Comparable<TreeNode<K, V>> {
        K key;
        V value;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> parent;
        boolean isLeftChild;
        int height;

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
            height = 1;
        }

        public TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "key=" + key +
                    ", value=" + value + ", height=" + height;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public K getKey() {
            return key;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * {@inheritDoc}
         */
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

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(TreeNode<K, V> o) {
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

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return index < keys.length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return keys[index++];

        }

        /**
         * {@inheritDoc}
         */
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

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return index < vals.length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return vals[index++];

        }

        /**
         * {@inheritDoc}
         */
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

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return index < nodes.length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("the iteration has no more elements");
            return nodes[index++];

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported by this iterator");

        }
    }

}
