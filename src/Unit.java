import java.awt.Color;
import java.awt.Graphics;


public class Unit implements GameObject{
	private int x; private int y;
	private int dx; private int dy;
	
	private int health; private int damage;
	private int speed; private int range;
	private int cooldown; private int sightRadius;
	
	private int radius;
	private Color color;
	
	public int getHealth(){
		System.out.println("fdf");
		return health;
	}
	public void setHealth(int h){
		health = h;
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
	
	public int getCooldown(){
		return cooldown;
	}
	public void setCooldown(int s){
		cooldown = s;
	}
	
	public boolean isAlive(){
		return (health <= 0);
	}

	public void attack(GameObject target){
		target.setHealth(target.getHealth()-1);
	}
	
	public void update() {
		x += dx; y += dy;
		
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
	}

	public int getRadius() {
		return radius;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

}
