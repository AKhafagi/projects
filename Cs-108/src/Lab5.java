/**
 * Poker style game tests Card and Deck classes
 *
 * CS108 Lab5
 * Date   02-28-15
 * @author Patty Kraft
 */


public class Lab5 {

	private static final int CARDS_IN_HAND = 5;
	private static final int HANDS = 5;

	public static void main(String[] args) throws Exception {

		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
		test10();
		test11();
		test12();
		test13();
		test14();
		test15();
		test16();
		test17();
	}

	private static void test1() {
		System.out.println("\nTest 1: Create uninitialized Card\n");
		try {
			Card card = new Card();
			System.out.println("Uninitialized card created.");
			System.out.println(card.toString());
		} catch (Exception e) {
			System.out.println("Test 1 Exception: " + e.getMessage());
		}
	};

	private static void test2() {
		System.out.println("\nTest 2: Initialize a new Card\n");
		try {
			Card card = new Card("Two", "Hearts");
			System.out.println("Initialized card created.");
			System.out.println(card.toString());
		} catch (Exception e) {
			System.out.println("Test 2 Exception: " + e.getMessage());
		}
	}
	private static void test3() {
		System.out.println("\nTest 3: Initialize to non-Deck values\n");
		try {
			Card card = new Card();
			card.setSuit("Stars");
			card.setRank("Millions");
			System.out.println(card.toString());
		} catch (Exception e) {
			System.out.println("Test 3 Exception: " + e.getMessage());
		}
	}
	private static void test4() {
		System.out.println("\nTest 4: Get hand from unshuffled Deck\n");
		try {
			Deck deck = new Deck();
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
				System.out.println(hand[i].toString());
			}
		} catch (Exception e) {
			System.out.println("Test 4 Exception: " + e.getMessage());
		}
	}
	private static void test5() {
		System.out.println("\nTest 5: Get hand from unshuffled Deck by getters\n");
		try {
			Deck deck = new Deck();
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
				System.out.println(hand[i].getRank() + " of " + hand[i].getSuit());
			}
		} catch (Exception e) {
			System.out.println("Test 5 Exception: " + e.getMessage());
		}
	}
	private static void test6() {
		System.out.println("\nTest 6: Get hand from shuffled Deck\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
				System.out.println(hand[i].toString());
			}
		} catch (Exception e) {
			System.out.println("Test 6 Exception: " + e.getMessage());
		}
	}
	private static void test7() {
		System.out.println("\nTest 7: Test for flush from unshuffled Deck\n");
		try {
			Deck deck = new Deck();
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
			}
			String flushSuit = hand[0].getSuit();
			System.out.print("Flush: " + flushSuit + " ");
			System.out.println(hasFlush(hand));
		} catch (Exception e) {
			System.out.println("Test 7 Exception: " + e.getMessage());
		}
	}
	private static void test8() {
		System.out.println("\nTest 8: Test for flush from shuffled Deck\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
			}
			String flushSuit = hand[0].getSuit();
			System.out.print("Flush: " + flushSuit + " ");
			System.out.println(hasFlush(hand));
		} catch (Exception e) {
			System.out.println("Test 8 Exception: " + e.getMessage());
		}
	}
	private static void test9() {
		System.out.println("\nTest 9: Test for canned pair should be true\n");
		try {
			Card[] hand = new Card[CARDS_IN_HAND];
			hand[0] = new Card("Four", "Clubs");
			hand[1] = new Card("Ace", "Clubs");
			hand[2] = new Card("King", "Diamonds");
			hand[3] = new Card("Ten", "Hearts");
			hand[4] = new Card("Four", "Hearts");
			System.out.println("Has Pair: " + hasPair(hand) + " ");
		} catch (Exception e) {
			System.out.println("Test 9 Exception: " + e.getMessage());
		}
	}
	private static void test10() {
		System.out.println("\nTest 10: Test for pair in shuffled Deck\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			Card[] hand = new Card[CARDS_IN_HAND];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = deck.getCard();
			}
			System.out.println("Has Pair: " + hasPair(hand) + " ");
		} catch (Exception e) {
			System.out.println("Test 10 Exception: " + e.getMessage());
		}
	}
	private static void test11() {
		System.out.println("\nTest 11: Get total Cards in new Deck\n");
		try {
			Deck deck = new Deck();
			System.out.println("Total cards in deck " + deck.totalCards());
		} catch (Exception e) {
			System.out.println("Test 11 Exception: " + e.getMessage());
		}
	}
	private static void test12() {
		System.out.println("\nTest 12: Get total Cards in half-dealt Deck\n");
		try {
			Deck deck = new Deck();
			for (int i = 0; i < 26; i++)
				deck.getCard();
			System.out.println("Total cards in deck " + deck.totalCards());
		} catch (Exception e) {
			System.out.println("Test 12 Exception: " + e.getMessage());
		}
	}
	private static void test13() {
		System.out.println("\nTest 13: Attempt to get 100 Cards from Deck\n");
		try {
			Deck deck = new Deck();
			for (int i = 0; i < 100; i++)
				deck.getCard();
			System.out.println("Total cards in deck " + deck.totalCards());
		} catch (Exception e) {
			System.out.println("Test 13 Exception: " + e.getMessage());
		}
	}
	private static void test14() {
		System.out.println("\nTest 14: Check for flush in newly created Deck\n");
		try {
			Deck deck = new Deck();
			for (int hands = 1; hands <= HANDS; hands++) {
				Card[] hand = new Card[CARDS_IN_HAND];
				for (int i = 0; i < hand.length; i++) {
					hand[i] = deck.getCard();
				}
				String flushSuit = hand[0].getSuit();
				System.out.print("Flush: " + flushSuit + " ");
				System.out.println(hasFlush(hand));
			}
		} catch (Exception e) {
			System.out.println("Test 14 Exception: " + e.getMessage());
		}
	}
	private static void test15() {
		System.out.println("\nTest 15: Check for flush in shuffled Deck\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			for (int hands = 1; hands <= HANDS; hands++) {
				Card[] hand = new Card[CARDS_IN_HAND];
				for (int i = 0; i < hand.length; i++) {
					hand[i] = deck.getCard();
				}
				String flushSuit = hand[0].getSuit();
				System.out.print("Flush: " + flushSuit + " ");
				System.out.println(hasFlush(hand));
			}
		} catch (Exception e) {
			System.out.println("Test 15 Exception: " + e.getMessage());
		}
	}
	private static void test16() {
		System.out.println("\nTest 16: Get pairs in 10 hands of Cards\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			for (int hands = 1; hands <= HANDS*2; hands++) {
				Card[] hand = new Card[CARDS_IN_HAND];
				for (int i = 0; i < hand.length; i++) {
					hand[i] = deck.getCard();
				}
				System.out.println("Has Pair: " + hasPair(hand) + " ");
			}
		} catch (Exception e) {
			System.out.println("Test 16 Exception: " + e.getMessage());
		}
	}
	private static void test17() {
		System.out.println("\nTest 17: Attempt to get pairs in 12 hands of Cards\n");
		try {
			Deck deck = new Deck();
			deck.shuffle(100);
			for (int hands = 1; hands <= HANDS*2+2; hands++) {
				Card[] hand = new Card[CARDS_IN_HAND];
				for (int i = 0; i < hand.length; i++) {
					hand[i] = deck.getCard();
				}
				System.out.println("Has Pair: " + hasPair(hand) + " ");
			}
		} catch (Exception e) {
			System.out.println("Test 17 Exception: " + e.getMessage());
		}
	}

	private static boolean hasFlush(Card[] cards) {
		for (int i = 1; i < cards.length; i++) {
			System.out.print(cards[i].getSuit() + " ");
			if (!cards[i].getSuit().equals(cards[0].getSuit())) return false;
		}
		return true;
	}
	private static boolean hasPair(Card[] cards) {
		for (int i = 1; i < cards.length; i++) {
			for (int j = 0; j < i; j++) {
				//System.out.println (cards[j].getRank() + " " + cards[i].getRank());
				if (cards[j].getRank().equals(cards[i].getRank())) {
					return true;
				}
			}
		}
		return false;
	}
}