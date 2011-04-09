package main;

import java.awt.Color;
import java.awt.Graphics;


public abstract class GameObject {

	private int x; private int y;
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
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}
