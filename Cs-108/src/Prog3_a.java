/**
 *Program # 3_a, Anas Khafagi, Masc1916
 * prompt user to choose a number and count down from that number to 1.
 * CS108-2
 * 02-18-15
 *@ Anas Khafagi 
 *
 */
import java.util.Scanner;
public class Prog3_a {
	public static void countDown(int num) { //method to count down from Number "N"
    if (num == 1) {
       System.out.print(num + " ");
    }
    else {
       System.out.print(num + " ");
       countDown(num-1); // envoke method but with number-1 prameter.
    }
    return;
 }
 
 public static void main (String[] args) {
	 System.out.println("Program # 3_a, Anas Khafagi, Masc1916 ");
	 Scanner scan = new Scanner(System.in); // input Scanner to prompt user to choose number
	 System.out.println("Please Choose a Number to Count Down from: "); // Prompt user to choose number to count down from.
	 int num = scan.nextInt(); // intializing num with the user input 
    countDown(num); // calling on the method to count down from int num
    scan.close();
}
}
