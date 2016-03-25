
public class Person {
	private int age;
	private String name;
	
	public Person(){
		age =0;
		name = null;
	}
	public Person(int age, String name){
		this.age = age;
		this.name=name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return this.age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void printAll(){
		System.out.println(age + name);
	}
	
	

}
