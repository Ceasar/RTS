package main;

/**
 * Used to replace Point with an object capable of double precision.
 * @author Ceasar
 *
 */

public class Location {
	
	private double x;
	private double y;
	
	public Location(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public double distance(Location other) {
		return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

}
