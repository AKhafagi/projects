/**
 *Program # 1-b, Anas Khafagi, Masc1916
 * print output program number, Full name , and Masc Id and prints the line numbers up to n.
 * CS108-2
 * 02-03-15
 *@ Anas Khafagi 
 *
 */
import java.util.Scanner;
public class Prog1b {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
		int n = 0;
		n = scan.nextInt();
	System.out.println("Program 1, Anas Khafagi, masc1916");
for (int i = 0;i < n;++i)
{
	int numLines= i+1;
	System.out.println("Line" + numLines + ".");
}
	}

}
