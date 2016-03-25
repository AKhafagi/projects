import java.util.Arrays;
public class ArrayList1 <E extends Comparable<E>> implements ListT<E> {
	private E[] arr;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList1(){
		this.arr = (E[]) new Comparable[10];
		this.size=0;
		
	}
	@SuppressWarnings("unchecked")
	public ArrayList1(int size){
		this.arr = (E[]) new Comparable[size];
		this.size=0;
	}
	

	@Override
	public boolean add(E data) {
		if(data != null){
			if(size+1 >= arr.length){
				this.grow();
				this.arr[size++]=data;
				return true;
			}
			else{
				arr[size++]=data;
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for(int i =0; i < size; i++){
			this.arr[i]=null;
		}
		this.size=0;
	}

	@Override
	public boolean contains(E data) {
		if(data !=null){
			for(int i =0; i<size;i++){
				if(data.compareTo(arr[i]) ==0)
					return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(E data) {
		if(data != null){
			for(int i =0; i<size; i++){
				if(data.compareTo(arr[i]) ==0)
					return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void trimToSize() {
		this.arr= Arrays.copyOf(arr, size);
		
	}

	@Override
	public int size() {
		if(!this.isEmpty())
			return size;
		return 0;
	}

	@Override
	public E get(int index) throws Exception {
		try{
			if (index >= size) {
				throw new IndexOutOfBoundsException("the index chosen is out of bound");
			}
			if (index < 0) {
				throw new IllegalArgumentException("the index chosen is invalid");
			}
		}
	catch (IllegalArgumentException | IndexOutOfBoundsException e) {
		e.printStackTrace();
		return null;
	}
	return arr[index];
	}
	private E[] grow(){
		try{
			this.arr=Arrays.copyOf(arr, this.arr.length*2);
			
		}
		catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return this.arr;
	}
	public void print(){
		if(!this.isEmpty()){
			for(int i = 0; i < size; i++){
				System.out.println(i + " : " + arr[i]);
			}
			
		}
		else{
			System.out.println("ArrayList is empty");
		}
	}

}
