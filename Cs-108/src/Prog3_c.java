/**
 *Program # 3_b, Anas Khafagi, Masc1916
 * Prompt user to choose a base and an exponent and calculate the power of that base to the exponent.
 * CS108-2
 * 02-18-15
 *@ Anas Khafagi 
 */
import java.util.Scanner;
public class Prog3_c {
	public static double powerBase (double base , double exp) // method to calculate power of a base and exponent
	{
	 if (exp == 0)
	 {
		 return 1; // calculate exponent of 0.
	 }
	 else if (exp < 0)
	 {
		 return (((1/(base * powerBase(base,(-1 * exp -1)))))); // calculate negative exponent.
	 }
		 else
		 { 
			 return (base* powerBase(base, exp-1)); // calculate positive exponent.
		 }
	 }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // input Scanner to prompt user to choose number.
		System.out.println("please choose the base! "); // Prompt user to choose base.
		double n = Math.round(scan.nextDouble()); // round base to nearest double. 
		System.out.println("please choose the exponent! "); // Prompt user to choose exponent.
		double x = Math.round(scan.nextDouble()); //round base to nearest double. 
		double answer = powerBase(n,x); // intialize answer with calculated powerBase.
		System.out.printf("%.3f", answer); // round the answeer to the nearest thousandths.
		scan.close();
	}
}
