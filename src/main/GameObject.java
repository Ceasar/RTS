package main;

import java.awt.Graphics;


public abstract class GameObject {

	private Map map;
	private Location location;
	private double collisionSize;
	
	public GameObject(Map map, Location loc, double collisionSize) {
		this.setMap(map);
		this.location = loc;
		this.collisionSize = collisionSize;
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
	
}
