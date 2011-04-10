package main;

import main.GameObject;
import main.Player;


public abstract class Unit extends GameObject{
	private double dx; private double dy;
	
	//Current stats
	private double damage;
	private double speed;
	private double range;
	private long cooldown;
	
	private int value;
	private boolean base;
	
	private Location destination;
	private Player owner;
	
	public Unit(Map map, Location loc, double hitpoints, double collisionSize, double selectionSize, Player owner, double damage, double speed, double range, long cooldownTime){
		super(map, loc, hitpoints, hitpoints, collisionSize, selectionSize);
		this.owner = owner;
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

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	public boolean isBase() {
		return base;
	}

}
