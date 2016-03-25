/**
 *Program # 2_b, Anas Khafagi, Masc1916
 * prompt user to choose number of rows and columns in 2D array and randomize each element with numbers from 10-99
 *  printout array columns and rows then transpose the rows and columns and print array agin
 * 02-10-15
 *@ Anas Khafagi 
 *
 */
import java.util.Scanner;
public class Prog2_c {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // prompt user for input
		System.out.println("Choose Number Of Rows  in 2D Array");
		final int numRows = scan.nextInt(); // ask user for number of rows of the 2D array
		System.out.println("Choose Number Of Columns  in 2D Array");
		final int numCols = scan.nextInt(); // ask the user for the number of columns in the 2D array
		int [][] arrList= new int [numRows][numCols];  // 2D array of user inputed rows and colu
		util.random2D(arrList,numRows,numCols); // envoke method to randomize each element of 2D Array
		util.transpose(arrList,numRows,numCols); // envoke method to transpose rows and columns and print the array
		scan.close();
	}
}
