import java.util.ArrayList;
import java.util.Scanner;

public class CardList {

	private ArrayList<Card> l = new ArrayList<Card>();

	// String name, String desc, int damage (plus strength)., int block, int draw,
	// int strength, int nrgGain, int nrgCost, boolean exhausts
	// 3 of each common, 2 of each common, 1 of each rare
	public void createCardPool() {
		l.add(new Card("Shrug it off", "Gain 8 block, draw 1 card.", 0, 8, 1, 0, 0, 1, false)); // common
		l.add(new Card("Shrug it off", "Gain 8 block, draw 1 card.", 0, 8, 1, 0, 0, 1, false));
		l.add(new Card("Shrug it off", "Gain 8 block, draw 1 card.", 0, 8, 1, 0, 0, 1, false));

		l.add(new Card("Seeing red", "Gain 2 energy, exhaust this card.", 0, 0, 0, 0, 2, 0, true)); // uncommon
		l.add(new Card("Seeing red", "Gain 2 energy, exhaust this card.", 0, 0, 0, 0, 2, 0, true));

		l.add(new Card("Battle trance", "Draw 3 cards. Exhaust this card.", 0, 0, 3, 0, 0, 0, true)); // rare

		l.add(new Card("Iron wave", "Gain 5 block, deal 5 damage (plus strength). ", 5, 5, 0, 0, 0, 1, false)); // common
		l.add(new Card("Iron wave", "Gain 5 block, deal 5 damage (plus strength). ", 5, 5, 0, 0, 0, 1, false));
		l.add(new Card("Iron wave", "Gain 5 block, deal 5 damage (plus strength).", 5, 5, 0, 0, 0, 1, false));

		l.add(new Card("Dash", "Gain 12 block, deal 12 damage (plus strength).", 12, 12, 0, 0, 0, 2, false)); // uncommon
		l.add(new Card("Dash", "Gain 12 block, deal 12 damage (plus strength).", 12, 12, 0, 0, 0, 2, false));

		l.add(new Card("Bludgeon", "Deal 32 damage (plus strength)", 32, 0, 0, 0, 0, 3, false)); // rare

		l.add(new Card("Outrage", "Deal 4 damage (plus strength), gain 1 strength.", 4, 0, 0, 1, 0, 0, false)); // common
		l.add(new Card("Outrage", "Deal 4 damage (plus strength), gain 1 strength.", 4, 0, 0, 1, 0, 0, false));
		l.add(new Card("Outrage", "Deal 4 damage (plus strength), gain 1 strength.", 4, 0, 0, 1, 0, 0, false));

		l.add(new Card("Backflip", "Gain 5 block, draw 2 cards.", 0, 5, 2, 0, 0, 1, false)); // common
		l.add(new Card("Backflip", "Gain 5 block, draw 2 cards.", 0, 5, 2, 0, 0, 1, false));
		l.add(new Card("Backflip", "Gain 5 block, draw 2 cards.", 0, 5, 2, 0, 0, 1, false));

		l.add(new Card("Deflect", "Gain 4 block.", 0, 4, 0, 0, 0, 0, false)); // common
		l.add(new Card("Deflect", "Gain 4 block.", 0, 4, 0, 0, 0, 0, false));
		l.add(new Card("Deflect", "Gain 4 block.", 0, 4, 0, 0, 0, 0, false));

		l.add(new Card("Backstab", "Deal 11 damage (plus strength), exhaust this card.", 11, 0, 0, 0, 0, 0, true)); // uncommon
		l.add(new Card("Backstab", "Deal 11 damage (plus strength), exhaust this card.", 11, 0, 0, 0, 0, 0, true));

		l.add(new Card("Wheel kick", "Deal 15 damage (plus strength), draw 2 cards.", 15, 0, 2, 0, 0, 2, false)); // uncommon
		l.add(new Card("Wheel kick", "Deal 15 damage (plus strength), draw 2 cards.", 15, 0, 2, 0, 0, 2, false));

		l.add(new Card("Adrenaline", "Gain 1 energy, draw 2 cards.", 0, 0, 2, 0, 1, 0, true)); // rare

		l.add(new Card("On guard", "Deal 25 damage (plus strength), gain 25 block.", 25, 25, 0, 0, 0, 3, false)); // rare

		l.add(new Card("Recuperate", "Block 8, gain 2 strength.", 0, 8, 0, 2, 0, 2, false)); // unncommon
		l.add(new Card("Recuperate", "Block 8, gain 2 strength.", 0, 8, 0, 2, 0, 2, false));

		l.add(new Card("Pommel Strike", "Deal 9 damage (plus strength), draw 1 card.", 9, 0, 1, 0, 0, 1, false)); // common
		l.add(new Card("Pommel Strike", "Deal 9 damage (plus strength), draw 1 card.", 9, 0, 1, 0, 0, 1, false));
		l.add(new Card("Pommel Strike", "Deal 9 damage (plus strength), draw 1 card.", 9, 0, 1, 0, 0, 1, false));

		l.add(new Card("Exert", "Deal 15 damage (plus strength), lose 3 strength.", 15, 0, 0, -3, 0, 1, false)); // uncommon
		l.add(new Card("Exert", "Deal 15 damage (plus strength), lose 3 strength.", 15, 0, 0, -3, 0, 1, false));

		l.add(new Card("Chaotic Defense", "Block 8, lose 1 strength.", 0, 8, 0, -1, 0, 1, false)); // uncommon
		l.add(new Card("Chaotic Defense", "Block 8, lose 1 strength.", 0, 8, 0, -1, 0, 1, false));
	}

	public ArrayList<Card> getPool() {
		return l;
	}

	public Card getCard() {
		int card = (int) (Math.random() * l.size());
		return l.get(card);
	}

	public void removeCard(String cardName) {
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getName().equals(cardName)) {
				l.remove(i);
				i = l.size() + 69;
			}
		}
	}

	public void cardReward(Player user) {
		Card c1 = getCard();

		Card c2 = getCard();

		while (c1.getName().equals(c2.getName())) {
			c2 = getCard();
		}

		Card c3 = getCard();

		while ((c3.getName().equals(c1.getName())) || (c3.getName().equals(c2.getName()))) {
			c3 = getCard();
		}

		clearScreen();
		System.out.println("Choose one card to add to your deck!");
		System.out.println("");

		wait(500);
		System.out.println("0: Skip card reward.");
		wait(500);
		System.out.println("1: " + c1);
		wait(500);
		System.out.println("2: " + c2);
		wait(500);
		System.out.println("3: " + c3);

		Scanner input = new Scanner(System.in);

		int choice = 0;
		boolean validInput = false;

		while (!validInput) {
		    try {
		        System.out.print("Enter your choice: ");
		        choice = Integer.parseInt(input.nextLine());

		        if (choice >= 0 && choice <= 3) {
		            validInput = true;
		        } else {
		            System.out.println("Invalid choice! Please enter a number between 0 and 3.");
		        }
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid input. Please enter an integer.");
		    }
		}

		if (choice == 1) {
		    user.getDeck().addCard(c1);
		    removeCard(c1.getName());
		} else if (choice == 2) {
		    user.getDeck().addCard(c2);
		    removeCard(c2.getName());
		} else if (choice == 3) {
		    user.getDeck().addCard(c3);
		    removeCard(c3.getName());
		} else if (choice == 0) {
		    return;
		}

	}

	public static void wait(int milliseconds) {
		long startTime, millis, current;
		millis = 0;

		startTime = System.currentTimeMillis();
		while (millis < startTime + milliseconds) {
			millis = System.currentTimeMillis();
		}

	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}