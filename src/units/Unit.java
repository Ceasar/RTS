package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import main.GameObject;
import main.Main;


public abstract class Unit extends GameObject{
	private int dx; private int dy;
	
	private int hitPoints; private int damage;
	private int speed;
	private int range;
	private long cooldown; private int sightRadius;
	
	private Point2D destination;
	
	public int getHitPoints(){
		return hitPoints;
	}
	
	public void setHitPoints(int h){
		hitPoints = h;
	}
	
	public void setDestination(Point2D destination){
		this.destination = destination;
	}
	
	public Point2D getDestination(){
		return destination;
	}
	
	public int getRange(){
		return range;
	}
	
	public void setRange(int r){
		range = r;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setDamage(int d){
		damage = d;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int s){
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
		double xDiff = other.getX() - getX(); double yDiff = other.getY() - getY();
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}

	public Set<Unit> getTargets(){
		HashSet<Unit> targets = new HashSet<Unit>();
		for (int i=0; i < Main.units.size(); i++) {
			Unit unit = Main.units.get(i);
			if (unit.getColor() != getColor() && distanceToObject(unit) < range)
				targets.add(unit);
		}
		return targets;
	}
	
	public abstract void attack();

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDx() {
		return dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDy() {
		return dy;
	}

}
