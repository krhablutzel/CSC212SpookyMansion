package edu.smith.cs.csc212.spooky;

public class LockedExit extends Exit{
	/**
	 * The exit knows what key it needs to open.
	 */
	private String key; 
	
	/**
	 * Create a new LockedExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.key = key;
	}
	
	/**
	 * Report what key the locked exit needs
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if they have the key, false if they need something special.
	 */
	@Override
	public boolean canOpen(Player player) {
		if (player.getStuff().contains(this.getKey())) {
			return true;
		} else {
			return false;
		}
	}

}
