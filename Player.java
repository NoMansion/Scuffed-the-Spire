public class Player {

	private int block; // block value
	private int hp; // health points
	private int cHp; // current hp
	private Deck playerDeck; // a deck object that holds a string of cards
	private int energy; // The starting amount of energy that player has
	private int strength;

	// if multiple different players are added;
	public Player(String name) {
		if (name.equals("Ironclad")) {
			playerDeck = new Deck();
			hp = 80;
			cHp = 80;
			block = 0;
			playerDeck = new Deck();
			energy = 3;
			strength = 0;
		}
	}

	public void lowBlock(int dmg) {
		block = block - dmg;
	}

	public void upBlock(int dmg) {
		block = block + dmg;
	}

	public void setBlock(int dmg) {
		block = dmg;
	}

	public void lowHp(int val) {
		int diff = block - val;
		// val is greater than block if negative
		if (diff < 0) {
			block = 0;
			cHp += diff;
		} else // val is less than block if positive.
		{
			block = 0;
			cHp = cHp;
		}
	}

	public void upHp(int dmg) {

		if (cHp + dmg >= hp) {
			cHp = hp;
		} else
			cHp = cHp + dmg;

	}

	public void maxHp(int val) {

		hp = hp + val;

	}

	public void lowStrength(int c) {
		strength -= c;
	}

	public void upStrength(int c) {
		strength += c;
	}

	public void isDead() {
		if (cHp <= 0) {
			System.out.println("Man you died.\nThat is rough buddy");
		}
	}

	public int getHp() {
		return hp;
	}

	public int getcHp() {
		return cHp;
	}

	public Deck getDeck() {
		return playerDeck;
	}

	public int getEnergy() {
		return energy;
	}

	public boolean dead() {
		return cHp <= 0;
	}

	public int getBlock() {
		return block;
	}

	public int getStrength() {
		return strength;
	}

	public void resetStrength() {
		strength = 0;
	}
}