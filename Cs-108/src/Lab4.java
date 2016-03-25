/**
 *Program # 4, Anas Khafagi, Masc1916
 * Reads Message From File encyrpts, and decrypts it using shifts and then writes to a file( ".crypt" for encrypt  and ".decrypt" for decrypt).
 * CS108-2
 * 03-01-15
 * @Anas Khafagi 
 *
 */
import java.util.Scanner; // import the java scanner to read from file.
import java.io.File; // input the java input/output file creater.
import java.io.FileNotFoundException; // input the java exception file not found.
import java.io.FileWriter; // input the java file writer to overwrite file
import java.io.PrintWriter; // input the jave print writer to write to file
public class Lab4 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("Program 4, Anas Khafagi, Masc1916");
		String fileName = args[2];
		try {	// a try loop to make sure Input/Output is good.
			if (args.length != 3) { 	// an if statement to check the number of Arguments.
				throw new Exception( // if arguments are invalid throws an exception
						"Invalid Argument, Please Change The Arguments and try again!!");
			}
			if (args[2].length() < 5) {   // an if statement to check the length of name of the file.
				throw new Exception( // if file length is invalid throws an exception
						"Invalid Text File Length, please change and try again");
			}
			File file = new File(args[2]); // creating a new file.	
			Scanner fin = new Scanner(file); // opening a scanner to read from the file. 
			if(file.length() == 0) {   // an if statement to check if the file is not empty 
				throw new Exception("File is empty, Please input Message and Try Again ");
			}
			if (args[0].equals("encode")) { // an if statement to see if the program will encode or decode the message.
				fileName = fileName.replace(".txt", ".crypt"); // renaming the extension from ".txt" to ".crypt
				PrintWriter fout = new PrintWriter(new FileWriter(fileName, false)); // making a Printwriter to write the encoded message to the ".crypt" file.
				Util4.encode(fin, fout, args[1]); // Evoking the method to encode the message.
			}
			else if (args[0].equals("decode")) { // an if statement to see if the program will encode or decode the message.
				fileName = fileName.replace(".crypt", ".decrypt");// renaming the extension from ".crypt" to ".decrypt
				PrintWriter fout = new PrintWriter(new FileWriter(fileName, false)); // making a Printwriter to write the decoded message to the ".decrypt" file.
				Util4.decode(fin, fout, args[1]); // Evoking the method to decoded the message.
			}
			fin.close(); // close the scanner to stop reading from the file.

			if (!args[0].equals("encode") && !args[0].equals("decode")) { // an if statement to make sure that the user argument is either encode or decode.
				throw new IllegalArgumentException( //if user does not choose encode or decode throws an exception
						"invalid arguments, Please Choose 'Encode' or 'decode'");
			}
		}
		catch (FileNotFoundException e) { // to catch the exception if the file is not found.
			System.out.println("File Not Found, Please Try again");
		}
	}
}
