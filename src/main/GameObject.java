package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;


public abstract class GameObject {

	private Location location;
	private double collisionSize;
	
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
	
}
