
import java.util.Random;
public class util <E> {
		public static void printIArr (int[] arrVals, int arrSize) // Method to Print integer Array Elements

		{

		for(int i=0; i< arrSize ; i++) {

		System.out.print(arrVals [i] + " ");

		}

		return;

		}
		public static void printDArr (double[] arrVals, int arrSize) // Method to Print Double Array Elements

		{

		for(int i=0; i< arrSize ; i++) {

		System.out.println(arrVals [i] + " ");

		}

		return;

		}
		public static void randomDouble (double [] arrVals, int arrSize)  // method to randomly generate Doubles to fill each element of the array

		{ 

		for(int i = 0; i < arrVals.length;i++) // generate random numbers for each array element and print out array.

		{

		arrVals[i] = (double) ((Math.random() ));

		}

		return;

		}
		public static void randomInt (int [] arrVals, int arrSize)  // method to randomly generate integers to fill each element of the array
		{ 
			for(int i = 0; i < arrVals.length;i++) // generate random numbers for each array element and print out array.
			{
				arrVals[i] = (int) ((Math.random() * 10));
			}
			return;
		}
		public static void reverseArray (int [] arrVals,int arrSize ) // Method to Reverse Array Elements 
		{
			for(int j = 0; j < (arrSize/2);j++)  
			{
				int temp = 0;
				temp = arrVals [j];
				arrVals[j] =  arrVals[arrSize - 1 - j ];
				arrVals[arrSize - 1 - j ] = temp;
			}
			return ;
		}
		public static void dotProduct(double [] arrVals, double [] arrVals2, int arrSize) // method to calculate dot Product of 2 arrays
		{ 
			double dotProduct = 0;
			for(int i=0; i < arrSize;i++)
			{
				dotProduct += (arrVals[i] * arrVals2[i]);
			}
			System.out.println("The dot product of arr1 and arr2 is " + dotProduct); // printout the dotproduct of the 2 arrays
			return;
		}
		public static void shuffle(String [] arr)  // a function to swap and shuffle array elements
		{
			
			int randomIndex;
			String cardTemp;
			Random rand = new Random();
			for(int i =0; i<arr.length;i++) // for loop to shuffle the 52 cards in the deck
			{
				randomIndex=rand.nextInt(52);
				cardTemp = arr[i];
				arr[i]=arr[randomIndex];
				arr[randomIndex]=cardTemp;
			}
		}
		public static void transpose(int [][]arrVals, int col, int row) // method to transpose rows and columns of the 2D array and print the elements out
		{
			for (int i =0; i< arrVals[0].length;i++)
			{
				System.out.println();

				for(int j = 0; j<arrVals.length; j++)
				{
					System.out.print(arrVals[j][i] + "  ");
				}
			}
		}
		public static void printlnIArr (int[] arrVals, int arrSize) // Method to Println Integer Array Elements

	{

	for(int i=0; i< arrSize ; i++) {

	System.out.println(arrVals [i] + " ");

	}

	return;

	}
		public static void printlnDArr (double[] arrVals, int arrSize) // Method to Print Array Elements

	{

	for(int i=0; i< arrSize ; i++) {

	System.out.println(arrVals [i] + " ");

	}

	return;

	}
		public static void random2D (int [][] arrVals, int col, int row)  // method to randomly generate integers from 10-99 to fill each element of the 2D array and print array out

	{ 
		Random rand = new Random();
		

	for(int i = 0; i < col;i++)

	{
		for(int j=0; j<row; j++)
		{
			arrVals[i][j] = rand.nextInt(90)+10;
			System.out.print(arrVals[i][j] + "  " );

		}
		System.out.println();
	}

	return;

	}
		public static void print2DArr (double[] [] arrVals, int row , int col) // Method to Println Integer Array Elements
		{
		for(int i = 0; i < col;i++)

		{
			for(int j=0; j<row; j++)
			{
				System.out.print(arrVals[i][j] + "  " );
			}
			System.out.println();
		}

		return;

		}
		public static void print2DIArr (int[] [] arrVals, int row , int col) // Method to Println Integer Array Elements
		{
		for(int i = 0; i < col;i++)

		{
			for(int j=0; j<row; j++)
			{
				System.out.print(arrVals[i][j] + "  " );
			}
			System.out.println();
		}

		return;

		}
}
