import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Combat extends Encounter {
	private Enemy awp; // the enemy
	private ArrayList<Card> hand = new ArrayList<Card>(); // list of user's cards in their hand
	private ArrayList<Card> discardPile = new ArrayList<Card>(); // list of user's cards they have played
	private ArrayList<Card> drawPile = new ArrayList<Card>(); // list of cards user has that have not yet drawn
	private ArrayList<Card> pDeck = new ArrayList<Card>();
	private CardList cardPool;
	private int nrg; // energy that the user gets every turn
	private int cNrg; // current energy that the player has mid turn
	private Player user; // a reference to the player object in Main
	private int enemyNum;

	public Combat(Player user, CardList cp) {
		this.user = user;
		cardPool = cp;
		enemyNum = -5;
		user.resetStrength();
	}

	public Combat(Player user, CardList cp, int index) {
		this.user = user;
		cardPool = cp;
		enemyNum = index;
		user.resetStrength();
	}

	public void shuffle(ArrayList<Card> x) {
		Collections.shuffle(x);
	}

	public void draw(int num) {
		// from pDeck draws cards and adds them to Arraylist hand
		// adds card(s) to hand equal to num
		// takes those cards out of drawPile
		for (int i = 0; i < num; i++) {
			if (drawPile.size() <= 0) {
				shuffle(discardPile);
				drawPile = discardPile;
				discardPile = new ArrayList<Card>();
			}

			int temp = (int) (Math.random() * drawPile.size());
			hand.add(drawPile.get(temp));
			drawPile.remove(temp);
		}
	}

	public void discardAll() {
		for (int i = 0; i < hand.size(); i++) {
			discardPile.add(hand.get(i));
		}
		hand = new ArrayList<Card>();
	}

	public void makeMove() {
		Scanner input = new Scanner(System.in);

		System.out.println("0: END TURN");

		for (int i = 1; i <= hand.size(); i++) {
			wait(250);
			System.out.println(i + ": " + hand.get(i - 1));
		}

		int choice = -1; // Initialize with an invalid value
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();

                // Validate choice
                if (choice == 0) {
                    validInput = true; // Exit the loop if choice is 0
                } else if (choice < 0 || choice > hand.size()) {
                    System.out.println("\nInvalid choice! Choose a number between 0 and " + hand.size() + ".");
                } else if (cNrg < hand.get(choice - 1).getNrgCost()) {
                    System.out.println("\nNot enough energy...");
                } else {
                    validInput = true; // Valid choice
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // Consume the invalid input
            }
        }

		System.out.println("\n\n----------------------------------------------------\n");

		if (choice == 0) {
			cNrg = -1;
		} else {

			cNrg -= hand.get(choice - 1).getNrgCost();
			playCard(choice - 1);
		}

	}

	public void playCard(int choice) {

		if (hand.get(choice).getDamage() > 0) {
			awp.lowHp(hand.get(choice).getDamage() + user.getStrength());
		} else
			awp.lowHp(hand.get(choice).getDamage());

		user.upBlock(hand.get(choice).getBlock());
		draw(hand.get(choice).getDraw());
		user.upStrength(hand.get(choice).getStrength());
		cNrg += hand.get(choice).getNrgGain();

		if (hand.get(choice).isExhausts()) {

		} else {
			// card goes to discard pile
			discardPile.add(hand.get(choice));
		}
		hand.remove(choice);
	}

	public void localConstruct() {
		if (enemyNum >= 0) {
			awp = new Enemy(enemyNum);
		} else {
			awp = new Enemy((int) (Math.random() * 8/* # of enemies */));
		}
		for (int i = 0; i < user.getDeck().getPlayerDeck().size(); i++) {
			pDeck.add(user.getDeck().getPlayerDeck().get(i));
		}
		drawPile = pDeck;
		hand = new ArrayList<Card>();
		shuffle(drawPile);
		nrg = user.getEnergy();
		discardPile = new ArrayList<Card>();
	}

	public void updateScreen() {
		localConstruct();
		draw(5);
		while (!user.dead() && !awp.dead()) {

			cNrg = nrg;
			wait(250);
			awp.genMove();
			awp.getMove(user);
			wait(250);
			System.out.println(awp.returnHealth());
			wait(250);
			System.out.println();
			wait(250);
			System.out.println(awp.getName() + " has " + awp.getBlock() + " block this turn");
			wait(250);
			System.out.println(awp.getName() + " has " + awp.getStrength() + " strength");
			wait(250);
			System.out.println("\n" + "-----------------------");
			wait(250);

			while (cNrg >= 0) {

				if (awp.dead()) {
					break;
				}

				wait(250);
				System.out.println("\n");
				wait(250);
				System.out.println("Ironclad");
				wait(250);
				System.out.println(user.getcHp() + " / " + user.getHp() + " HP");
				wait(250);

				System.out.println("\n" + "You have " + cNrg + " energy left to use");
				wait(250);
				System.out.println("You have " + user.getBlock() + " block this turn");
				wait(250);
				System.out.println("You have " + user.getStrength() + " strength");
				wait(250);

				System.out.println();
				makeMove();

				wait(250);
				awp.getMove(user);
				System.out.println(awp.returnHealth());

				wait(250);
				System.out.println();
				wait(250);
				System.out.println(awp.getName() + " has " + awp.getBlock() + " block this turn");
				wait(250);
				System.out.println(awp.getName() + " has " + awp.getStrength() + " strength");
				// wait(1000);

			}
			clearScreen();
			awp.move(user);
			discardAll();
			draw(5);
			user.setBlock(0);
			awp.setBlock(0);
		}

		// if enemy is dead and is the boss
		if (awp.dead() && awp.getMonNum() == 69) {
			System.out.println("You have slayed The Scuffed Spire! Congratulations!");
			System.exit(0);
		}

		// if enemy is dead, continue
		if (awp.dead()) {
			System.out.println("You have defeated the enemy! Congratulations!");
			cardPool.cardReward(user);
			// whatever else happens then combat is done
		} else // user is dead and the program ends
		{
			user.isDead();
			System.exit(0);
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

	// copy pasted this bad boy from intellipat (in case we need to cite or
	// whatever)
	// does what is says. clears the screen.
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public String toString() {
		return "😈";
	}

}