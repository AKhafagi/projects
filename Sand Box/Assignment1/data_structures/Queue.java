import java.util.Iterator;

public class Queue<E> {
    private ListI<E> list;
    
    public Queue() {
       list = new LinkedList<E>();
        }
        
    public void enqueue(E obj) {
        list.addLast(obj);
        }
        
    public E dequeue() {
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
