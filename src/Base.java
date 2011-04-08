
public class Base implements GameObject{
	
	private int health;
	private int income;
	
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
	public boolean dead(){
		return false;
	}
}
