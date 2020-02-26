package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	
	/**
	 * The player will now remember where they've been to.
	 */
	private Set<String> visited;
	
	/**
	 * The items the player has collected.
	 */
	private List<String> stuff;
	
	/**
	 * The player's time tracker.
	 */
	private GameTime timeTracker;

	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<String>();
		this.stuff = new ArrayList<String>();
		this.timeTracker = new GameTime();
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Call this method when the player moves to a new place.
	 * Records the old place in the player's memory, and moves time forward.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.visited.add(this.getPlace());
		this.timeTracker.increaseHour();
		this.place = place;

	}
	
	/**
	 * Check whether player has been here before.
	 * @return whether the player has been here before.
	 */
	public boolean hasBeenHereBefore() {
		return this.visited.contains(this.getPlace());
	}
	
	/**
	 * Print out the player's stuff.
	 */
	public void printStuff() {
		if (stuff.size() < 1) {
			// Special message if have nothing
			System.out.println("You have nothing.");
		} else {
			// Print one line per item
			for (String thing : stuff) {
				System.out.println("You have a " + thing +  ".");
			}
		}
	}
	
	/**
	 * Add to player's stuff.
	 * @param found item
	 */
	public void addStuff(List<String> found) {
		stuff.addAll(found);
	}
	
	/**
	 * Get a list of the player's stuff.
	 * @return list of player's stuff
	 */
	public List<String> getStuff() {
		return this.stuff;
	}
	
	/**
	 * Get the player's current time 
	 * @return Current hour
	 */
	public int getHour() {
		return this.timeTracker.getHour();
	}
	
	/**
	 * Resting moves the hours forward by 2
	 */
	public void rest() {
		this.timeTracker.increaseHour();
		this.timeTracker.increaseHour();
	}
	
	/**
	 * Get the player's total time spent in the game
	 * @return Current hour
	 */
	public int getTotalTime() {
		return this.timeTracker.getTotalTime();
	}
	
	/**
	 * Check whether it's night or day.
	 * @return true if it's night, false if it's day
	 */
	public boolean isNightTime() {
		return this.timeTracker.isNightTime();
	}

}
