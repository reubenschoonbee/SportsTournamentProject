package sportstournament.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.GameEnvironment;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;


/**
 * class for the club main screen where the user can choose to go to the inventory screen or the team screen
 * extends the abstract class screen
 * @author rsc103
 *
 */
public class ClubScreen extends Screen {

	/**
	 * the classes local reference to the frame
	 */
	private JFrame clubWindow;
	/**
	 * the classes local reference to the games club class 
	 */
	private Club club;
	

	
	/**
	 * creates the club screen and initializes the required class level variables
	 * calls the parents constructor
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that can open and close windows
	 */
	public ClubScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		club = game.getClub();
		initialize();
		super.window = clubWindow;	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		clubWindow = new JFrame();
		clubWindow.setTitle("Club");
		clubWindow.setBounds(100, 100, 784, 470);
		clubWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clubWindow.getContentPane().setLayout(null);
		
		JButton viewTeamBtn = new JButton("View Team");
		viewTeamBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewTeamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeClub();
				gui.openTeamPropertiesScreen();
			}
		});
		viewTeamBtn.setBounds(96, 100, 253, 208);
		clubWindow.getContentPane().add(viewTeamBtn);
		
		JButton inventoryBtn = new JButton("View Inventory");
		inventoryBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeClub();
				gui.openInventoryScreen();
			}
		});
		inventoryBtn.setBounds(415, 100, 253, 208);
		clubWindow.getContentPane().add(inventoryBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeClub();
				gui.openMainScreen();
			}
		});
		backBtn.setBounds(73, 368, 137, 32);
		clubWindow.getContentPane().add(backBtn);
		
		DefaultListModel<Athlete> activeTeamModel = new DefaultListModel<Athlete>();
		activeTeamModel.addAll(club.viewActiveTeam());
			}
}
