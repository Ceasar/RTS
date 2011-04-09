package units;

import java.awt.Color;
import java.awt.Graphics;

public class Interceptor extends Unit{
	
	static final long cooldownTime = (long) 4.0;

	public Interceptor(int x, int y, Color color){
		setX(x); setY(y); setColor(color);
		setHitPoints(20);
		setDamage(2);
		setRange(100);
		setSpeed(3);
		setRadius(10);
	}
	
	public void attack() {
		if (getCooldown() > 0)
			return;
		Unit target = getTargets().iterator().next();
		target.setHitPoints(target.getHitPoints() - getDamage());
		setCooldown(cooldownTime);
	}

	public void update() {
		setX(getX() + getDx()); setY(getY() + getDy());
		setCooldown((long) (getCooldown() - 0.1));
	}

	public void draw(Graphics g) {
		g.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
	}

}
