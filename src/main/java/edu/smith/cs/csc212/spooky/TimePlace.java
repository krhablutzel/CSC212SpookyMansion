package edu.smith.cs.csc212.spooky;

public class TimePlace extends Place{
	/**
	 * Store an additional description for the daytime.
	 */
	private String dayDescription;

	/**
	 * A TimePlace is a place with different day and night descriptions.
	 */
	protected TimePlace(String id, String nightDescription, String dayDescription) {
		super(id, nightDescription, false);
		this.dayDescription = dayDescription;
	}
	
	/**
	 * Print different descriptions during the day and the night
	 * @param whether it's night
	 */
	@Override
	public void printDescription(boolean isNight) {
		if (isNight) {
			System.out.println(this.getDescription());
		} else {
			System.out.println(this.dayDescription);
		}
		for (String item : this.getStuff()) {
			System.out.println("There is a " + item +  " here.");
		}
	}
	
	/**
	 * Create a TimePlace with an id and both descriptions.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param nightDescription - the nighttime description to show to the user
	 * @param dayDescription - the daytime description to show to the user
	 * @return the new Place object.
	 */
	public static Place timeCreate(String id, String nightDescription, String dayDescription) {
		return new TimePlace(id, nightDescription, dayDescription);
	}
}
