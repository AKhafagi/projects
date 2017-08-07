import java.util.*;

/**
 * Created by Anas on 3/29/17.
 */
@SuppressWarnings("ALL")
public class RedBlackTree<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    TreeNode<K, V> root;

    public RedBlackTree() {
        root = null;
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

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
        if (temp)
            return true;
        return temp;
    }

    @Override
    public boolean containsValue(Object value) {
        return containsVal(root, (V) value);
    }


    private void InorderTraversal(TreeNode<K, V> node, int width) {
        if (node == null)
            return;
        InorderTraversal(node.left, width + 1);
        for (int i = 0; i < width; i++)
            System.out.print(".");
        if (node == root)
            System.out.println("root: " + node);
        else
            System.out.println(node);
        InorderTraversal(node.right, width + 1);
    }

    public void display() {
        System.out.println();
        InorderTraversal(root, 0);
        System.out.println();
    }

    @Override
    public V get(Object key) {
        if (isEmpty())
            return null;
        TreeNode<K, V> temp = new TreeNode<K, V>((K) key, null);
        return get(root, temp);
    }

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

    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new NullPointerException("Map does not allow null keys");
        if (isEmpty()) {
            root = new TreeNode<>(key, value);
            root.parent = null;
            root.black = true;
            currentSize++;
            return null;
        }
        TreeNode<K, V> toAdd = new TreeNode<>(key, value);
        V temp = put(root, toAdd);
        currentSize++;
        root.black = true;
        return temp;
    }

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
        checkColor(toAdd);
        return null;
    }

    private void rightLeftRotate(TreeNode<K, V> node) {
        rightRotate(node.right);
        leftRotate(node);
    }

    private void leftRightRotate(TreeNode<K, V> node) {
        leftRotate(node.left);
        rightRotate(node);
    }

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
    }

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
    }

    private void checkColor(TreeNode<K, V> node) {
        if (node == root)
            return;
        if (!node.black && !node.parent.black) {
            correctTree(node);
        }
        if (node.parent != null)
            checkColor(node.parent);
    }

    private void correctTree(TreeNode<K, V> node) {
        if (node.parent.isLeftChild) {
            if (node.parent.parent.right == null || node.parent.parent.right.black) {
                rotate(node);
            } else {
                if (node.parent.right != null)
                    node.parent.parent.right.black = true;
                node.parent.parent.black = false;
                node.parent.black = true;
            }

        } else {
            if (node.parent.parent.left == null || node.parent.parent.left.black) {
                rotate(node);
            } else {
                if (node.parent.parent.left != null)
                    node.parent.parent.left.black = true;
                node.parent.parent.black = false;
                node.parent.black = true;
            }

        }

    }

    private void rotate(TreeNode<K, V> node) {
        TreeNode<K, V> parent = node.parent;
        TreeNode<K, V> grandParent = node.parent.parent;
        if (node.isLeftChild) {
            if (parent.isLeftChild) {
                rightRotate(grandParent);
                node.black = false;
                node.parent.black = true;
                if (node.parent.right != null)
                    node.parent.right.black = false;
            } else {
                rightLeftRotate(grandParent);
                node.black = true;
                node.right.black = false;
                node.left.black = false;
            }

        } else {
            if (!parent.isLeftChild) {
                leftRotate(grandParent);
                node.parent.black = true;
                node.black = false;
                if (node.parent.left != null)
                    node.parent.left.black = false;
            } else {
                leftRightRotate(grandParent);
                node.black = true;
                node.right.black = false;
                node.left.black = false;
            }
        }
    }

