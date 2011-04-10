package main.units.bases;

import java.awt.Graphics;

import main.Location;
import main.Map;
import main.Player;
import main.Unit;

public class Shipyard extends Unit {
	
	static final double HIT_POINTS = 200;
	static final double DAMAGE = 5;
	static final double RANGE = 100;
	static final double SPEED = 0;
	static final double COLLISION_SIZE = 100;
	static final double SELECTION_SIZE = 120;
	static final long COOLDOWN_TIME = (long) 4.0;

	public Shipyard(Map map, Location loc, Player owner) {
		super(map, loc, HIT_POINTS, COLLISION_SIZE, SELECTION_SIZE, owner, DAMAGE, SPEED, RANGE,COOLDOWN_TIME);
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
		g.setColor(getOwner().getColor());
		g.fillOval((int)(loc.getX() - radius), (int)(loc.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		
	}

}
