public class Card {
	private int damage;
	private int block;
	private int draw;
	private int strength;
	private String desc;
	private String name;
	private boolean exhausts;
	private int nrgGain;
	private int nrgCost;

	// Constructor
	public Card(String name, String desc, int damage, int block, int draw, int strength, int nrgGain, int nrgCost,
			boolean exhausts) {
		this.name = name;
		this.desc = desc;
		this.damage = damage;
		this.block = block;
		this.draw = draw;
		this.strength = strength;
		this.nrgGain = nrgGain;
		this.nrgCost = nrgCost;
		this.exhausts = exhausts;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public int getDamage() {
		return damage;
	}

	public int getBlock() {
		return block;
	}

	public int getDraw() {
		return draw;
	}

	public int getStrength() {
		return strength;
	}

	public int getNrgGain() {
		return nrgGain;
	}

	public int getNrgCost() {
		return nrgCost;
	}

	public boolean isExhausts() {
		return exhausts;
	}

	// toString method
	@Override
	public String toString() {
		return "Costs " + getNrgCost() + ": " + getName() + ": " + getDesc();
	}
}
