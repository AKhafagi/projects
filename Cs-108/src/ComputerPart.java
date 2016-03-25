/**
 * Program Extra Credit, Anas Khafagi, Masc1916. 
 * Creates a computer part with a name and a price
 * CS108-2 04-15-15
 * @author Anas khafagi
 */
public class ComputerPart {
	protected String item;
	protected double price;

	/**
	 * constructors a ComputerPart with no name and 0.0 for the price
	 */
	public ComputerPart() {
		this.item = "";
		this.price = 0.0;
	}

	/**
	 * constructors a ComputerPart with user input name and price
	 * @param item the name of the computer part
	 * @param price	the price of the computer part
	 */
	public ComputerPart(String item, double price) {
		this.item = item;
		this.price = price;
	}

	/**
	 * returns the name of the computer part
	 * @return the name of the computer part
	 */
	public String getItem() {
		return item;
	}

	/**
	 * sets the name of the computer part
	 * @param item the name of the computer part
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * returns the price of the computer part
	 * @return the price of the computer part
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets the price of the computer part
	 * @param price the price of the computer price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