//    private void checkBalance(Node<K, V> node) {
//        if(Math.abs(node.lheight - node.rheight) >1 )
//            rotate(node);
//        else
//            checkBalance(node.parent);
//    }
//
//    private void rotate(Node<K, V> node) {
//       if(node.lheight > node.rheight){
//           if(node.left)
//
//       }
//
//    }

    @Override
    public V remove(Object key) {
        if (isEmpty())
            return null;
        V temp = removeKey(root, (K) key);
        if (currentSize == 0)
            root = null;
        return temp;
    }

    private V removeKey(TreeNode<K, V> node, K key) {
        V temp = null;
        if (node.key.compareTo(key) > 0) {
            if (node.left != null)
                return removeKey(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            if (node.right != null)
                return removeKey(node.right, key);
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
                    fixColors(node.left);
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
                min = null;
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

    private void fixColors(TreeNode<K, V> node) {
        if (!node.parent.black && node.black)
            node.parent.black = true;
        else if (node.parent.black && !node.black)
            node.black = true;
        else if (node.black && node.parent.black) {
            if (node.parent == root)
                return;
            TreeNode<K, V> sis;
            if (node.isLeftChild)
                sis = node.parent.right;
            else
                sis = node.parent.left;
            if (sis != null) {
                if (!sis.black)
                    deleteRotate(sis);
            }

        }


    }

    private void deleteRotate(TreeNode<K, V> sibling) {
        TreeNode<K, V> parent = sibling.parent;
        TreeNode<K, V> node;
        if (sibling.isLeftChild)
            node = parent.right;
        else
            node = parent.left;


    }

    private TreeNode<K, V> findMinimumNode(TreeNode<K, V> node) {
        if (node.left == null)
            return node;
        else {
            return findMinimumNode(node.left);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> k : m.entrySet()) {
            put(k.getKey(), k.getValue());
        }
    }

    @Override
    public void clear() {
        root = null;
        currentSize = 0;

    }

    private void addKeysSet(TreeNode<K, V> node, Set<K> set) {
        if (node == null)
            return;
        addKeysSet(node.left, set);
        set.add(node.key);
        addKeysSet(node.right, set);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<K>();
        addKeysSet(root, set);
        return set;
    }

    private void addValsCollec(TreeNode<K, V> node, Collection<V> set) {
        if (node == null)
            return;
        addValsCollec(node.left, set);
        set.add(node.value);
        addValsCollec(node.right, set);
    }

    @Override
    public Collection<V> values() {
        Collection<V> ll = new LinkedList<V>();
        addValsCollec(root, ll);
        return ll;
    }

    private void addNodesSet(TreeNode<K, V> node, Set<Map.Entry<K, V>> set) {
        if (node == null)
            return;
        addNodesSet(node.left, set);
        set.add(new TreeNode<K, V>(node.key, node.value));
        addNodesSet(node.right, set);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new LinkedHashSet<>();
        addNodesSet(root, set);
        return set;
    }

    public boolean isBalanced() {
        return checkBalance1(root);
    }

    public boolean checkBalance1(TreeNode<K, V> root) {
        int result = isBalanced(root);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int isBalanced(TreeNode<K, V> root) {
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

    class TreeNode<K extends Comparable<K>, V> implements Map.Entry<K, V>, Comparable<TreeNode<K, V>> {
        protected K key;
        protected V value;
        protected TreeNode<K, V> left, right, parent;
        protected boolean isLeftChild, black;

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
            black = false;
        }

        public TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            if (black) {
                if (this.isLeftChild) {
                    return "key=" + key +
                            ", value=" + value + ", color=" + " Black " + "leftChild";
                } else {
                    return "key=" + key +
                            ", value=" + value + ", color=" + " Black " + "rightChild";
                }


            } else if (!black) {
                if (this.isLeftChild) {
                    return "key=" + key +
                            ", value=" + value + ", color=" + " Red " + "leftChild";
                } else {
                    return "key=" + key +
                            ", value=" + value + ", color=" + " Red " + "rightChild";
                }
            }
            return null;
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
            return this.key.compareTo(o.key);
        }

        public void set(TreeNode<K, V> temp) {
            this.key = temp.key;
            this.value = temp.value;
            this.isLeftChild = temp.isLeftChild;
        }
    }

    public int blackNodes(TreeNode<K, V> node) {
        if (node == null)
            return 1; // nulls are black!

        int rightBlacks = blackNodes(node.right);
        int leftBlacks = blackNodes(node.left);

        if (leftBlacks != rightBlacks) {
            System.err.println("There are an uneven number of blacks underneath " + node.key + " left: " + leftBlacks + " right: " + rightBlacks);
            if (leftBlacks < rightBlacks)
                leftBlacks = rightBlacks;
        }

        if (node.black)
            leftBlacks++;

        return leftBlacks;
    }

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

    private class ValueIteratorHelper<T> implements Iterator<T> {
        T[] vals;
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

    public Iterator<TreeNode<K, V>> nodeIterator() {
        return new NodeIterator<TreeNode<K, V>>();
    }

    public Iterator<K> keyIterator() {
        return new KeyIteratorHelper<K>();
    }

    public Iterator<V> valueIterator() {
        return new ValueIteratorHelper<V>();
    }

    private class NodeIterator<T> implements Iterator<T> {
        T[] nodes;
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

    public static void main(String[] args) {
        int n = 10000;
        RedBlackTree<Integer, Integer> bst = new RedBlackTree<>();
        ArrayList<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(i + 1);
        }
        //Collections.shuffle(arr);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            bst.put(arr.get(i), i);
        }
        long end = System.currentTimeMillis();
        System.out.println("the time it took was : " + (double) (end - start) / 1000);
        System.out.println(bst.isBalanced());
        //bst.display();
        System.out.println();
        Set<Integer> set = bst.keySet();
        start = System.currentTimeMillis();
        for (Integer k : set) {
            System.out.println(bst.get(k));
        }
        end = System.currentTimeMillis();
        System.out.println("the time it took was : " + (double) (end - start) / 1000);
        start = System.currentTimeMillis();
        for (Integer k : bst.keySet()) {
            bst.remove(k);
        }
        end = System.currentTimeMillis();
        System.out.println("the time it took was : " + (double) (end - start) / 1000);

    }

}
