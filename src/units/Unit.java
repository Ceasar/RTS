package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import main.GameObject;
import main.Main;


public abstract class Unit extends GameObject{
	private double dx; private double dy;
	
	private double hitPoints; private double damage;
	private double speed; private double range;
	private long cooldown; private int sightRadius;
	
	private Point2D destination;
	
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
	
	public Set<Unit> getUnitsInRange(double range){
		HashSet<Unit> targets = new HashSet<Unit>();
		for (int i=0; i < Main.units.size(); i++) {
			Unit unit = Main.units.get(i);
			if (unit.getColor() != getColor() && distanceToObject(unit) < range)
				targets.add(unit);
		}
		return targets;
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

}
