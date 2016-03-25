import java.util.Scanner;
public class Prog_2b_test {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Please Choose a Number of elements in Array"); // ask user for number of elements in array.
		final int NUM_ELEMENTS = scan.nextInt(); // Number of Elements in Array.
		double [] numbers = new double [NUM_ELEMENTS];
		double [] numbers2 = new double [NUM_ELEMENTS];
		double sum = 0;
	 
		for(int i = 0; i < NUM_ELEMENTS;i++)
		{
			numbers[i] = (double) (Math.random());

		}
		for(int i = 0; i < NUM_ELEMENTS;i++)
		{
			numbers2[i] = (double) (Math.random());

		}
		
		System.out.println("numbers");
		for(int i = 0; i < 20; i++)
		{ 
			System.out.print("-");
		}
		System.out.println();
		for(int i=0; i< NUM_ELEMENTS ; i++) {

			System.out.println(numbers [i] + " ");

			}
		System.out.println("numbers2");
		for(int i = 0; i < 20; i++)
		{ 
			System.out.print("-");
		}
		System.out.println();
		for(int i=0; i< NUM_ELEMENTS ; i++) {

			System.out.println(numbers2 [i] + " ");

			}
		for(int j = 0;j < NUM_ELEMENTS; j++) {
			sum = sum + numbers[j] * numbers2[j];
			
		}
		System.out.println("dot product is " + sum);
		scan.close();
	}
	

}
