import java.util.Scanner;
import java.util.ArrayList;

public class RandomEvent extends Encounter {

    private ArrayList<Card> pDeck = new ArrayList<Card>();
    private Player user;
    private int rNum;
    private CardList cardPool;
    private Scanner input = new Scanner(System.in);

    // At the start of the program in main, initialize every event
    public RandomEvent(Player user, CardList cardPool) {
        this.user = user;
        this.pDeck = user.getDeck().getPlayerDeck();
        rNum = (int) (Math.random() * 7); // random 0 through 6
        this.cardPool = cardPool;
    }

    // Takes a random number from 0-6
    public void updateScreen() {
        rNum = (int) (Math.random() * 7); // random 0 through 6
        int num = 0;
        try {
            switch (rNum) {
                case 0:
                    handleAPClassEvent();
                    break;
                case 1:
                    handleSnacksEvent();
                    break;
                case 2:
                    handleFallEvent();
                    break;
                case 3:
                    handleSpinWheelEvent();
                    break;
                case 4:
                    handleCombatEvent();
                    break;
                case 5:
                    handlePortalEvent();
                    break;
                case 6:
                    handleCampfireEvent();
                    break;
                default:
                    System.out.println("Unexpected random event number: " + rNum);
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void handleAPClassEvent() {
        clearScreen();
        wait(1000);
        System.out.println("You stumble upon an AP class. You consider taking a seat to try and learn, or you could just leave.");
        wait(1000);
        System.out.println("1. Take a seat. (Card reward)");
        System.out.println("2. Leave and keep sanity. (Gain 10 health)");

        int num = getValidInput(1, 2);

        if (num == 1) {
            cardPool.cardReward(user);
        } else if (num == 2) {
            user.upHp(10);
        }
    }

    private void handleSnacksEvent() {
        clearScreen();
        wait(1000);
        System.out.println("In front of you are 2 snacks: a donut and an apple. What would you like to do?");
        wait(1000);
        System.out.println("Your health: " + user.getcHp() + " / " + user.getHp() + " HP");
        wait(1000);
        System.out.println("1. Eat the apple. (Gain 20 health)");
        System.out.println("2. Eat the donut (Gain 5 max hp)");

        int num = getValidInput(1, 2);

        if (num == 1) {
            user.upHp(20);
        } else if (num == 2) {
            user.maxHp(5);
        }
    }

    private void handleFallEvent() {
        clearScreen();
        wait(1000);
        System.out.println("While walking you stumble and fall. What would you like to do?");
        wait(1000);
        System.out.println("1. Find a card on the ground. (Card reward, take 15 damage.)");
        System.out.println("2. Get up and bandage yourself. (Nothing happens)");

        int num = getValidInput(1, 2);

        if (num == 1) {
            user.lowHp(15);
            cardPool.cardReward(user);
        }
    }

    private void handleSpinWheelEvent() {
        clearScreen();
        wait(1000);
        System.out.println("You come across a nice man who begs you to spin his wheel. How could you not?");
        wait(1000);
        System.out.println("1. Spin it! (25% chance each of: +20 hp, +5 max hp, card reward, take 15 damage)");

        int num = getValidInput(1, 1); // Only one choice to spin

        int number = (int) (Math.random() * 4);

        switch (number) {
            case 0:
                wait(1000);
                System.out.println("You gained 20 hp! Wahoo!");
                break;
            case 1:
                wait(1000);
                System.out.println("You gained 5 max hp!");
                break;
            case 2:
                wait(1000);
                System.out.println("Card reward! Yay!");
                break;
            case 3:
                wait(1000);
                System.out.println("You took 15 damage!");
                user.lowHp(15);
                break;
            default:
                System.out.println("Unexpected result from spin wheel.");
                break;
        }
        wait(1000);
        getValidInput(1, 1); // Wait for user to continue
    }

    private void handleCombatEvent() {
        clearScreen();
        Combat itActuallyWorks = new Combat(user, cardPool);
        wait(1000);
        System.out.println("An enemy appears from your random event!");
        wait(3000);
        itActuallyWorks.updateScreen();
    }

    private void handlePortalEvent() {
        clearScreen();
        wait(1000);
        System.out.println("You find a mysterious portal. Would you like to enter?");
        wait(1000);
        System.out.println("1. Enter the portal, travel straight to boss.");
        System.out.println("2. Leave. (Nothing happens)");

        int num = getValidInput(1, 2);

        if (num == 1) {
            clearScreen();
            Combat bossTime = new Combat(user, cardPool, 69);
            bossTime.updateScreen();
        }
    }

    private void handleCampfireEvent() {
        clearScreen();
        System.out.println("The random event is a campfire!");
        Campfire test1 = new Campfire(user, cardPool);
        test1.updateScreen();
    }

    private int getValidInput(int min, int max) {
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                num = Integer.parseInt(input.nextLine());
                if (num >= min && num <= max) {
                    valid = true;
                } else {
                    System.out.println("Invalid number! Please pick a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return num;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String toString() {
        return "❓";
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
