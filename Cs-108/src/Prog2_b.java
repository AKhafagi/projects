
/**
 *Program # 2_b, Anas Khafagi, Masc1916
 * prompt user to choose number of elements in 2 arrays and print arrays and compute the dot product of the 2 arrays
 * 02-08-15
 *@ Anas Khafagi 
 *
 */
import java.util.Scanner;
public class Prog2_b {
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Please Choose a Number of elements in Array"); // ask user for number of elements in array.
		final int NUM_ELEMENTS = scan.nextInt(); // Number of Elements in Array.
		double [] arr1 = new double [NUM_ELEMENTS];// length of the array is the user entered number.
		double [] arr2 = new double [NUM_ELEMENTS];
		util.randomDouble (arr1,NUM_ELEMENTS);  // envoke method to fill first array  with random numbers
		util.randomDouble (arr2, NUM_ELEMENTS);  // envoke method to fill second array with random numbers
			System.out.println("arr1\n");
		util.printDArr (arr1, NUM_ELEMENTS); // envoke method to print first array elements
		System.out.println();
			System.out.println("arr2\n");
		util.printDArr (arr2, NUM_ELEMENTS);// envoke method to print second array elements
		System.out.print("\n");

		util.dotProduct(arr1, arr2, NUM_ELEMENTS);
		scan.close();
		
	}


}
