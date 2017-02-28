/**
 *Program # 5, Anas Khafagi, Masc1916.
 * a program that creates a deck of cards and plays a simple game.
 * Card Class: Creates a Card object, gets and sets the suit and rank, and prints them out in one string.
 * CS108-2.
 * 03-15-15.
 * @Anas Khafagi
 */
public class Card {
	private String suit;
	private String rank;

	public Card() { // Default constructor to create a card object.
		suit = "";
		rank = "";
	}
	/**
	 * Creates a card object with 2 string parameters.
	 * @param String rank
	 * @param String Suit
	 */
	public Card(String r, String s) {     // Constructor to overload the card object.
		suit = s;
		rank = r;
	}
	/**
	 * Gets the suit.
	 * @return the String Suit
	 */
	public String getSuit() {   // Method to return the suit of the card object.
		return suit;
	}
	/**
	 * Sets the field Suit to the String parameter Suit.
	 * @param String Suit
	 */
	public void setSuit(String suit) {   // Method to set the suit of the card object.
		this.suit = suit;
	}
	/**
	 * Gets the rank.
	 * @return the String Rank
	 */
	public String getRank() {      // Method to return the rank of the card object.
		return rank;
	}
	/**
	 * Sets the field Suit to the String parameter Suit.
	 * @param String Rank
	 */
	public void setRank(String rank) {    // Method to set the rank of the card object.
		this.rank = rank;
	}
	/**
	 * Prints out the combined String of Rank and Suit.
	 */
	public String toString() { // to-string to print both suit and rank in one String.
		return (rank + " of " + suit);
	}
}