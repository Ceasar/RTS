package main;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Map {
	
	private Dimension size;
	private Set<Unit> units;
	private Set<Player> players;
	
	public Map(Dimension size){
		this.setSize(size);
		units = new HashSet<Unit>();
		players = new HashSet<Player>();
	}
	
	/**
	 * Gets all the units in range of a point.
	 * @param range
	 * @param point
	 * @return
	 */
	public Set<Unit> getAllUnitsInRange(double range, Location point){
		HashSet<Unit> matching = new HashSet<Unit>();
		Iterator<Unit> iter = units.iterator();
		while (iter.hasNext()){
			Unit unit = iter.next();
			if (point.distance(unit.getLocation()) < range)
				matching.add(unit);
		}
		return matching;
	}
	
	/**
	 * Gets all units in range owned by a particular player.
	 * @param range
	 * @param point
	 * @param player
	 * @return
	 */
	public Set<Unit> getPlayerUnitsInRange(double range, Location point, Player player){
		HashSet<Unit> matching = new HashSet<Unit>();
		Iterator<Unit> iter = units.iterator();
		while (iter.hasNext()){
			Unit unit = iter.next();
			if (point.distance(unit.getLocation()) < range && unit.getOwner() == player)
				matching.add(unit);
		}
		return matching;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Dimension getSize() {
		return size;
	}

	public void setUnits(Set<Unit> units) {
		this.units = units;
	}

	public Set<Unit> getUnits() {
		return units;
	}
	
	public void addUnit(Unit unit) {
		units.add(unit);
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Set<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
}
