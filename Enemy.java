public class Enemy {

	private int monNum; // index val that controls what the enemy is
	private String name;
	private int hp; // total health
	private int cHp; // holds the current hp
	private int block; // block the enemy has at any time || must be reset after each turn
	private int strength; // strength carries over || add to any attack
	private double intent; // controls what the enemy will do.

	// index will control the Monster Number
	// which will control what the monster is
	// Player other will be used to control player stats
	public Enemy(int index) {
		monNum = index;

		// cultist
		if (index == 0) {
			name = "Cultist";
			hp = 48;
			cHp = 48;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 1) // jaw worm
		{
			name = "Jaw Worm";
			hp = 40;
			cHp = 40;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 2) // louse
		{
			name = "Louse";
			hp = 10;
			cHp = 10;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 3) {
			name = "Fungi Beast";
			hp = 28;
			cHp = 28;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 4) {
			name = "Blue Slaver";
			hp = 50;
			cHp = 50;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 5) {
			name = "Red Slaver";
			hp = 46;
			cHp = 46;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 6) {
			name = "Gremlin Nob";
			hp = 86;
			cHp = 86;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 7) {
			name = "Lagavulin";
			hp = 60;
			cHp = 60;
			block = 0;
			strength = 0;
			intent = 0;
		} else if (index == 69) {
			name = "Hexaghost";
			hp = 250;
			cHp = 250;
			block = 0;
			strength = 0;
			intent = 0;
		}

	}

//all of the getter methods    

	public int getMonNum() {
		return this.monNum;
	}

	public String getName() {
		return this.name;
	}

	public int getCurrentH() {
		return this.cHp;
	}

	public int getH() {
		return this.hp;
	}

	public int getBlock() {
		return this.block;
	}

	public int getStrength() {
		return this.strength;
	}

//all of the setter methods 

	// can be up / down for all stats (except hp || just use negative / positive
	// nums to determine)
	public void lowHp(int val) {
		int diff = block - val;
		// val is greater than block if negative
		if (diff < 0) {
			block = 0;
			cHp += diff;
		} else // val is less than block if positive.
		{
			block = diff;
			cHp = cHp;
		}
	}

	// adds the val to current hp unless it would go over total health
	// in that case, is equal to total hp;
	// otherwise add like normal
	public void highHp(int val) {
		if (cHp + val > hp) {
			cHp = hp;
		} else
			cHp = cHp + val;

	}

	public void setBlock(int blk) {
		block = blk;
	}

	public void setStrength(int str) {
		strength = strength + str;
	}

	// getMove will actually do the attack
	// randomized through math.random
	// Mon Num controls the monster's moveset aligned with the constructor
	public void move(Player user) {
		// cultist moveset
		if (monNum == 0) {
			// intent = Math.random()
			if (intent < 0.15) {
				// System.out.println("Ritual: Gains 3 Strength");
				setStrength(3);
			} else if (intent >= 0.15) {
				// System.out.println("Dark Strike: Attacking For " +( 6 + getStrength() )+ "
				// Damage");
				user.lowHp(6 + getStrength());
			}
		} else if (monNum == 1) // Jaw Worm moveset
		{

			if (intent < 0.25) {
				user.lowHp(11 + getStrength());
			} else if (intent < 0.50) {
				setBlock(5);
				user.lowHp(7 + getStrength());
			} else if (intent >= 0.50) {
				setBlock(6);
				setStrength(3);
			}
		} else if (monNum == 2) // louse moveset
		{

			if (intent < .25) {
				setStrength(3);

			} else if (intent >= .25) {
				user.lowHp(5 + getStrength());
			}
		} else if (monNum == 3) // Fungi Beast
		{
			if (intent < .6) {
				user.lowHp(6 + getStrength());
			} else if (intent > .6) {
				setStrength(3);
			}
		} else if (monNum == 4) // Blue Slaver
		{
			if (intent < .4) {
				setBlock(12);
			} else if (intent > .4) {
				user.lowHp(12 + getStrength());
			}
		} else if (monNum == 5) // Red Slaver
		{
			if (intent < .4) {
				setBlock(12);
			} else if (intent > .4) {
				user.lowHp(12 + getStrength());
			}
		} else if (monNum == 6) // Gremlin Nob
		{
			if (intent < .33) {
				setStrength(2);
			} else if (intent < .66) {
				user.lowHp(6 + getStrength());
			} else if (intent < 1) {
				user.lowHp(14 + getStrength());
			}
		} else if (monNum == 7) // lagavulin
		{
			if (intent < .8) {
				user.lowHp(18 + getStrength());

			} else if (intent < 1) {
				user.lowStrength(1);
			}
		} else if (monNum == 69) // Hexaghost
		{
			if (intent < .05) {
				user.lowHp(2 * (6 + getStrength()));
			} else if (intent < .15) {
				setStrength(2);
				setBlock(12);
			} else if (intent < .4) {
				user.lowHp(((user.getcHp() / 12) + 1) * (6 + getStrength()));
			} else if (intent > .4) {
				user.lowHp(6 + getStrength());
			}
		}
	}

	// prints out what the enemy will do, without doing it
	// also randomizes the intent
	public void getMove(Player user) {
		// cultist moveset
		if (monNum == 0) {

			if (intent < 0.15) {
				System.out.println(getName() + " plans to buff!");
				System.out.println("Ritual: Gains 3 Strength");
			} else if (intent >= 0.15) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Dark Strike: Attacking For " + ((6 + getStrength())) + " Damage");
			}
		} else if (monNum == 1) // Jaw Worm moveset
		{

			if (intent < 0.25) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Chomp: Attacking For " + ((11 + getStrength())) + " Damage");

			} else if (intent < 0.50) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Thrash: Attacking For " + ((7 + getStrength())) + " Damage, Gains 5 Block");

			} else if (intent >= 0.50) {
				System.out.println(getName() + " plans to buff and block!");
				System.out.println("Bellow: Gains 3 Strength, Gains 6 Block");

			}
		} else if (monNum == 2) // louse moveset
		{

			if (intent < .25) {
				System.out.println(getName() + " plans to buff!");
				System.out.println("Grow: Gains 3 Strength");

			} else if (intent >= .25) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Bite: Attacking For " + ((5 + getStrength())) + " Damage");

			}
		} else if (monNum == 3) // fungi beast
		{

			if (intent < .60) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Bite: Attacking For " + ((6 + getStrength())) + " Damage");
			} else if (intent > .60) {
				System.out.println(getName() + " plans to buff!");
				System.out.println("Grow: Gains 3 Strength");
			}
		} else if (monNum == 4) // Blue Slaver
		{

			if (intent < .40) {
				System.out.println(getName() + " plans to block!");
				System.out.println("Rake: Gains 12 Block");
			} else if (intent > .40) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Stab: Attacking For " + ((12 + getStrength())) + " Damage");
			}
		} else if (monNum == 5) // Red Slaver
		{

			if (intent < .40) {
				System.out.println(getName() + " plans to block!");
				System.out.println("Rake: Gains 12 Block");
			} else if (intent > .40) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Stab: Attacking For " + ((12 + getStrength())) + " Damage");
			}
		} else if (monNum == 6) // Gremlin Nob
		{

			if (intent < .33) {
				System.out.println(getName() + " plans to buff!");
				System.out.println("Bellow: Gains 2 Strength");
			} else if (intent < .66) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Skull Bash: Attacking For " + ((6 + getStrength())) + " Damage");
			} else if (intent < 1) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Rush: Attacking For " + ((14 + getStrength())) + " Damage");
			}
		} else if (monNum == 7) // Lagavulin
		{

			if (intent < .8) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Thrash: Attacking For " + (18 + getStrength()) + " Damage");
			} else if (intent < 1) {
				System.out.println(getName() + " plans to debuff!");
				System.out.println("Siphon Soul: Taking Away 1 Strength");
			}
		} else if (monNum == 69) // Hexaghost
		{

			if (intent < .05) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Inferno: Attacking For 2 x " + (6 + getStrength()) + " Damage");
			} else if (intent < .15) {
				System.out.println(getName() + " plans to buff!");
				System.out.println("Inflame: Gains 2 Strength and 12 Block");
			} else if (intent < .4) {

				System.out.println(getName() + " plans to attack!");
				System.out.println("Divider: Attacking For " + ((user.getcHp() / 12 + 1)) + " x " + (6 + getStrength())
						+ " Damage");
			} else if (intent > .4) {
				System.out.println(getName() + " plans to attack!");
				System.out.println("Sear: Attacking For " + (6 + getStrength()) + " Damage");
			}
		}
	}

	public void genMove() {
		intent = Math.random();
	}

	public String returnHealth() {
		return "\n" + getName() + "\n" + cHp + " / " + hp + " HP";
	}

	public boolean dead() {
		return cHp <= 0;
	}

	public String toString() {
		return getName() + " has " + getCurrentH() + " HP and " + getBlock() + " block";
	}
}