package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import sportstournament.main.GameEnvironment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * the class for the market screen where the player can go to the buy item screen or the buy athlete screen
 * extends the abstract screen class
 * @author Isaac Steele and Reuben Schoonbee
 */
public class MarketScreen extends Screen{

	/**
	 * the classes local reference to the frame
	 */
	private JFrame marketWindow;

	/**
	 * creates a MarketScreen instance
	 * calls the parents constructor
	 * initializes the class level variables
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that can open and close windows
	 */
	public MarketScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		initialize();
		super.window = marketWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		marketWindow = new JFrame();
		marketWindow.setTitle("Market");
		marketWindow.setBounds(100, 100, 804, 463);
		marketWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marketWindow.getContentPane().setLayout(null);
		
		JLabel lblTotalFunds = new JLabel("Total Funds:");
		lblTotalFunds.setText("");
		lblTotalFunds.setBounds(53, 33, 270, 48);
		marketWindow.getContentPane().add(lblTotalFunds);
		
		JButton btnDraftAthletesBack = new JButton("Buy and sell athletes");
		btnDraftAthletesBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMarket();
				gui.openBuyAthleteScreen();
			}
		});
		btnDraftAthletesBack.setBounds(79, 73, 270, 259);
		marketWindow.getContentPane().add(btnDraftAthletesBack);
		
		JButton btnViewAvailableItems = new JButton("Buy and sell items");
		btnViewAvailableItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMarket();
				gui.openBuyItemScreen();
			}
		});
		btnViewAvailableItems.setBounds(422, 73, 260, 259);
		marketWindow.getContentPane().add(btnViewAvailableItems);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMarket();
				gui.openMainScreen();
			}
		});
		btnMainMenu.setBounds(648, 396, 117, 25);
		marketWindow.getContentPane().add(btnMainMenu);
	}

}
