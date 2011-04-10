package main.units.bases;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.Location;
import main.Map;
import main.Player;
import main.Unit;

public class BigPlanet extends Unit{
	
	static final double RANGE = 100;
	static final double SIZE = 30;

	public BigPlanet(Map map, Location loc, Player owner) {
		super(map, loc, -1, 0, 50, owner, 0, 0, RANGE, 0);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	public void update() {
		int most = 0;
		Set<Player> candidates = new HashSet<Player>();
		Set<Player> players = getMap().getPlayers();
		Iterator<Player> iter = players.iterator();
		while (iter.hasNext()){
			Player player = iter.next();
			Set<Unit> matching = getMap().getPlayerUnitsInRange(RANGE, getLocation(), player);
			matching.remove(this);
			int count = matching.size();
			if (count > most){
				most = count;
				candidates.clear();
				candidates.add(player);
			} else if (count == most){
				candidates.add(player);
			}
		}
		if (candidates.size() != 1) {
			setOwner(Map.NEUTRAL_PASSIVE);
		} else
			setOwner(candidates.iterator().next());
		
	}

	@Override
	public void draw(Graphics g) {
		double radius = RANGE;
		Location loc = getLocation();
		g.setColor(getOwner().getColor());
		g.drawOval((int)(loc.getX() - radius), (int)(loc.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		radius = SIZE;
		g.setColor(Color.blue);
		g.fillOval((int)(loc.getX() - radius), (int)(loc.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		
	}

}
