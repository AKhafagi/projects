package edu.sdsu.cs.datastructures;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class util {
	/**
	 * prints the elements of an int array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(int[] a) {

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of an double array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(double[] a) {

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a short array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(short[] a) {

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a long array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(long[] a) {
		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a float array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(float[] a) {
		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a string array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(String[] a) {
		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a char array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(char[] a) {

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * prints the elements of a byte array on one line
	 * 
	 * @param a
	 *          the array to printout the elements of
	 */
	public static void printArray(byte[] a) {

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i] + " ");

		}

		return;

	}

	/**
	 * randomly generate ints to fill each element of the array
	 * 
	 * @param a
	 *          int array
	 */
	public static void randomArray(int[] a)

	{

		for (int i = 0; i < a.length; i++) // generate random numbers for each array
																				// element and print out array.

		{

			a[i] = (int) (Math.random() * 10);

		}

		return;

	}

	/**
	 * randomly generate double to fill each element of the array
	 * 
	 * @param a
	 *          double array
	 */
	public static void randomArray(double[] a) {

		for (int i = 0; i < a.length; i++) // generate random numbers for each array
																				// element and print out array.

		{

			a[i] = ((Math.random()));

		}

		return;

	}

	/**
	 * Reverse int Array Elements
	 * 
	 * @param a
	 *          int array
	 */
	public static void reverseArray(int[] a) {
		for (int j = 0; j < (a.length / 2); j++) {
			int temp = 0;
			temp = a[j];
			a[j] = a[a.length - 1 - j];
			a[a.length - 1 - j] = temp;
		}
		return;
	}

	/**
	 * Reverse double Array Elements
	 * 
	 * @param a
	 *          double array
	 */
	public static void reverseArray(double[] a) {
		for (int j = 0; j < (a.length / 2); j++) {
			double temp = 0;
			temp = a[j];
			a[j] = a[a.length - 1 - j];
			a[a.length - 1 - j] = temp;
		}
		return;
	}

	/**
	 * Reverse string Array Elements
	 * 
	 * @param a
	 *          string array
	 */
	public static void reverseArray(String[] a) {
		for (int j = 0; j < (a.length / 2); j++) {
			String temp;
			temp = a[j];
			a[j] = a[a.length - 1 - j];
			a[a.length - 1 - j] = temp;
		}
		return;
	}

	/**
	 * calculate the dot product of 2 double arrays and print it out
	 * 
	 * @param a
	 *          first double array
	 * @param a1
	 *          second double array
	 */
	public static void dotProduct(double[] a, double[] a1)

	{
		double dotProduct = 0;
		for (int i = 0; i < a.length; i++) {
			dotProduct += (a[i] * a1[i]);
		}
		System.out.println("The dot product of arr1 and arr2 is " + dotProduct);

		return;
	}

	/**
	 * calculate the dot product of 2 int arrays and print it out
	 * 
	 * @param a
	 *          first int array
	 * @param a1
	 *          second int array
	 */
	public static void dotProduct(int[] a, int[] a1) {
		int dotProduct = 0;
		for (int i = 0; i < a.length; i++) {
			dotProduct += (a[i] * a1[i]);
		}
		System.out.println("The dot product of arr1 and arr2 is " + dotProduct);

		return;
	}

	/**
	 * shuffles the String array num times
	 * 
	 * @param a
	 *          String array
	 * @param num
	 *          the number of times to shuffle the array
	 */
	public static void shuffle(String[] a, int num) {

		String cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * a.length);
			int random2 = (int) (Math.random() * a.length);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;
		}
	}

	/**
	 * shuffles the int array num times
	 * 
	 * @param a
	 *          int array
	 * @param num
	 *          the number of times to shuffle the array
	 */
	public static void shuffle(int[] a, int num) {

		int cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * a.length);
			int random2 = (int) (Math.random() * a.length);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;
		}
	}

	/**
	 * shuffles a deck
	 * 
	 * @param a
	 *          String array
	 */
	public static void shuffle(String[] a) {

		String cardTemp;
		for (int i = 0; i < a.length; i++) {
			int random1 = (int) (Math.random() * a.length);
			int random2 = (int) (Math.random() * a.length);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;
		}
	}

	/**
	 * shuffles the double array num times
	 * 
	 * @param a
	 *          double array
	 * @param num
	 *          the number of times to shuffle the array
	 */
	public static void shuffle(double[] a, int num) {

		double cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * a.length);
			int random2 = (int) (Math.random() * a.length);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;
		}
	}

	/**
	 * transpose rows and columns of the 2D int array and print the elements out
	 * 
	 * @param a
	 *          2D int array
	 */
	public static void transpose(int[][] a) {
		for (int i = 0; i < a[0].length; i++) {
			System.out.println();

			for (int j = 0; j < a.length; j++) {
				System.out.print(a[j][i] + "  ");
			}
		}
	}

	/**
	 * transpose rows and columns of the 2D Double array and print the elements
	 * out
	 * 
	 * @param a
	 *          2D Double array
	 */
	public static void transpose(Double[][] a) {
		for (int i = 0; i < a[0].length; i++) {
			System.out.println();

			for (int j = 0; j < a.length; j++) {
				System.out.print(a[j][i] + "  ");
			}
		}
	}

	/**
	 * prints the elements of an 2D int array on one line
	 * 
	 * @param a
	 *          the 2D int array to printout the elements of
	 */
	public static void print2DArray(int[][] a)

	{
		for (int i = 0; i < a.length; i++) {
			System.out.println();

			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "  ");
			}
		}

	}

	/**
	 * prints the elements of an 2D double array on one line
	 * 
	 * @param a
	 *          the 2D Double array to printout the elements of
	 */
	public static void print2DArray(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println();

			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "  ");
			}
		}

	}

	/**
	 * prints the elements of an 2D String array on one line
	 * 
	 * @param a
	 *          the 2D String array to printout the elements of
	 */
	public static void print2DArray(String[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println();

			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "  ");
			}
		}

	}

	/**
	 * randomly generate integers from 10-99 to fill each element of the 2D array
	 * and print array out
	 * 
	 * @param a
	 *          the 2D int array to fill
	 */
	public static void random2D(int[][] a)

	{
		Random rand = new Random();

		for (int i = 0; i < a.length; i++)

		{
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = rand.nextInt(90) + 10;
				System.out.print(a[i][j] + "  ");

			}
			System.out.println();
		}

		return;

	}

	/**
	 * randomly generate Double from 10-99 to fill each element of the 2D double
	 * array and print array out
	 * 
	 * @param a
	 *          the 2D double array to fill
	 */
	public static void random2D(double[][] a) {

		for (int i = 0; i < a.length; i++)

		{
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = Math.random();
				System.out.print(a[i][j] + "  ");

			}
			System.out.println();
		}

		return;

	}

	/**
	 * method to count down from Number "N"
	 * 
	 * @param num
	 *          the number to count down from
	 */
	public static void countDown(int num) {
		if (num == 1) {
			System.out.print(num + " ");
		}
		else {
			System.out.print(num + " ");
			countDown(num - 1); // envoke method but with number-1 prameter.
		}
		return;
	}

	/**
	 * calculates power of a base and exponent {
	 * 
	 * @param base
	 *          the base to calculate
	 * @param exp
	 *          the exponent to calculate
	 * @return the number calculated
	 */
	public static double powerBase(double base, double exp) {
		if (exp == 0) {
			return 1; // calculate exponent of 0.
		}
		else if (exp < 0) {
			return (((1 / (base * powerBase(base, (-1 * exp - 1)))))); // calculate
																																	// negative
																																	// exponent.
		}
		else {
			return (base * powerBase(base, exp - 1)); // calculate positive exponent.
		}
	}

	/**
	 * print the digits in reverse order of a number.
	 * 
	 * @param num
	 *          the number to printout in reverse order
	 */
	public static void printReverse(int num) {
		if (num < 10) {
			System.out.println(num);
			return;
		}
		else {
			System.out.print(num % 10); // print out the remainder of the number and
																	// 10 which is the last digit
			printReverse(num / 10); // method calling itself but with number/10
															// prameter moving to the next digit
		}
	}

	/**
	 * prints the fibonacci sequence until max
	 * 
	 * @param fib1
	 *          the 1st number in the sequence
	 * @param fib2
	 *          the 2nd number in the sequence
	 * @param max
	 *          the max number to stop at
	 */
	public static void fibSeq(int fib1, int fib2, int max) // method to print the
																													// Fibonacci sequence.
	{
		int lim = fib1;
		if (lim > max) {
			return;
		}
		else {
			System.out.print(lim + " ");
			fibSeq(fib2, (fib2 + fib1), max); // calling fibSeq method but with
																				// different prameters ( next number in
																				// the sequence).
		}
	}

	/**
	 * shifts a letter up by K units
	 * 
	 * @param c
	 *          the letter to shift
	 * @param k
	 *          the number to shift by
	 * @return the letter after shift
	 */
	public static char shiftUpByK(char c, int k) {
		if ('a' <= c && c <= 'z') {
			return (char) ('a' + (c - 'a' + k) % 26);
		}

		if ('A' <= c && c <= 'Z') {
			return (char) ('A' + (c - 'A' + k) % 26);
		}
		return c; // don't encrypt if not an alphabetic character
	}

	/**
	 * shifts a letter down by K units
	 * 
	 * @param c
	 *          the letter to shift
	 * @param k
	 *          the number to shift by
	 * @return the letter after shift
	 */
	public static char shiftDownByK(char c, int k) {
		return shiftUpByK(c, 26 - k);
	}

	/**
	 * method to encode the text by shifting the letters up by the keyword
	 * 
	 * @param fin
	 *          Scanner fin to read to the message from file
	 * @param out
	 *          PrintWriter out to write the encoded message to the new file
	 * @param key
	 *          the keyword that will shift letters up by
	 */
	public static void encode(Scanner fin, PrintWriter out, String key) {
		String message = fin.nextLine(); // read the message from the file and
																			// intialize it to a string

		for (int i = 0; i < message.length(); i++) // a loop to shift the letters up
																								// and encode the message
		{
			out.print(shiftUpByK(message.charAt(i),
					key.charAt(i % key.length()) - 'a'));
		}
		out.close(); // closing the printwriter to stop writing to file
	}

	/**
	 * method to decode the text by shifting down the letters up by the keyword.
	 * 
	 * @param fin
	 *          Scanner fin to read to the message from file.
	 * @param out
	 *          PrintWriter out to write the decoded message to the new file.
	 * @param key
	 *          the keyword that will shift letters down by.
	 */
	public static void decode(Scanner fin, PrintWriter out, String key) {
		String message = fin.nextLine(); // read the message from the file and
																			// intialize it to a string

		for (int i = 0; i < message.length(); i++) // a loop to shift the letters up
																								// and encode the message
		{
			out.print(shiftDownByK(message.charAt(i),
					key.charAt(i % key.length()) - 'a'));
		}
		out.close(); // closing the printwriter to stop writing to file
	}

	/**
	 * Method to intialize the String array with 52 cards of 2 Strings suits and
	 * ranks
	 * 
	 * @return
	 */
	public static String[] init() {
		String[] a = new String[52];
		String[] suits = { "Spades", "Clubs", "Hearts", "Diamonds" };
		String[] rank = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
				"Eight", "Nine", "Ten", "Jack", "Queen", "King" };
		for (int i = 0; i < rank.length; i++) // for loop to intialize the 52 cards
																					// in a deck
		{
			for (int j = 0; j < suits.length; j++) {
				a[suits.length * i + j] = rank[i] + " Of " + suits[j];
			}
		}
		return a;
	}

	/**
	 * shuffle the deck num times.
	 * 
	 * @param num
	 *          the number of times to shuffle
	 * @param a
	 *          the String array to shuffle
	 */
	public void shuffle(int num, String a[]) {
		try { // check if the number of shuffles is valid.

			if (num <= 0) {

				throw new Exception("invalid number of shuffles please try agin"
						+ "Please change number of shuffles and try agin");

			}
		}

		catch (Exception e) { // catch the exception thrown if the number of
													// shuffles is invalid

			System.out.println(e);

		}
		String cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * 52);
			int random2 = (int) (Math.random() * 52);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;

		}
	}

	/**
	 * get the top card from the deck
	 * 
	 * @param deckNum
	 *          the number of cards in the deck
	 * @param deck
	 *          to get the card from
	 * @return the card
	 */
	public String getCard(int deckNum, String[] a) {
		try { // checks if there are enough cards in the deck
			if (deckNum <= 0) {
				throw new Exception("there are not enough cards in the deck");
			}
		}
		catch (Exception e) { // catches the exception thrown if there isn't enough
													// cards.

			System.out.println(e);

		}
		int temp = deckNum;
		deckNum--;
		return a[52 - temp];
	}

	/**
	 * clears all the elements of an int array
	 * 
	 * @param a
	 *          the int array to clear
	 */
	@SuppressWarnings("null")
	public void clear(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (Integer) null;
		}
	}

	/**
	 * clears all the elements of a double array
	 * 
	 * @param a
	 *          the double array to clear
	 */
	@SuppressWarnings("null")
	public void clear(double[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (Double) null;
		}
	}

	/**
	 * clears all the elements of a String array
	 * 
	 * @param a
	 *          the String array to clear
	 */
	public void clear(String[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = null;
		}
	}

	/**
	 * checks if a specific int is in array
	 * 
	 * @param data
	 *          the int to check for
	 * @param a
	 *          the array to find the element in
	 * @return
	 */
	public boolean contains(int data, int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == data) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if a specific object is in array
	 * 
	 * @param data
	 *          the object to check for
	 * @param a
	 *          the array to find the element in
	 * @return true if found false if not
	 */
	public boolean contains(Object data, Object[] a) {
		if (data != null) {
			for (int i = 0; i < a.length; i++) {
				if (a[i].equals(data)) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * checks if a specific String is in array
	 * 
	 * @param data
	 *          the String to check for
	 * @param a
	 *          the array to find the element in
	 * @return true if found false if not
	 */
	public boolean contains(String data, String[] a) {
		if (data != null) {
			for (int i = 0; i < a.length; i++) {
				if (a[i].equals(data)) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * checks if a specific double is in array
	 * 
	 * @param data
	 *          the double to check for
	 * @param a
	 *          the array to find the element in
	 * @return true if found false if not
	 */
	public boolean contains(double data, double[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == data) {
				return true;
			}

		}
		return false;
	}

	/**
	 * checks for an object and returns its index in the array
	 * 
	 * @param data
	 *          the object to check for
	 * @param a
	 *          the array to find the element in
	 * @return the index if found -1 if not
	 */
	public int indexOf(Object data, Object[] a) {
		if (data != null) {
			for (int i = 0; i < a.length; i++) {
				if (a[i].equals(data)) {
					return i;
				}

			}
		}
		return -1;
	}

	/**
	 * checks for an String and returns its index in the array
	 * 
	 * @param data
	 *          the String to check for
	 * @param a
	 *          the array to find the element in
	 * @return the index if found -1 if not
	 */
	public int indexOf(String data, String[] a) {
		if (data != null) {
			for (int i = 0; i < a.length; i++) {
				if (a[i].equals(data)) {
					return i;
				}

			}
		}
		return -1;
	}

	/**
	 * checks for an int and returns its index in the array
	 * 
	 * @param data
	 *          the int to check for
	 * @param a
	 *          the array to find the element in
	 * @return the index if found -1 if not
	 */
	public int indexOf(int data, int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == data) {
				return i;
			}

		}
		return -1;
	}

	/**
	 * checks for an double and returns its index in the array
	 * 
	 * @param data
	 *          the double to check for
	 * @param a
	 *          the array to find the element in
	 * @return the index if found -1 if not
	 */
	public int indexOf(double data, double[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == data) {
				return i;
			}

		}
		return -1;
	}

	/**
	 * checks if the object array is empty
	 * 
	 * @param a
	 *          the object array
	 * @returns true if empty false otherwise
	 */
	public boolean isEmpty(Object[] a) {
		if (a.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * checks if the int array is empty
	 * 
	 * @param a
	 *          the int array
	 * @returns true if empty false otherwise
	 */
	public boolean isEmpty(int[] a) {
		if (a.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * checks if the double array is empty
	 * 
	 * @param a
	 *          the double array
	 * @returns true if empty false otherwise
	 */
	public boolean isEmpty(double[] a) {
		if (a.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * checks if the String array is empty
	 * 
	 * @param a
	 *          the String array
	 * @returns true if empty false otherwise
	 */
	public boolean isEmpty(String[] a) {
		if (a.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
