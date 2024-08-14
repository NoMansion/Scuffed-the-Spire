import java.util.Collections;
import java.util.ArrayList;

public class Deck {
	// player deck
	private ArrayList<Card> pDeck = new ArrayList<Card>();

	// initializes the arraylist of cards that make up a deck with
	// a specific array of cards passed in based on player deck
	public Deck() {
		// adds 3 strikes
		for (int i = 0; i < 3; i++) {
			pDeck.add(new Card("Strike", "Deal 6 damage (plus strength).", 6, 0, 0, 0, 0, 1, false));
		}

		// adds 3 defends
		for (int i = 0; i < 3; i++) {
			pDeck.add(new Card("Defend", "Gain 5 block.", 0, 5, 0, 0, 0, 1, false));
		}

		// adds 1 inflame
		pDeck.add(new Card("Inflame", "Gain 2 strength.", 0, 0, 0, 2, 0, 1, false));

	}

	// adds a card to the deck
	public void addCard(Card iCantDoThisAnymore) {
		pDeck.add(iCantDoThisAnymore);
	}

	// returns an arrayList of every card in the deck
	public ArrayList<Card> getPlayerDeck() {
		return pDeck;
	}

	// shuffles the deck into a random order
	public void shuffle() {
		Collections.shuffle(pDeck);
	}

	public String toString() {
		String answer = "Your deck:" + "\n";

		for (int i = 0; i < pDeck.size(); i++) {
			answer += pDeck.get(i).getName() + "\n";
		}

		return answer;

	}
}