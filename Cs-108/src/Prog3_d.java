/**
 *Program # 3_b, Anas Khafagi, Masc1916
 * Prompt user to choose a number and prints the numbers in the Fibonacci sequence until that number.
 * CS108-2
 * 02-18-15
 *@ Anas Khafagi 
 */
import java.util.Scanner;

public class Prog3_d {
public static void fibSeq (int fib1, int fib2, int max ) // method to print the Fibonacci sequence.
{
	int lim = fib1; 
	if (lim > max) 
	{
		return;
	}
	else {
		System.out.print(lim + " ");
		fibSeq(fib2,(fib2 + fib1),max );  // calling fibSeq method but with different prameters ( next number in the sequence).
	}
}
public static void main(String [] args ) 
{
	System.out.println("Program # 3_a, Anas Khafagi, Masc1916 ");
	Scanner scan = new Scanner(System.in); // input Scanner to prompt user to choose number
System.out.println("Enter number upto which Fibonacci series to print: "); // Prompt user to choose number to print fibonacci sequence up to
int N = scan.nextInt(); // intialize N with user input
int fib1 = 0; // intialize first number of fibSeq which is 0
int fib2 = 1; // intialize second number of fibSeq which is 1
fibSeq(fib1,fib2,N); // call the method fibSeq to print the Fibonacci sequence up to N.
scan.close();
}
	
}