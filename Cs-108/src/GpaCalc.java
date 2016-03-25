import java.util.Scanner;
import java.util.ArrayList;

public class GpaCalc {

	public static void main(String[] args) {
		int numClasses =0;
		double totalGpa=0.0;
		double totalCreds =0.0;
		double weightedGpa = 0.0;
		Scanner scan = new Scanner(System.in);
		ArrayList<Class> classes = new ArrayList<Class>();
		System.out.println("Please enter number of classes ");
		numClasses = scan.nextInt();
		for(int i = 0; i <numClasses; i++){
			String name;
			String grade;
			double cred;
			System.out.println("Please enter class name: ");
			name = scan.next();
			System.out.println("Grade: ");
			grade = scan.next();
			System.out.println("Credits: ");
			cred = scan.nextDouble();
			classes.add(new Class(name,grade,cred));
		}
		for(int i = 0; i < numClasses; i++){
			 totalCreds += classes.get(i).getCredits();
			weightedGpa +=classes.get(i).gpaCalc() * classes.get(i).getCredits();
		}
		System.out.println("Calculating Weighted G.P.A for " + numClasses + " Classes Please Wait");
		System.out.print(".");
		System.out.print(".");
		System.out.print(".");
		System.out.print(".");
		System.out.println();
		totalGpa = weightedGpa / totalCreds;
		System.out.println(" Weighted G.P.A for " + numClasses + " Classes is: ");
		System.out.printf("%.2f", totalGpa);
		scan.close();
	
		
			
			
	}

	}

