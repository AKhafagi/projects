import java.util.Scanner; // import the java scanner to read from file.
import java.io.File; // input the java input/output file creater.
import java.io.FileNotFoundException; // input the java exception file not found.
/**
 * Program #7, Anas Khafagi, Masc1916. 
 * tests the Couple class
 * CS108-2 04-21-15
 * @author Anas khafagi
 */
public class CoupleClient {

	@SuppressWarnings({ "resource", "rawtypes" })
	public static void main(String args[]) throws Exception {
		String str1 = null;
		String str2 = null;
		Double d = null;
		Double d2 = null;
		int x = 0;
		int y = 0;

		System.out.println("Program #6, Anas Khafagi, Masc1916");

		try { // checks if the file exits if not it throws file not found 
			if (args[0].equals(null)) { // checks if the file name is valid
				throw new Exception("Usage: java CoupleClient inputFileName");
			}
			String fileName = args[0];
			File file = new File(fileName);
			Scanner fin = new Scanner(file);
			if (!fin.hasNext()) { // checks if the file is not empty 
				throw new Exception("File is Empty please check File and try again");
			}
			while (fin.hasNext()) { // loop through the file while it is not empty 
				str1 = fin.next();
				str2 = fin.next();
				d = Double.parseDouble(fin.next());
				d2 = Double.parseDouble(fin.next());
				x = Integer.parseInt(fin.next());
				y = Integer.parseInt(fin.next());
			}
			fin.close(); // close the scanner to stop reading from the file 

		}
		catch (FileNotFoundException e) { // catchs the file not found exception
			System.out.println("File Not Found, Please Check and Try again");
		}
		Couple<String> sCouple = new Couple<String>(str1, str2); // creates a string Couple Object
		Couple<Double> dCouple = new Couple<Double>(d, d2); // creates a double Couple object
		Couple<Integer> iCouple = new Couple<Integer>(x, y); // creates a int Couple Object
		System.out.println(sCouple); // prints out both strings on one line
		System.out.println(dCouple); // prints out both doubles on one line
		System.out.println(iCouple); // prints out both int on one line
		sCouple.setFirst("moonlit"); // sets the first String of the Scouple instance to moonlit
		System.out.println(sCouple); // prints out the new Strings
		Couple<Double> dCouple2 = dCouple; // creates a copy of dcouple called dCouple2
		dCouple.equals(dCouple2); // checks if dcouple equals dCouple2
		Couple<Double> dCouple3 = new Couple<Double>(dCouple.getFirst(),dCouple.getSecond()); // creates another copy of dcouple but by using getters
		dCouple.equals(dCouple3); // checks if dcouple equals dCouple3
		dCouple.setSecond(-12.34); // sets the second Double of dCouple to -12.34
		Couple<String> sCouple2 = new Couple<String>("one", "Couple"); // makes String Couple Object with 2 String one and couple 
		Couple<Couple> Couple = new Couple<Couple>(sCouple, sCouple2); // creates a couple couple obeject with scouple and scouple2
		System.out.println(Couple); // prints out couple 
		Couple<String> sCouple3 = new Couple<String>("Dark", "Night"); // creates a new String couple with Dark and Night
		System.out.println(sCouple2.getFirst()); // returns the first field of sCouple2
		System.out.println(sCouple.getFirst()); // returns the first field of sCouple
		System.out.println(sCouple3.getSecond()); // returns the second field of sCouple3
		System.out.println(sCouple.equals(dCouple)); // checks if sCouple equals dCouple
		System.out.println(sCouple.equals(sCouple2)); // checks if scouple equals sCouple2
		System.out.println(sCouple.equals(sCouple3));
		System.out.println(sCouple.equals(Couple));
		System.out.println(sCouple.equals(dCouple));
		System.out.println(sCouple.equals(Couple.getFirst())); // checks if Scouple equals the first field of Couple 
		System.out.println(Couple.equals(Couple.getSecond()));
		System.out.println(dCouple.equals(iCouple));
		System.out.println(iCouple.equals(dCouple));
		System.out.println(dCouple.equals(dCouple2));
		System.out.println(dCouple.equals(dCouple3));

	}
}
