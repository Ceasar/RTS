package main;

public abstract class Base extends Unit{

	public Base(Location loc, double collisionSize, Player owner, double hitpoints, double damage, double speed, double range, long cooldownTime) {
		super(loc, collisionSize, owner, hitpoints, damage, speed, range, cooldownTime);
	}

	private int income;

	public void setIncome(int income) {
		this.income = income;
	}

	public int getIncome() {
		return income;
	}

}
