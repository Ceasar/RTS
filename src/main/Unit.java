package main;

import java.awt.geom.Point2D;


import main.GameObject;
import main.Player;


public abstract class Unit extends GameObject{
	private double dx; private double dy;
	
	private double hitPoints;
	private double damage;
	private double speed;
	private double range;
	private long cooldown;
	private int sightRadius;
	
	private Point2D destination;
	private Player owner;
	
	public Unit(){
		dx = 0; dy = 0;
	}
	
	public double getHitPoints(){
		return hitPoints;
	}
	
	public void setHitPoints(double h){
		hitPoints = h;
	}
	
	public void issueMoveOrder(Point2D destination){
		this.destination = destination;
	}
	
	public Point2D getOrder(){
		return destination;
	}
	
	public double getRange(){
		return range;
	}
	
	public void setRange(double r){
		range = r;
	}
	
	public double getDamage(){
		return damage;
	}
	
	public void setDamage(double d){
		damage = d;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public void setSpeed(double s){
		speed = s;
	}
	
	public long getCooldown(){
		return cooldown;
	}
	
	public void setCooldown(long cooldowntime){
		cooldown = cooldowntime;
	}
	
	public boolean isAlive(){
		return (hitPoints <= 0);
	}
	
	public double distanceToObject(GameObject other){
		Point2D location = getLocation();
		Point2D targetLoc = other.getLocation();
		double xDiff = targetLoc.getX() - location.getX(); double yDiff = targetLoc.getY() - location.getY();
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
	
	public abstract void attack();

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDx() {
		return dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getDy() {
		return dy;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}

}
