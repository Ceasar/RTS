package main.units.ships;

import java.awt.Graphics;

import main.Location;
import main.Map;
import main.Player;
import main.Unit;

public class Interceptor extends Unit{

	static final double DEFAULT_HIT_POINTS = 20;
	static final double DAMAGE = 2;
	static final double RANGE = 100;
	static final double SPEED = 2;
	static final double COLLISION_SIZE = 10;
	static final double SELECTION_SIZE = 12;
	static final long COOLDOWN_TIME = (long) 4.0;

	public Interceptor(Map map, Location loc, Player owner){
		super(map, loc, DEFAULT_HIT_POINTS, COLLISION_SIZE, SELECTION_SIZE, owner, DAMAGE, SPEED, RANGE, COOLDOWN_TIME);
	}

	/**
	 * Picks a random unit within range and damages it.
	 */
	public void attack() {
//		if (getCooldown() > 0)
//			return;
//		Unit target = getMap().getEnemiesInRange(getRange(), getLocation(), getOwner()).iterator().next();
//		target.setHitPoints(target.getHitPoints() - getDamage());
//		setCooldown(getCooldown());
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		drawSelectionCircle(g);
		drawLifebar(g);
		g.setColor(getOwner().getColor());
		Location location = getLocation();
		double x = location.getX(); double y = location.getY();
		double radius = getCollisionSize();
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(radius * 2), (int) (radius * 2));
		double range = getRange();
		g.drawOval((int)(x - range), (int)(y - range), (int)(range * 2), (int)(range * 2));
		Location destination = getOrder();
		if (destination != null)
			g.drawLine((int)x, (int)y, (int)destination.getX(), (int)destination.getY());
	}

}