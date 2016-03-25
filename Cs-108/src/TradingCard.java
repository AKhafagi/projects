/**
 * Program # 6, Anas Khafagi, Masc1916. 
 * Creates a Trading car that has a game, a
 * name, and an image file. 
 * CS108-2 04-01-15
 * 
 * @author Anas Anas khafagi
 */
public class TradingCard {
	private String game;
	protected String name;
	protected String imageFile;

	/**
	 * Constructs a Trading Card.
	 */
	public TradingCard() {
		this.name = "";
		this.game = "";
		this.imageFile = "";
	}

	/**
	 * Constructs a Trading Card for a specific game.
	 * 
	 * @param game
	 *          : the name of the game that needs constructed card.
	 */
	public TradingCard(String game) {
		this.name = "";
		this.game = game;
		this.imageFile = "";
	}

	/**
	 * Constructs a Trading card using the name, game, and Imagefile parameters.
	 * 
	 * @param name
	 *          : name of the Card.
	 * @param game
	 *          : name of the Game.
	 * @param imageFile
	 *          : Image of the Card.
	 */
	public TradingCard(String name, String game, String imageFile) {
		this.name = name;
		this.game = game;
		this.imageFile = imageFile;
	}

	/**
	 * Sets the name of the game of a specific Trading card.
	 * 
	 * @param game
	 *          : the name of the game that is set.
	 * 
	 */
	public void setGame(String game) {
		this.game = game;
	}

	/**
	 * returns the name of the game.
	 * 
	 * @return game: name of the game.
	 */
	public String getGame() {
		return this.game;
	}

	/**
	 * prints out the name of the game and an empty line.
	 */
	public void print() {
		System.out.println(game);
		System.out.println();
	}
}