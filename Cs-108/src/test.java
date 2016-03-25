
public class test<E> {
	private int maxSize;
	private int currentSize;
	public E[] storage;
	private int front,rear;
	

	@SuppressWarnings("unchecked")
	public test(int maxSize) {
		super();
		this.maxSize = maxSize;
		currentSize = 0;
		storage = (E[]) new Object[maxSize];
		front = rear=0;
	}
	public void enqueue(E data){
		if(!isFull()){
			storage[rear++]= data;
			currentSize++;
		}
	}
	public E dequeue(){
		if(!isEmpty()){
			E temp =storage[front++];
			currentSize--;
			return temp;
			
		}
		return null;
	}

	public boolean isFull() {
		return (rear == maxSize-1);
	}
	public boolean isEmpty() {
		return (currentSize == 0);
	}
	
	public static void main(String[] args) {
		test<Integer> t = new test<Integer>(20);
		t.enqueue(1);
		t.enqueue(2);
		t.enqueue(3);
		System.out.println(t.dequeue());
		System.out.println(t.dequeue());
		System.out.println(t.dequeue());
		System.out.println(t.dequeue());
		System.out.println(8>>1);
		
		
		}

	}

