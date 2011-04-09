import java.awt.Graphics;


public class Base implements GameObject{
	private int x; private int y;
	private int health;
	private int income;
	
	private int radius;
	
	public int getHealth(){
		return health;
	}
	public void setHealth(int h){
		health = h;
	}
	
	public int getIncome(){
		return income;
	}
	public void draw(){
		
	}

	public void update() {

	}

	public void draw(Graphics g) {

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

	public boolean isAlive() {
		return (health <= 0);
	}
}
