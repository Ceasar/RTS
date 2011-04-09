package main.units.bases;

import java.awt.Graphics;

import main.Base;
import main.Location;
import main.Player;

public class Shipyard extends Base {
	
	static final double HIT_POINTS = 200;
	static final double DAMAGE = 5;
	static final double RANGE = 100;
	static final double SPEED = 0;
	static final double COLLISION_SIZE = 100;
	static final long COOLDOWN_TIME = (long) 4.0;

	public Shipyard(Location loc, Player owner) {
		super(loc, COLLISION_SIZE, owner, HIT_POINTS, DAMAGE, SPEED, RANGE,COOLDOWN_TIME);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		double radius = getCollisionSize();
		Location loc = getLocation();
		g.fillOval((int)(loc.getX() - radius), (int)(loc.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		
	}

}
