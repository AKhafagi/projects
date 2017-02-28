/**
 * Program # 6, Anas Khafagi, Masc1916. 
 * Creates a Character card that has a
 * game, a name, an image file, a number , and an attribute. 
 * CS108-2 04-01-15
 * @author Anas khafagi
 */
public class CharacterCard extends TradingCard {
	private int number;
	private String attribute;

	/**
	 * constructs a Character card for Pokemon.
	 */
	public CharacterCard() {
		super("Pokemon");
		this.number = 0;
		this.attribute = "";
	}

	/**
	 * constructors a Character card with the name, game, imagefile, number and
	 * attribute parameters
	 * 
	 * @param name
	 *          : name of the character.
	 * @param game
	 *          : the game.
	 * @param imageFile
	 *          : image of the character.
	 * @param number
	 *          : Hp number of the character.
	 * @param attribute
	 *          : attribute of the character.
	 */
	public CharacterCard(String name, String game, String imageFile, int number,
			String attribute) {
		super(name, game, imageFile);
		this.number = number;
		this.attribute = attribute;
	}

	/**
	 * sets the name of the character to the parameter name.
	 * 
	 * @param name
	 *          : name of the Charcter.
	 */
	public void updateName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see TradingCard#print() overrides TradingCard print() to print the name of
	 * the game and the name of the character followed by and empty line
	 */
	@Override
	public void print() {
		System.out.println(getGame() + ": " + this.name);
		System.out.println();

	}

	/**
	 * @return number: returns the hp number of the charcter.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * sets the hp of the charcter.
	 * 
	 * @param number
	 *          : hp of the character
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * returns the attribute of the character.
	 * 
	 * @return
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * sets the attribute of the charcter
	 * 
	 * @param attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * prints out all the fields of the character.
	 */
	public void printAll() {
		System.out.println(getGame() + ": " + this.name);
		System.out.println("HP: " + getNumber() + "\n" + "Powers: "
				+ getAttribute());
		System.out.println();
	}
}