import java.util.ArrayList;
/**
 * Program Extra Credit, Anas Khafagi, Masc1916. 
 * Creates a computer kit with an arraylist of computer parts
 * CS108-2 04-15-15
 * @author Anas khafagi
 */
public class ComputerKit {
	private ArrayList<ComputerPart> computer;

	/**
	 * constructs an empty ComputerPart arraylist computer
	 */
	public ComputerKit() {
		this.computer = new ArrayList<>();
	}

	/**
	 * constructs an ComputerPart arraylist from the user 
	 * @param computer ComputerPart arraylist 
	 */
	public ComputerKit(ArrayList<ComputerPart> computer) {
		this.computer = computer;
	}

	/**
	 * returns the ComputerPart arraylist 
	 * @return ComputerPart arraylist computer
	 */
	public ArrayList<ComputerPart> getComputer() {
		return computer;
	}

	/**
	 * sets the ComputerPart arraylist
	 * @param computer ComputerPart arraylist computer
	 */
	public void setComputer(ArrayList<ComputerPart> computer) {
		this.computer = computer;
	}
	/**
	 * adds a ComputerPart to the arraylist computer
	 * @param a a ComputerPart
	 */
	public void add (ComputerPart c)
	{
		computer.add(c);
	}
	/**
	 * checks if the computer is expensive, cheap, or normal
	 * @param a a ComputerKit object
	 * @return if the computer is expensive, cheap, or normal
	 */
	public String comparePrice(ComputerKit a) {
		double total = 0;
		for (int i = 0; i < a.getComputer().size(); i++) { // loop through the array list and add the price of each ComputerPart to total

			total += a.getComputer().get(i).getPrice();
		}
		if (total >= 1000) {	// if the total price is 1000 or more return expensive
			return "Expensive";
		}
		else if (total <= 250) { // if the total price is 250 or less return cheap
			return "Cheap";
		}
		else {  // if anything else return normal
			return "Normal";
		}
	}

	/**
	 * searches for a specific ComputerPart in the computer
	 * @param a the Computer
	 * @param o the part to check for 
	 * @return boolean true if the computer has that part, false if it doesn't
	 */
	public boolean instanceOf(ComputerKit a, ComputerPart o) {
		return a.getComputer().contains(o);
	}

	/**
	 * returns how many times a specific ComputerPart is found in the computer
	 * @param a the computer
	 * @param o the part to check for
	 * @return how many times the specific part is found 
	 */
	public int numFound(ComputerKit a, ComputerPart o) {

		int numCount = 0;
		for (int i = 0; i < a.getComputer().size(); i++) {  // loop through the arraylist and search for the part
			if (a.getComputer().get(i) == o) { // if the part is found increment the number of times found
				numCount++;
			}

		}
		return numCount;
}
}