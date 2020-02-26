package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit{
	/**
	 * Is the exit still secret?
	 */
	private boolean secret;
	
	/**
	 * Create a new Secret Exit.
	 * @param target - where it goes
	 * @param description - how it looks.
	 * @param found - whether the exit is found.
	 */
	
	public SecretExit(String target, String description) {
		super(target, description);
		secret = true;
	}
	
	/**
	 * Return whether the exit is still secret.
	 * @return true if it's secret, false if it's found.
	 */
	@Override
	public boolean isSecret() {
		return secret;
	}
	
	/**
	 * Find the exit when the player searches in its room
	 */
	@Override
	public void search() {
		secret = false;
	}
	
}
