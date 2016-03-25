
public class Child extends Person {
	private String eyeColor;
	private String hairColor;
	

	public Child() {
		super();
		eyeColor=null;
		hairColor = null;
	}
	public Child(int age, String name, String eyeColor, String hairColor){
		super(age,name);
		this.eyeColor= eyeColor;
		this.hairColor= hairColor;
		
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public void printAll(){
		System.out.println(getName() + getAge() + eyeColor + hairColor);
	}

}
