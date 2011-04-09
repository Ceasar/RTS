package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;


public abstract class GameObject {

	private Point2D location;
	private double collisionSize;
	
	public abstract void update();
	
	public abstract void draw(Graphics g);

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setCollisionSize(double collisionSize) {
		this.collisionSize = collisionSize;
	}

	public double getCollisionSize() {
		return collisionSize;
	}
	
}
