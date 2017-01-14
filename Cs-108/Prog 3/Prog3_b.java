/**
 *Program # 3_b, Anas Khafagi, Masc1916
 * Prompt user to choose a number and print digits of that number in reverse order.
 * CS108-2
 * 02-18-15
 *@ Anas Khafagi 
 */
import java.util.Scanner;

public class Prog3_b {
public static void printReverse(int num) { // Method to print the digits in reverse order.
	if (num < 10) {
		System.out.println(num);
			return;
	}
	else 
	{
		System.out.print(num % 10); // print out the remainder of the number and 10 which is the last digit 
			printReverse(num / 10); // method calling itself but with number/10 prameter moving to the next digit 
}
	
}
	public static void main(String[] args) {
		System.out.println("Program # 3_a, Anas Khafagi, Masc1916 ");
		 	Scanner scan = new Scanner(System.in); // input Scanner to prompt user to choose number
		System.out.println("Please Choose a Number to Reverse order of : "); // Prompt user to choose number to reverse order of.
		 	int num = scan.nextInt();// intializing the int num with the user input
		printReverse(num); // calling on the method to print digits in reverse of int num
		scan.close();

	}

}
