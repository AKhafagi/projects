/**
 *Program # 4 Utility File , Anas Khafagi, Masc1916
 * Reads Message From File encyrpts, and decrypts it using shifts and then writes to a file( ".crypt" for encrypt  and ".decrypt" for decrypt).
 * CS108-2
 * 03-01-15
 *@ Anas Khafagi 
 *
 */

import java.io.PrintWriter;
import java.util.Scanner;

public class Util4 {
	public static final int NUM_LETTERS = 26;

	// shifting up for the encoding process
	public static char shiftUpByK(char c, int k) {
		if ('a' <= c && c <= 'z') {
			return (char) ('a' + (c - 'a' + k) % NUM_LETTERS);
		}

		if ('A' <= c && c <= 'Z') {
			return (char) ('A' + (c - 'A' + k) % NUM_LETTERS);
		}
		return c; // don't encrypt if not an alphabetic character
	}

	// shifting down for the decoding process
	public static char shiftDownByK(char c, int k) {
		return shiftUpByK(c, NUM_LETTERS - k);
	}
	
	/**
	 * method to encode the text by shifting the letters up by the keyword
	 * @param fin Scanner fin to read to the message from file
	 * @param out PrintWriter out to write the encoded message to the new file
	 * @param key the keyword that will shift letters up by
	 */
	public static void encode(Scanner fin, PrintWriter out, String key) { 
		String message = fin.nextLine(); // read the message from the file and intialize it to a string

		for (int i = 0; i < message.length(); i++) // a loop to shift the letters up and encode the message 
		{
			out.print(shiftUpByK(message.charAt(i),key.charAt(i % key.length()) - 'a'));
		}
		out.close(); // closing the printwriter to stop writing to file 
	}

	/**
	 * method to decode the text by shifting down the letters up by the keyword.
	 * @param fin Scanner fin to read to the message from file.
	 * @param out PrintWriter out to write the decoded message to the new file.
	 * @param key the keyword that will shift letters down by.
	 */
	public static void decode(Scanner fin, PrintWriter out, String key) {
		String message = fin.nextLine(); // read the message from the file and intialize it to a string

		for (int i = 0; i < message.length(); i++) // a loop to shift the letters up and encode the message
		{
			out.print(shiftDownByK(message.charAt(i),key.charAt(i % key.length()) - 'a'));
		}
		out.close(); // closing the printwriter to stop writing to file 
	}

}
