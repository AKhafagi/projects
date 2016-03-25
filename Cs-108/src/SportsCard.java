/**
 * Program # 6, Anas Khafagi, Masc1916. 
 * Creates a Sports card that has a game, a
 * name, an image file, a team , and a position. 
 * CS108-2 04-01-15
 * @author Anas khafagi
 */

public class SportsCard extends TradingCard {
	private String team;
	private String position;
	private boolean isRookie;

	/**
	 * Constructors a sports card
	 */
	public SportsCard() {
		super("Baseball");
		this.team = "";
		this.position = "";
		this.isRookie = true;
	}

	/**
	 * constructors a Character card with the name, game, imagefile, team and
	 * position parameters
	 * @param name
	 *          : name of the player.
	 * @param game
	 *          : the game.
	 * @param imageFile
	 *          : image of the character.
	 * @param team : team of the player
	 * @param position : position of the player
	 * @param isRookie : wether or not the player is a rookie 
	 */
	public SportsCard(String name, String game, String imageFile, String team,
			String position, boolean isRookie) {
		super(name, game, imageFile);
		this.position = position;
		this.team = team;
		this.isRookie = isRookie;
	}

	/**
	 * sets the name of the player 
	 * @param name: name of the player
	 */
	public void updateName(String name) {
		this.name = name;
	}

	/**
	 * returns the team of the player
	 * @return team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * sets the team of the player
	 * @param team
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * returns the position of the player
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * sets the position of the player
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * returns wether the player is a rookie or not
	 * @return true or false
	 */
	public boolean isRookie() {
		return isRookie;
	}

	/**
	 * sets wether the player is a rookie or not
	 * @param isRookie: true or false
	 */
	public void setRookie(boolean isRookie) {
		this.isRookie = isRookie;
	}

	/* (non-Javadoc)
	 * overrides the trading card print method to print out the game, the name, the team , and the position.
	 */
	@Override
	public void print() {
		if (team.equals("")) { //if team is empty string  print out game only. 
			System.out.println(getGame() + ": ");
			System.out.println();
		}
		else { // else printout game : name as as position with the team.
			System.out.println(getGame() + ": " + this.name + " as a " + getPosition()
					+ " With the " + getTeam());
			System.out.println();
		}
	}
}