package data_structures;

import java.util.Iterator;

import data_structures.LinkedList;

/**
 * @author redwards
 *
 */
public class Stack<E> {

        private ListI<E> llist;
        public Stack() {
                llist = new LinkedList<E>();
        }

        public void push(E c) {
                llist.addFirst(c);
        }

        public E pop() {
                return llist.removeFirst();
        }

        public int size() {
                return llist.size();
        }

        public boolean isEmpty() {
                return llist.isEmpty();
        }

        public boolean isFull() {
                return llist.isFull();
        }
        
        public E peek() {
        	return llist.peekFirst();
        }

        public boolean contains(E obj) {
                return llist.contains(obj);
        }

        public void makeEmpty() {
                llist.makeEmpty();
        }

        public Iterator<E> iterator() {
                return llist.iterator();
        }  
}
