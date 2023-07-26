package sportstournament.gui;

import javax.swing.JFrame;

import sportstournament.main.GameEnvironment;

/**
 * This abstract class implements a Screen which contains generic attributes and methods that all screens inherit and implement
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public abstract class Screen {
	
	/**
	 * The JFrame for the specific screen class that holds its contents
	 */
	protected JFrame window;
	
	/**
	 * The instance of Gui that can open and close windows
	 */
	protected final Gui gui;
	
	/**
	 * The instance of GameEnvironment that keeps track of the game
	 */
	protected final GameEnvironment game;
	
	/**
	 * The constructor for Screen that instantiates the GameEnvironment and Gui objects
	 * @param game The current state of the game
	 * @param gui The instance of Gui used to control windows
	 */
	public Screen(final GameEnvironment game, final Gui gui) {
		this.gui = gui;
		this.game = game;
	}
	
	/**
	 * Closes the window
	 */
	public void closeWindow() {
		window.dispose();
	}
	/**
	 * Opens the window
	 */
	public void open() {
		window.setVisible(true);
	}
	

}
