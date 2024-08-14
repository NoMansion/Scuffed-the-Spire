import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Map {
	private Encounter[][] map = new Encounter[16][5]; // change length back to 16 at some point
	private Player user;
	private int pIndex;// playerRow
	private int pCol;// player column;
	private CardList cardPool;

	public Map(Player user, CardList c) {
		cardPool = c;
		this.user = user;
		firstFloor();
		for (int i = 1; i < map.length - 1; i++) {
			randomFloor(i);
		}
		bossFloor();
		pIndex = 0;
		pCol = 2;
	}

	public void bossFloor() {
		map[map.length - 1][2] = new Combat(user, cardPool, 69);
	}

	public void firstFloor() {
		map[0][0] = new Combat(user, cardPool);
		map[0][2] = new Combat(user, cardPool);
		map[0][4] = new Combat(user, cardPool);
	}

	public boolean everyPossibleMove(int index) {
		boolean aMove = true;
		boolean temp = false;
		for (int c = 0; c < 5; c++) {
			if (map[index - 1][c] != null) {
				for (int x = c - 1; x <= c + 1; x++) {
					if (x >= 0 && x < 5) {
						if (map[index][x] != null) {
							temp = true;
						}
					}
				}
				if (!temp) {
					return false;
				}
			}
			temp = false;
		}
		return true;
	}

	// index is the current number of row that is being declared
	public void randomFloor(int index) {
		while (!everyPossibleMove(index)) {
			int ran = (int) (Math.random() * 3);
			if (ran == 0) {
				map[index][(int) (Math.random() * 5)] = new Combat(user, cardPool);
			} else if (ran == 1) {
				map[index][(int) (Math.random() * 5)] = new RandomEvent(user, cardPool);
			} else if (ran == 2) {
				map[index][(int) (Math.random() * 5)] = new Campfire(user, cardPool);
			}
		}
	}

	public boolean oneMove(int row, int col, String direction) {// directions (l)eft, (r)ight, (f)orwards
		if (direction.equals("l") && col > 0) {
			return (map[row + 1][col - 1] != null);
		} else if (direction.equals("r") && (col < map[0].length - 1)) {
			return (map[row + 1][col + 1] != null);
		} else if (direction.equals("f")) {
			return (map[row + 1][col] != null);
		}
		return false;
	}

	public void printCurrent(int dex) {
		System.out.print("|");
		for (int x = 0; x < map[0].length; x++) {
			if (map[dex][x] != null) {
				System.out.print(map[dex][x]);
				System.out.print(" ");
			} else
				System.out.print("   ");
			System.out.print("|");
		}
		System.out.println("");
		System.out.println("|---+---+---+---+---|");
	}

	public void printFirst() {
		System.out.println("|---+---+---+---+---|");
		System.out.print("|");
		for (int x = 0; x < map[0].length; x++) {
			if (map[0][x] != null) {
				System.out.print(map[0][x]);
				System.out.print(" ");
			} else
				System.out.print("   ");
			System.out.print("|");
		}
		System.out.println("");
		System.out.println("|---+---+---+---+---|");

	}

	public void printBoss() {
		System.out.println("|---+---+---+---+---|");
		System.out.println("|        👑          |");
		System.out.println("|^^^^^^^^^^^^^^^^^^^|");
	}

	public void printLegend() {
		System.out.println("😈 = Combat");
		System.out.println("🔥 = Campfire");
		System.out.println("❓ = Random Event");
		System.out.println("✅ = Complete Encounter");
	}

	public void updateScreen() {
		Scanner input = new Scanner(System.in);
		boolean first = true;
		while (pIndex < map.length) {
			clearScreen();
			printBoss();
			for (int i = map.length - 2; i >= 0; i--) {
				printCurrent(i);
			}
			if (first) {
				System.out.println("\n\nYou are able to choose your path");
				System.out.println("1: left path");
				System.out.println("2: middle path");
				System.out.println("3: right path");
				int pick = -1; // Initialize with an invalid value

				while (pick != 1 && pick != 2 && pick != 3) {

					try {
						pick = input.nextInt();

						if (pick != 1 && pick != 2 && pick != 3) {
							System.out.println("Invalid input! Please choose 1, 2, or 3.");
						}

					} catch (InputMismatchException e) {
						System.out.println("Invalid input! Please enter a valid integer.");
						input.next(); // Clear the invalid input from the scanner buffer
					}
				}
				if (pick == 1) {
					pCol -= 2;
					clearScreen();
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();
				} else if (pick == 2) {
					clearScreen();
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();
				} else if (pick == 3) {
					pCol += 2;
					clearScreen();
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();

				}
				first = false;
			} else if (pIndex == map.length - 2) {
				clearScreen();
				map[map.length - 1][2].updateScreen();
			} else {
				System.out.println("\n\nYou are able to advance");
				boolean forwards = false;
				boolean left = false;
				boolean right = false;

				if (oneMove(pIndex, pCol, "l")) {
					System.out.println("1: left path");
					left = true;
				}
				if (oneMove(pIndex, pCol, "f")) {
					System.out.println("2: straight forwards path");
					forwards = true;
				}
				if (oneMove(pIndex, pCol, "r")) {
					System.out.println("3: right path");
					right = true;
				}

				pIndex++;
				int chosen = -1;
				
				while (true) {

					try {
						int choice = input.nextInt();
						if (choice == 1 && !left) {
							System.out.println("Invalid input! Please choose again.");
							continue;
						}

						if (choice == 2 && !forwards) {
							System.out.println("Invalid input! Please choose again.");
							continue;
						}

						if (choice == 3 && !right) {
							System.out.println("Invalid input! Please choose again.");
							continue;
						}

						if (choice == 1 || choice == 2 || choice == 3) {
							chosen = choice;
							break; // Exit loop if a valid choice is made
						} else {
							System.out.println("Invalid input!");
						}

					} catch (InputMismatchException e) {
						System.out.println("Invalid input! Please enter an integer.");
						input.next(); // Clear the invalid input
					}
				}

				if (chosen == 1) {
					clearScreen();
					pCol--;
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();
				} else if (chosen == 2) {
					clearScreen();
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();
				} else if (chosen == 3) {
					clearScreen();
					pCol++;
					map[pIndex][pCol].updateScreen();
					map[pIndex][pCol] = new CompleteEncounter();
				}
			}

		}
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void wait(int milliseconds) {
		long startTime, millis, current;
		millis = 0;

		startTime = System.currentTimeMillis();
		while (millis < startTime + milliseconds) {
			millis = System.currentTimeMillis();
		}

	}
}