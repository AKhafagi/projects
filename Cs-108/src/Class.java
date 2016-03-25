
public class Class {
	private String grade;
	private String name;
	private double credits;
	private double gpaVal;
	
	public Class (String name, String grade, double credits ){
		this.grade = grade;
		this.name =name;
		this.credits = credits;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredit(int credit) {
		this.credits = credit;
	}
	public double gpaCalc(){
		
		switch (this.grade){
		case "A":
			setGpaVal(4.0);
			break;
		case "A-":
			setGpaVal(3.7);
			break;
		case "B+":
			setGpaVal(3.3);
			break;
		case "B" : 
			setGpaVal(3.0);
			break;
		case "B-":
			setGpaVal(2.7);
			break;
		case "C+":
			setGpaVal(2.3);
			break;
		case "C":
			setGpaVal(2.0);
			break;
		case "C-":
			setGpaVal(1.7);
			break;
		case "D+":
			setGpaVal(1.3);
			break;
		case "D" :
			setGpaVal(1.0);
			break;
		case "D-":
			setGpaVal(0.7);
			break;
		case "F": 
			setGpaVal(0.0);	
			break;
		}
		return gpaVal;
	}

	public double getGpaVal() {
		return gpaVal;
	}

	public void setGpaVal(double gpaVal) {
		this.gpaVal = gpaVal;
	}
	

}
