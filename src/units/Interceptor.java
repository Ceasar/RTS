package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

public class Interceptor extends Unit{

	static final double HIT_POINTS = 20;
	static final double DAMAGE = 2;
	static final double RANGE = 100;
	static final double SPEED = 2;
	static final int RADIUS = 10;
	static final long COOLDOWN_TIME = (long) 4.0;

	public Interceptor(Point2D loc, Color color){
		setLocation(loc); setColor(color);
		setDx(0); setDy(0);
		setHitPoints(HIT_POINTS);
		setDamage(DAMAGE);
		setRange(RANGE);
		setSpeed(SPEED);
		setRadius(RADIUS);
	}

	public void attack() {
		if (getCooldown() > 0)
			return;
		Unit target = getUnitsInRange(getRange()).iterator().next();
		target.setHitPoints(target.getHitPoints() - getDamage());
		setCooldown(getCooldown());
	}

	public void update() {
		if (getOrder() != null && getLocation().distance(getOrder()) > RADIUS){
			Point2D destination = getOrder();
			Point2D location = getLocation();
			double x = location.getX(); double y = location.getY();
			double angle = Math.atan2(destination.getY() - y, destination.getX() - x);
			setDx(getSpeed() * Math.cos(angle));
			setDy(getSpeed() * Math.sin(angle));
			Point2D newLoc = new Point((int)(x + getDx()), (int)(y + getDy()));
			setLocation(newLoc);
		}
		else{
			issueMoveOrder(null);
		}
		setCooldown((long) (getCooldown() - 0.1));
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		Point2D location = getLocation();
		double x = location.getX(); double y = location.getY();
		int radius = getRadius();
		g.fillOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);
		double range = getRange();
		g.drawOval((int)(x - range), (int)(y - range), (int)(range * 2), (int)(range * 2));
		Point2D destination = getOrder();
		if (destination != null)
			g.drawLine((int)x, (int)y, (int)destination.getX(), (int)destination.getY());
	}

}