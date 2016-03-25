/**
 * Program # 6, Anas Khafagi, Masc1916. 
 * Driver class to test the other classes and objects 
 * CS108-2 04-01-15
 * @author Anas Khafagi
 */
public class Lab6 {
	/**
	 * @param card: a TradingCard object
	 *trading class object prints out printing.... please wait and
	 *prints out the method of that specific object
	 */
	public static void printCard(TradingCard card) {
		System.out.println("Printing.... Please wait");
		card.print();
	}

	public static void main(String args[]) {

		TradingCard uno1 = new TradingCard("Reverse", "Uno", "Reverse.jpg");	// creates a TradingCard object called uno1.
																																					
		SportsCard rogerClemens = new SportsCard("Roger Clements", "BaseBall",
				"Roger23238478239473.jpg", "Red Sox", "Pitcher", false);	// creates a sportsCard object called rogerclemens.
		SportsCard babeRuth = new SportsCard("Babe Ruth", "Baseball",
				"BabeRuth12239230.jpg", "Red Sox", "Pitcher", true);
		CharacterCard slifer = new CharacterCard("Slifer the Sky Dragon", "Yugioh",
				"Slifer02132139.jpg", 5000, "Divine");	// creates a CharacterCard object  called slifer.
		CharacterCard obelisk = new CharacterCard("Obelisk the Tormentor",
				"Yugioh", "Obelisk203029.jpg", 4000, "Divine");
		CharacterCard ra = new CharacterCard("Winged Dragon Of Ra", "Yugioh",
				"Ra02349823.jpg", 8000, "Divine");
		TradingCard t1 = new TradingCard();	// creates an empty TradingCard object called t1.
		TradingCard t2 = new SportsCard();	// creates an empty TradingCard object called t2.
		TradingCard t3 = new CharacterCard();	// creates an empty TradingCard object called t3.
		t1.print();	// calls TradingCard Class' print method.
		t2.print(); // calls SportsCard Class' print method.
		t3.print(); // calls CharacterCard Class' print method.
		uno1.print();	// calls TradingCard Class' print method.
		rogerClemens.print();	// calls SportsCard Class' print method.
		babeRuth.print();
		slifer.print();	// calls CharacterCard Class' print method.
		obelisk.printAll(); // calls CharacterCard Class' printAll method.
		ra.printAll();
		printCard(ra); // calls the printcard with the ra CharacterCard Parameter.
		printCard(rogerClemens); // calls the printcard with the rogerclemens SportsCard Parameter.
		printCard(uno1); // calls the printcard with the uno1 TradingCard Parameter.

	}

}
