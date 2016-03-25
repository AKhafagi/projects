
/**
 *Program # 2_a, Anas Khafagi, Masc1916
 * prompt user to choose number of elements in array and print array and then print the array in reverse order
 * CS108-2
 * 02-08-15
 *@ Anas Khafagi 
 *
 */

//import util;
import java.util.Scanner;

public class Prog2_a {
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Please Choose a Number of elements in Array"); // ask user for number of elements in array.
		final int NUM_ELEMENTS = scan.nextInt(); // Number of Elements in Array.
		int [] numbers = new int [NUM_ELEMENTS]; // length of the array is the user entered number.
		//ArrayList<ComputerPart> comp = new ArrayList<>();
		util.randomInt(numbers,NUM_ELEMENTS);  // envoke method to fill array elements with random numbers
		util.printIArr (numbers, NUM_ELEMENTS); // envoke method to print array elements
		
		System.out.println(); //  print line after array elements 
			
		util.reverseArray (numbers, NUM_ELEMENTS); // envoke method to reverse array elements
		
		util.printIArr(numbers, NUM_ELEMENTS);  // envoke method to print reversed array elements
		scan.close();
	}

}
