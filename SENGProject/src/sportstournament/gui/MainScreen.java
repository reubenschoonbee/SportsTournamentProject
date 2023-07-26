package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * the class for the main screen where player can choose to go the club, market, stadium or take a bye
 * extends the abstract class screen
 * @author rsc103
 *
 */
public class MainScreen extends Screen {
	
	/**
	 * the classes local reference to the frame
	 */
	private JFrame mainWindow;

	
	/**
	 * creates a MainScreen instance and calls the parent classes constructor
	 * calls the screens initialize method
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that opens and closes windows
	 */
	public MainScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		initialize();
		super.window = mainWindow;
	}
		
	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setTitle("4-A-Side Football Main Menu");
		mainWindow.setBounds(100, 100, 770, 464);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Main Menu!");
		lblWelcomeToThe.setBounds(268, 12, 244, 15);
		mainWindow.getContentPane().add(lblWelcomeToThe);

	
		JButton btnClub = new JButton("Club");
		btnClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMainScreen();
				gui.openClub();

			}
		});
		btnClub.setBounds(117, 154, 192, 76);
		mainWindow.getContentPane().add(btnClub);
		
		JButton btnStadium = new JButton("Stadium");
		btnStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMainScreen();
				gui.OpenStadium();

			}
		});
		btnStadium.setBounds(397, 154, 192, 76);
		mainWindow.getContentPane().add(btnStadium);
		
		JButton btnMarket = new JButton("Market");
		btnMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMainScreen();
				gui.OpenMarket();

			}
		});
		btnMarket.setBounds(117, 248, 192, 76);
		mainWindow.getContentPane().add(btnMarket);
		
		JButton btnNewButton = new JButton("Take Bye");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String randomEventOccurrence = game.takeBye();
				if (game.getWeeksRemaining() == -1) {
					gui.endGame();
				} else {
					if (randomEventOccurrence == "Athlete Boost") {
						Athlete boostedAthlete = game.getBooster();
						String message = "It's your lucky day! " + boostedAthlete.getName() + "'s stats have been boosted by 10";
						JOptionPane.showMessageDialog(mainWindow, message, "Random Event", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (randomEventOccurrence == "Athlete Quits") {
						Athlete quitter = game.getQuitter();
						String message = "Oh no! " + quitter.getName() + " has quit your team. A reserve has been added to your starting team.";
						JOptionPane.showMessageDialog(mainWindow, message, "Random Event", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (randomEventOccurrence == "Athlete Joins") {
						Athlete joiner = game.getJoiner();
						String message = "Wow! " + joiner.getName() + " has joined your reserves. Go to club to see their stats.";
						JOptionPane.showMessageDialog(mainWindow, message, "Random Event", JOptionPane.INFORMATION_MESSAGE);
					}
					gui.closeMainScreen();
					gui.openTakeByeScreen();
				}
			}
		});
		btnNewButton.setBounds(397, 248, 192, 76);
		mainWindow.getContentPane().add(btnNewButton);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMainScreen();
			}
		});
		btnQuit.setBounds(616, 397, 117, 25);
		mainWindow.getContentPane().add(btnQuit);
		
		JLabel lblTotalFunds = new JLabel("Total Funds:");
		lblTotalFunds.setBounds(60, 37, 117, 15);
		mainWindow.getContentPane().add(lblTotalFunds);
		
		JLabel lblgetFunds = new JLabel("");
		lblgetFunds.setText("$"+ game.getMoneyAmount());
		lblgetFunds.setBounds(158, 37, 70, 15);
		mainWindow.getContentPane().add(lblgetFunds);
		
		JLabel lblCurrentWeek = new JLabel("Current Week:");
		lblCurrentWeek.setBounds(60, 70, 117, 15);
		mainWindow.getContentPane().add(lblCurrentWeek);
		
		JLabel lblWeeksRemaining = new JLabel("Weeks Remaining:");
		lblWeeksRemaining.setBounds(60, 97, 147, 15);
		mainWindow.getContentPane().add(lblWeeksRemaining);
		
		JLabel lblgetCurrentWeek = new JLabel("");
		lblgetCurrentWeek.setText("" + game.getCurrentWeek());
		lblgetCurrentWeek.setBounds(168, 70, 70, 15);
		mainWindow.getContentPane().add(lblgetCurrentWeek);
		
		JLabel lblgetWeeksRemaining = new JLabel("");
		lblgetWeeksRemaining.setText("" + game.getWeeksRemaining());
		lblgetWeeksRemaining.setBounds(201, 97, 70, 15);
		mainWindow.getContentPane().add(lblgetWeeksRemaining);
	}
}
