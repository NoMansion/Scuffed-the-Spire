import java.util.Scanner;

public class Campfire extends Encounter {
	private Player user;
	private CardList cardPool;

	public Campfire(Player u, CardList c) {
		user = u;
		cardPool = c;
	}

	public void updateScreen() {
		Scanner input = new Scanner(System.in);

		wait(250);
		System.out.println("Choose To Rest OR Pick A Card");

		wait(250);
		System.out.println("1 : Rest ( " + user.getHp() + " (Max HP) * 0.3 ) = +" + (int) (user.getHp() * 0.3)
				+ " HP: (Current HP = " + user.getcHp() + " )");
		wait(250);
		System.out.println("2 : Card Reward");

		int choice = 0;
		boolean validInput = false;

		while (!validInput) {
		    try {
		        String userInput = input.next(); // Get the next input as a string
		        choice = Integer.parseInt(userInput); // Try to parse the input as an integer

		        if (choice == 1 || choice == 2) {
		            validInput = true; // Valid input, break the loop
		        } else {
		            System.out.println("Invalid choice. Please enter 1 or 2.");
		        }
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid input. Please enter an integer.");
		    }
		}
		
		// Conditional statement for either choice
		if (choice == 1) {
			// Heals player HP * 0.3 (e.g., 80 * 0.3 = 24)
			user.upHp((int) (user.getHp() * 0.3));
		} else if (choice == 2) {
			cardPool.cardReward(user);
		}
	}

	public String toString() {
		return "🔥";
	}

	public static void wait(int milliseconds) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < milliseconds) {
			// Busy-waiting for the specified duration
		}
	}
}
