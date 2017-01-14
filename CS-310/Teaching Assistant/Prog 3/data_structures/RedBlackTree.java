package data_structures;

import java.util.Iterator;


public class RedBlackTree<K, V> implements RedBlackI<K, V> {

	Node<K, V> root;
	int size = 0;

	@SuppressWarnings("hiding")
	class Node<K,V> {
		K key;
		V value;
		Node<K, V> left, right, parent;
		boolean isLeftChild;
		boolean black; // if it is true we are black otherwise we are red
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = right = parent = null;
			black = false; // new nodes are red
			isLeftChild = false;
		}
	}


	@Override
	public void add(K key, V value) {
		Node<K,V> newNode = new Node<K,V>(key, value);
		if (root == null) {
			root = newNode;
			newNode.black = true;
			size++;
			return;
		}
		add(root, newNode);
		root.black = true;
	}

	@SuppressWarnings("unchecked")
	public void add(Node<K, V> parent, Node<K, V> newNode) {
		if (((Comparable<K>)newNode.key).compareTo(parent.key) > 0) {
			// object is bigger than parent. Add to the right side.
			if (parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = false;
				size++;
			} else {
				add(parent.right, newNode);
			}
		}
		else {
			// add to the left side
			if (parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = true;
				size++;
			} else {
				add(parent.left, newNode);
			}
		}
		checkColor(newNode);
	}

	/**
	 * Check the color balance of this node.
	 * 
	 * We need to go from this node to root and make sure there are not
	 * two consecutive red nodes 
	 *    
	 * @param node
	 * @throws Exception 
	 */
	public void checkColor(Node<K,V> node) {
		if (node == root)
			return;
		if (!node.black && !node.parent.black) {
			// this is a violation, two consecutive reds
			correctTree(node);
		}
		
		// If we correct the tree and make this the root node, then node.parent
		// becomes null!
		if (node.parent != null)
			checkColor(node.parent);
	}

	/**
	 * We need to correct this node. If the aunt is red
	 * do a color flip or else rotate.
	 * @param node
	 */
	public void correctTree(Node<K,V> node) {
		if (node.parent.isLeftChild) {
			// aunt is parent.right
			if (node.parent.parent.right == null || node.parent.parent.right.black) {
				//black aunt, rotate
				rotate(node);
			} else {
				if (node.parent.parent.right != null)
					node.parent.parent.right.black = true;
				node.parent.parent.black = false;
				node.parent.black =  true;		
			}
		} else {
			// aunt is parent.left
			if (node.parent.parent.left == null || node.parent.parent.left.black) {
				//black aunt, rotate
				rotate(node);
			} else {
				if (node.parent.parent.left != null)
					node.parent.parent.left.black = true;
				node.parent.parent.black = false;
				node.parent.black =  true;		
			}
		}
	}

	/**
	 * Figure out which rotation I need to fix the tree.
	 * There are four types of rotation:
	 *    <ul>
	 *    <li>Left child of left child: right rotate
	 *    <li>Left child of right child: right left rotate
	 *    <li>Right child of right child: left rotate
	 *    <li>Right child of left child: left right rotate
	 *    </ul>
	 * Afterwards we fix the color so parent is black and children
	 * are red
	 *  
	 * @param node The node to check
	 */
	public void rotate(Node<K,V> node) {
		if (node.isLeftChild) {
			if (node.parent.isLeftChild) {
				rightRotate(node.parent.parent);
				// after a right rotate, we become red, our parent becomes black, and our sibling (node.parent.right) is red
				node.black = false;
				node.parent.black = true;
				if (node.parent.right != null)
					node.parent.right.black = false;
			} else {
				rightLeftRotate(node.parent.parent);
				// after a right left, the node we start at is the new parent
				// and is black
				node.black = true;
				node.right.black = false;
				node.left.black = false;
			}
		} else {
			if (node.parent.isLeftChild) {
				leftRightRotate(node.parent.parent);
				// after a left right, the node we start at is the new parent
				// and is black
				node.black = true;
				node.right.black = false;
				node.left.black = false;
			} else {
				leftRotate(node.parent.parent);
				// after a left rotate, we become red, our parent becomes black, and our sibling (node.parent.left) is red
				node.black = false;
				node.parent.black = true;
				if (node.parent.left != null)
					node.parent.left.black = false;
			}
		}
	}

	@Override
	public boolean contains(K obj) {
		return contains(obj, root);
	}

	@SuppressWarnings("unchecked")
	public boolean contains(K toFind, Node<K, V> node) {
		if (node == null)
			return false;
		if (((Comparable<K>)node.key).compareTo(toFind) == 0)
			return true;
		if (((Comparable<K>)node.key).compareTo(toFind) > 0)
			return contains(toFind, node.left);
		return contains(toFind, node.right);			
	}

	@Override
	public V getValue(K key) {
		Node<K, V> node = new Node<K, V>(key, null);
		return getValue(node, root);
	}

	@SuppressWarnings("unchecked")
	public V getValue(Node<K, V> toFind, Node<K, V> node) {
		if (node == null)
			return null;
		if (((Comparable<K>)node.key).compareTo(toFind.key) == 0)
			return node.value;
		if (((Comparable<K>)node.key).compareTo(toFind.key) < 0)
			return getValue(toFind, node.left);
		return getValue(toFind, node.right);			
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int height() {
		if (root == null)
			return 0;
		return height(root)-1;
	}

	public int height(Node<K,V> node) {
		if (node == null)
			return 0;

		int leftheight = height(node.left)+1;
		int rightheight = height(node.right)+1;

		if (leftheight > rightheight)
			return leftheight;
		return rightheight;
	}

	
	public int blackNodes(Node<K,V> node) {
		if (node == null)
			return 1; // nulls are black!
		
		int rightBlacks = blackNodes(node.right);
		int leftBlacks = blackNodes(node.left);
		
		if (leftBlacks != rightBlacks) {
			System.err.println("There are an uneven number of blacks underneath" + node.key + " left: " + leftBlacks + " right: " + rightBlacks);
			if (leftBlacks < rightBlacks)
				leftBlacks = rightBlacks;
		}
		
		if (node.black)
			leftBlacks++;
		
		return leftBlacks;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}



	public void leftRotate(Node<K,V> node) {
		Node<K,V> temp = node.right;
		node.right = temp.left;

		if (node.right != null) {
			node.right.parent = node;
			node.right.isLeftChild = false;
		}

		// check if we are the root node
		if (node.parent == null) {
			root = temp;
			temp.parent = null;
		} 
		else {
			temp.parent = node.parent;
			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			} else {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
		}
		temp.left = node;
		node.isLeftChild = true;
		node.parent = temp;
	}

	public void rightRotate(Node<K,V> node) {
		Node<K,V> temp = node.left;
		node.left = temp.right;

		if (node.left != null) {
			node.left.parent = node;
			node.left.isLeftChild = true;
		}

		// check if we are the root node
		if (node.parent == null) {
			root = temp;
			temp.parent = null;
		} else {
			temp.parent = node.parent;
			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			} else {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
		}

		temp.right = node;
		node.isLeftChild = false;
		node.parent = temp;
	}

	public void leftRightRotate(Node<K,V> node) {
		leftRotate(node.left);
		rightRotate(node);
	}

	public void rightLeftRotate(Node<K,V> node) {
		rightRotate(node.right);
		leftRotate(node);
	}


	@Override
	public void print() {
		printtree(root, 0);
	}


	public void printtree(Node<K,V> node, int width) {
		if (node == null)
			return;

		printtree(node.left, width+1);
		for (int i=0; i<width; i++)
			System.out.print(".");
		String color = "red";
		if (node.black)
			color = "black";
		
		System.out.println(node.key + " : " + node.value + " : " + color);
		printtree(node.right,width+1);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	class IteratorHelper<K> implements Iterator<K> {

		K[] keys;
		int index;

		public IteratorHelper() {
			// populate the keys array
			keys = (K[]) new Object[size];
			index = 0;
			getKey((Node<K,V>) root); // the cast is required because Node is in the outer class
			index = 0;
		}

		public void getKey(Node<K, V> node) {
			if (node == null)
				return;
			getKey(node.left);
			keys[index++]=node.key;
			getKey(node.right);
		}

		@Override
		public boolean hasNext() {
			return index < keys.length;
		}

		@Override
		public K next() {
			return keys[index++];
		}

	}
	
	public static void main(String args[]){
			RedBlackTree<Integer,String> test = new RedBlackTree();
			
			for(int i=0; i< 10;i++){
				test.add(i, "Suh");
			test.print();
			System.out.println("Done!!!");
			}
		}
	



}
