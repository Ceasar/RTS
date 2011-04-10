package main;

import java.awt.Color;
import java.awt.Graphics;


public abstract class GameObject {
	
	private static final int LIFEBAR_HEIGHT = 5;

	private Map map;
	private Location location;
	private double maxHitPoints;
	private double hitpoints;
	private double collisionSize;
	private boolean selected;
	private double selectionSize;
	
	public GameObject(Map map, Location loc, double maxHitPoints, double hitpoints, double collisionSize, double selectionSize) {
		this.setMap(map);
		this.location = loc;
		this.maxHitPoints = maxHitPoints;
		this.hitpoints = hitpoints;
		this.collisionSize = collisionSize;
		this.selectionSize = selectionSize;
		selected = false;
	}
	
	/**
	 * Draws a circle around the unit indicating it is selected.
	 * @param g
	 */
	public void drawSelectionCircle(Graphics g) {
		if (!selected)
			return;
		Location location = getLocation();
		double x = location.getX(); double y = location.getY();
		Color color = Color.green;
		g.setColor(color);
		int diameter = (int)(selectionSize * 2);
		g.drawOval((int)(x - selectionSize), (int)(y - selectionSize), diameter, diameter);
	}
	
	/**
	 * Draws a life bar above the unit indicating the percentage hitpoints.
	 * @param g
	 */
	public void drawLifebar(Graphics g) {
		Location location = getLocation();
		double x = location.getX(); double y = location.getY();
		double life = getPercentLife();
		Color color = new Color((int) (255 * (1 - life)), (int) (255 * life), 0);
		g.setColor(color);
		g.fillRect((int)(x - selectionSize), (int)(y - selectionSize - LIFEBAR_HEIGHT - 1), (int)(selectionSize * 2 * life), LIFEBAR_HEIGHT);
	}
	
	/**
	 * Returns percentage life.
	 * @return percentage life between 0.0 and 1.0
	 */
	public double getPercentLife(){
		return hitpoints / maxHitPoints;
	}
	
	public boolean isAlive(){
		return (hitpoints <= 0);
	}
	
	public double getHitPoints(){
		return hitpoints;
	}
	
	public void setHitPoints(double h){
		hitpoints = h;
	}

	public abstract void update();
	
	public abstract void draw(Graphics g);

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setCollisionSize(double collisionSize) {
		this.collisionSize = collisionSize;
	}

	public double getCollisionSize() {
		return collisionSize;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void deselect() {
		this.selected = false;
	}

	public boolean isSelected() {
		return selected;
	}
	
}
