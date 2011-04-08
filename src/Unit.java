
public class Unit implements GameObject{
	private int health;
	private int range;
	private int strength;
	private int speed;
	private int cooldown;
	
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
	
	public int getStrength(){
		return strength;
	}
	
	public void setStrength(int s){
		strength = s;
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
	
	
	public boolean dead(){
		if(health <= 0)
			return true;
		else
			return false;
	}
	
	public void draw(){
		
	}

	public void attack(GameObject target){
		target.setHealth(target.getHealth()-1);
	}
	
	public void move(){
		
	}
	

}
