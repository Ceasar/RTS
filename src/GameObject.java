import java.awt.Graphics;


public interface GameObject {

	public void update();
	
	public void draw(Graphics g);
	
	public boolean isAlive();
	
	public int getHealth();
	
	public void setHealth(int h);
	
	public int getRadius();
	
	public int getX();
	
	public int getY();
	
}
