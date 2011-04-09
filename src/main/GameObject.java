package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;


public abstract class GameObject {

	private Point2D location;
	private int radius;
	private Color color;
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public int getRadius(){
		return radius;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public Point2D getLocation() {
		return location;
	}
	
}
