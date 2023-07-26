package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.GameEnvironment;
import sportstournament.main.Item;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

/**
 * class for the inventory screen where player can view their items and use them on their athletes
 * extends the abstract class screen
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class InventoryScreen extends Screen {

	/**
	 * the classes local reference to the frame
	 */
	private JFrame inventoryWindow;
	/**
	 * the inventory
	 */
	private ArrayList<Item> inventory;
	/**
	 * the classes local reference to the club class
	 */
	private Club club;
	/**
	 * the active team
	 */
	private ArrayList<Athlete> activeTeam;
	/**
	 * the reserves
	 */
	private ArrayList<Athlete> reserves;


	/**
	 * creates an InventoryScreen instance
	 * calls the parents constructor
	 * initializes class level variables and updates required information for the frame
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that opens and closes windows
	 */
	public InventoryScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		club = game.getClub();
		inventory = club.viewItems();
		activeTeam = game.getActiveTeam();
		reserves = game.getReserves();
		initialize();
		super.window = inventoryWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		inventoryWindow = new JFrame();
		inventoryWindow.setTitle("Inventory");
		inventoryWindow.setBounds(100, 100, 829, 580);
		inventoryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryWindow.getContentPane().setLayout(null);
		
		JLabel lblItems = new JLabel("Available Items:");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblItems.setBounds(51, 37, 147, 15);
		inventoryWindow.getContentPane().add(lblItems);
		
		JButton btnClub = new JButton("Back to club");
		btnClub.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeInventoryScreen();
				gui.openClub();
			}
		});
		btnClub.setBounds(608, 489, 174, 44);
		inventoryWindow.getContentPane().add(btnClub);
		
		DefaultListModel<Item> inventoryModel = new DefaultListModel<Item>();
		inventoryModel.addAll(inventory);
		JList<Item> inventoryList = new JList<Item>(inventoryModel);
		inventoryList.setBounds(51, 64, 521, 119);
		JScrollPane inventoryScrollPane = new JScrollPane(inventoryList);
		inventoryScrollPane.setSize(524, 122);
		inventoryScrollPane.setLocation(48, 64);
		inventoryWindow.getContentPane().add(inventoryScrollPane);
		
		JLabel lblNewLabel = new JLabel("Please select a player to use item on");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(39, 198, 287, 15);
		inventoryWindow.getContentPane().add(lblNewLabel);
		
		DefaultListModel<Athlete> teamModel = new DefaultListModel<Athlete>();
		teamModel.addAll(activeTeam);
		teamModel.addAll(reserves);
		JList<Athlete> teamList = new JList<Athlete>(teamModel);
		teamList.setFont(new Font("Dialog", Font.BOLD, 11));
		teamList.setBounds(39, 233, 743, 246);
		inventoryWindow.getContentPane().add(teamList);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setEnabled(false);
		btnUseItem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUseItem.setBounds(608, 175, 174, 44);
		inventoryWindow.getContentPane().add(btnUseItem);
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemIndex = inventoryList.getSelectedIndex(); 
				int athleteIndex = teamList.getSelectedIndex();
				String message;
				if (athleteIndex >= 4) {
					athleteIndex -= 4;
					message = inventory.get(itemIndex).name()+" used on "+reserves.get(athleteIndex).getName();
					club.useItem(inventory.get(itemIndex), reserves.get(athleteIndex));
				}
				else {
					message = inventory.get(itemIndex).name()+" used on "+activeTeam.get(athleteIndex).getName();
					club.useItem(inventory.get(itemIndex), activeTeam.get(athleteIndex));
				}
				
				JOptionPane.showMessageDialog(inventoryWindow, message,"Item used", JOptionPane.INFORMATION_MESSAGE);
				gui.closeInventoryScreen();
				gui.openClub();
			}
		});
		
	
		ListSelectionListener selectionListener = new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	            if (teamList.getSelectedIndex() != -1 && inventoryList.getSelectedIndex()!= -1) {
	            	btnUseItem.setEnabled(true);
	            }
	            else {
	            	btnUseItem.setEnabled(false);
	            }
	        }
	    };
	    
	    teamList.addListSelectionListener(selectionListener);
		inventoryList.addListSelectionListener(selectionListener);
		
	}
	
}
