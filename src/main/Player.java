package main;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
	
	enum Controller {PLAYER, COMPUTER}
	
	private int number;
	private int funds;
	private Color color;
	private Set<Player> allies;
	private String name;
	private Controller controller;
	
	public Player(int number, Color color, String name, Controller controller){
		this.setNumber(number);
		this.setColor(color);
		this.setController(controller);
		this.name = name;
		funds = 0;
		allies = new HashSet<Player>();
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setFunds(int funds) {
		this.funds = funds;
	}

	public int getFunds() {
		return funds;
	}
	
	public void addFunds(int funds) {
		this.funds += funds;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setAllies(Set<Player> allies) {
		this.allies = allies;
	}
	
	public void addAlly(Player ally) {
		this.allies.add(ally);
	}

	public Set<Player> getAllies() {
		return allies;
	}
	
	public String toString() {
		return name;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

}
