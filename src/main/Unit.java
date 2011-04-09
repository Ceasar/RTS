package main;


import java.awt.Color;
import java.awt.Graphics;

import main.GameObject;
import main.Player;


public abstract class Unit extends GameObject{
	private double dx; private double dy;
	
	private double maxHitPoints;
	
	private double hitpoints;
	private double damage;
	private double speed;
	private double range;
	private long cooldown;
	
	private Location destination;
	private Player owner;
	
	public Unit(Location loc, double collisionSize, Player owner, double hitpoints, double damage, double speed, double range, long cooldownTime){
		super(loc, collisionSize);
		this.owner = owner;
		this.maxHitPoints = this.hitpoints = hitpoints;
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.cooldown = cooldownTime;
		
		dx = 0; dy = 0;
	}
	
	public void update() {
		//Moves the unit closer to its target destination.
		if (getOrder() != null && getLocation().distance(getOrder()) > getCollisionSize()){
			Location location = getLocation();
			double x = location.getX(); double y = location.getY();
			double angle = Math.atan2(destination.getY() - y, destination.getX() - x);
			double dx = speed * Math.cos(angle); double dy = speed * Math.sin(angle);
			setDx(dx); setDy(dy);
			location.setXY(x + dx, y + dy);
		}
		else{
			issueMoveOrder(null);
		}
		setCooldown((long) (getCooldown() - 0.1));
	}
	
	public void drawLifebar(Graphics g) {
		Location location = getLocation();
		double x = location.getX(); double y = location.getY();
		double life = getPercentLife();
		Color color = new Color((int) (255 * (1 - life)), (int) (255 * life), 0);
		g.setColor(color);
		g.fillRect((int)(x - getCollisionSize()), (int)(y - getCollisionSize() - 6), (int)(getCollisionSize() * 2 * life), 5);
	}
	
	/**
	 * Returns percentage life [0,1]
	 * @return percentage life
	 */
	public double getPercentLife(){
		return hitpoints / maxHitPoints;
	}
	
	public double getHitPoints(){
		return hitpoints;
	}
	
	public void setHitPoints(double h){
		hitpoints = h;
	}
	
	public void issueMoveOrder(Location destination){
		this.destination = destination;
	}
	
	public Location getOrder(){
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
		return (hitpoints <= 0);
	}
	
	public double distanceToObject(GameObject other){
		Location location = getLocation();
		Location targetLoc = other.getLocation();
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
