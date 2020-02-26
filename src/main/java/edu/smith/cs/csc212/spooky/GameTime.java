package edu.smith.cs.csc212.spooky;

/**
 * This represents the time elapsed in our text adventure.
 * @author khablutzel
 *
 */
public class GameTime {
	/*
	 * Track current hour of game time.
	 */
	private int hour = 0;
	
	/*
	 * Track total hours of game time.
	 */
	private int totalHours = 0;
	
	/**
	 * Constructor for GameTime. The GameTime the tracks time for the player during the game.
	 */
	public GameTime() {
		
	}
	
	/**
	 * Get the current hour.
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Get the total hours spent in the game.
	 * @return totalHours
	 */
	public int getTotalTime() {
		return totalHours;
	}
	
	/**
	 * Move the hour forward (modulo 24).
	 * Move the total hours forward.
	 */
	public void increaseHour() {
		totalHours++;
		hour++;
		if (hour > 23) {
			hour -= 24;
		}
	}
	
	/**
	 * Report whether it is day or night. Night is [6 PM, 6 AM).
	 * @return true if night, false if day
	 */
	public boolean isNightTime() {
		if (hour >= 18 || hour < 6) {
			return true;
		} else {
			return false;
		}
	}

}
