import java.util.Iterator;
public class Stack<E>{
private ListI<E> list;

public Stack() {
   list = new LinkedList<E>();
    }
    
public void push(E obj) {
    list.addFirst(obj);
    }
    
public E pop() {
    return list.removeFirst();
    }
    
public int size() {
   return list.size();
    }
    
public boolean isEmpty() {
   return list.isEmpty();
    }
    
public E peek() {
   return list.peekFirst();
    }
    
public boolean contains(E obj) {
   return list.contains(obj);
    }
    
public void makeEmpty() {
    list.makeEmpty();
    }
    
public Iterator<E> iterator() {
    return list.iterator();
    }
}
