
import java.util.*;
public class Prog2_d {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // requesting user input
		System.out.println("Choose Number of Poker Players"); // ask user for number of players 
		final int NUMPLAYERS= scan.nextInt(); // number of Poker Players
		final int NUMCARDS=5; // number of cards in one hand of poker
		String [] suits = {"Spades","Hearts","Clubs","Diamonds"}; // array of suits of deck
		String [] value = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"}; // array of values of deck 
		String [] deck = new String[suits.length * value.length]; // array of 52 cards in a deck

		for(int i =0; i< value.length; i++) // for loop to intialize the 52 cards in a deck
		{
			for(int j = 0; j< suits.length; j++)
			{
				deck[suits.length *i+j] = value[i] + " Of " + suits[j];
			}
		}
		
		util.shuffle(deck);
		
		for(int i = 0; i < NUMPLAYERS; i++) // for loop to deal the poker hands to the players
		{
			System.out.println();
			for(int j =0; j <NUMCARDS; j++)
			{
				System.out.println(deck[NUMCARDS * i +j]);
			}
			
		}
		scan.close();
	}

}
