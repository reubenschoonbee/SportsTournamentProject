package sportstournament;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;

import sportstournament.gui.Gui;
import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;
import sportstournament.ui.CommandLine;

/**
 * This class runs the application.
 * The application is either run through the command line or the graphical user interface.
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class Main {
	/**
	 * The main method that launches the application
	 * 
	 * @param args Used to distinguish between running the command line application or graphical
	 */
	public static void main(String[] args) {
		Random rng = new Random();
		final ArrayList<Athlete> draft = new ArrayList<Athlete>();
		
		while (draft.size() <= 10) {
			int offenceStat = rng.nextInt(16) + 50;
			int defenceStat = rng.nextInt(16) + 50;
			draft.add(Athlete.randomAthleteGenerator(defenceStat, offenceStat));
		}
		// This if statement comes from the RocketManager Example in Lab 6
		 if (args.length > 0 && (args[0].equals("cmd"))) {
	            CommandLine ui = new CommandLine();
	        	GameEnvironment game = new GameEnvironment(ui, draft);
	            game.startUi();
	        } else {
	            Gui gui = new Gui();
	            GameEnvironment game = new GameEnvironment(gui, draft);
	            SwingUtilities.invokeLater(() -> game.startGui());
	        }
		
	}

}
