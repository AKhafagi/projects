
public class ArrayList1Tester {
	public static void main(String [] args) throws Exception{
	ArrayList1<Integer> arr = new ArrayList1<Integer>();
	arr.add(new Integer(10));
	arr.add(new Integer(20));
	arr.add(new Integer(30));
	arr.add(new Integer(40));
	System.out.println(arr.isEmpty());
	System.out.println(arr.size());
	System.out.println(arr.contains(new Integer(10)));
	System.out.println(arr.indexOf(new Integer(10)));
	arr.print();
	System.out.println(arr.get(1));
	arr.clear();
	arr.trimToSize();
	arr.print();
	
}
}