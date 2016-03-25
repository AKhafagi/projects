/**
 * Program # 5, Anas Khafagi, Masc1916 
 * a program that creates a deck of cards
 * Deck Class: Creates a Deck object , intializes the deck with 52 Card objects from an instance of the Card object,
 * shuffles the deck, and gets the top card of the deck.
 * and plays a simple game. 03-01-15
 * @Anas Khafagi
*/

public class Deck {
	private Card[] deck;
	private int inDeck;
	private final int SIZE_OF_DECK = 52;

	public Deck() { // Default constructotr to create a deck object.
		deck = new Card[SIZE_OF_DECK];
		inDeck = 0;
		this.init(); // Calling the method to intialize the deck with 52 card objects.

	}
	/**
	 * Intializes the Deck array with 52 Cards.
	 */
	private void init() { // Method to intialize the deck array with 52 card objects
		String[] suits = { "Spades", "Clubs", "Hearts", "Diamonds" };
		String[] rank = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
				"Eight", "Nine", "Ten", "Jack", "Queen", "King" };
		for (int s = 0; s < suits.length; s++) { // Loop to intialize the deck with a rank and suit and make the into a card object
			for (int r = 0; r < rank.length; r++) {
				deck[rank.length * s + r] = (new Card(rank[r], suits[s]));
				inDeck++; // Incrementing inDeck to show how many cards are in the deck after intializing
			}
		}
	}
	/**
	 * Shuffles the deck num times.
	 * @param num the number of times to Shuffle the deck.
	 */
	public void shuffle(int num) { //  method to shuffle the deck num times.
		try {  // check if the number of shuffles is valid.

			if (num <= 0) {

				throw new Exception("invalid number of shuffles please try agin"
						+ "Please change number of shuffles and try agin");

			}
		}

		catch (Exception e) { // catch the exception thrown if the number of shuffles is invalid

			System.out.println(e);

		}
		Card cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * 52);
			int random2 = (int) (Math.random() * 52);
			cardTemp = deck[random1];
			deck[random1] = deck[random2];
			deck[random2] = cardTemp;

		}
	}
	/**
	 * Gets the top card in the deck
	 * @return the top of the card in the deck
	 */
	public Card getCard() { // a method to get the top card from the deck array
		try { // checks if there are enough cards in the deck
			if (inDeck <= 0) {
				throw new Exception("there are not enough cards in the deck");
			}
		}
		catch (Exception e) { // catches the exception thrown if there isn't enough cards.

			System.out.println(e);

		}
		int temp = inDeck;
		inDeck--;
		return this.deck[SIZE_OF_DECK - temp];

	}
	/**
	 * returns the total cards in the deck after the hands are delt.
	 * @return the total cards in the deck.
	 */
	public int totalCards() { // Method to return the number of cards in the deck after hands delt
		return this.inDeck;

	}
}