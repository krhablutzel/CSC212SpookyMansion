package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @return when - the time it took the player to finish.
	 */
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart());
				
		System.out.println("Type help if you don't know what you're doing!");

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Get player location
			Place here = game.getPlace(player.getPlace());
			
			// Print the description of where you are.
			System.out.println();
			System.out.println("... --- ...");
			if (player.hasBeenHereBefore()) {
				System.out.println("This place feels familiar...");
			}
			here.printDescription(player.isNightTime());
			
			// Print hour
			System.out.println("The hour is " + player.getHour() + ":00.");
			
			// Game over after print description of terminal room!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();
			
			// Cat easter egg
			if (action.equals("meow")) {
				System.out.println("You are now a cat. That will make life difficult.");
				continue;
			}
			
			// Help menu
			if (action.equals("help")) {
				System.out.println("Select adventure options by typing the corresponding number + enter.");
				System.out.println("Type 'take' to collect items at a location or 'stuff' to see what stuff you have collected.");
				System.out.println("Type 'search' to search rooms for secret exits.");
				System.out.println("Explore different rooms until you find the escape!");
				System.out.println("Type 'quit' or 'q' or 'escape' + enter to quit.");
				continue;
			}
			
			// Quit game
			if (action.equals("quit") || action.equals("q") || action.equals("escape")) {
				if (input.confirm("Are you sure you want to quit?")) {
					// quit!
					break;
				} else {
					// go to the top of the game loop!
					continue;
				}
			}
			
			// Search the room
			if (action.equals("search")) {
				System.out.println("You search the room for additional exits.");
				here.search();
				continue;
			}
			
			// Show player's stuff
			if (action.equals("stuff")) {
				player.printStuff();
				continue;
			}
			
			// Take stuff at location
			if (action.equals("take")) {
				List<String> newThings = here.getStuff();
				// Print what got taken
				for (String thing : newThings) {
					System.out.println("You take the " + thing + ".");
				}
				player.addStuff(newThings);
				here.clearStuff();
				continue;
			}
			
			// Rest action advances game 2 hours
			if (action.equals("rest")) {
				player.rest();
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			if (destination.canOpen(player)) {
				player.moveTo(destination.getTarget());
			} else {
				System.out.println("It's locked. Maybe you need a key?");
			}
		}

		// End of game
		System.out.println("\n\nYou spent " + player.getTotalTime() + " hours trapped in the game.");
		return player.getPlace();
	}

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
	}

}
