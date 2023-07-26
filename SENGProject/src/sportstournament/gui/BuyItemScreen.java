package sportstournament.gui;

import javax.swing.JFrame;
import sportstournament.main.GameEnvironment;
import sportstournament.main.Item;
import sportstournament.main.Market;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * class for the buy item screen where player can purchase and sell items
 * extends the abstract screen class
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class BuyItemScreen extends Screen{

	/**
	 * the classes local reference to the frame
	 */
	private JFrame frame;
	/**
	 * the classes local reference to the games market class
	 */
	private Market market;
	/**
	 * the items in the inventory
	 */
	private ArrayList<Item> inventory;
	/**
	 * the items available for purchase
	 */
	private ArrayList<Item> availableItems;

	
	/**
	 * Creates a BuyItemScreen instance
	 * calls the parents constructor method
	 * Initializes required variables at class level, updating all the required information on the frame
	 * calls the screens initializes method
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that can open and close windows
	 */
	public BuyItemScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		market = game.getMarket();
		availableItems = market.viewAvailableItems();
		inventory = game.getClub().viewItems();
		initialize();
		this.window = frame;	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 734, 434);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultListModel<Item> availableItemsModel= new DefaultListModel<Item>();
		availableItemsModel.addAll(availableItems);
		DefaultListModel<Item> inventoryModel= new DefaultListModel<Item>();
		inventoryModel.addAll(inventory);
		
		JButton buyItemBtn = new JButton("Buy item");
		buyItemBtn.setEnabled(false);
		buyItemBtn.setBounds(64, 191, 117, 25);
		frame.getContentPane().add(buyItemBtn);
		
		JButton sellItemBtn = new JButton("Sell item");
		sellItemBtn.setEnabled(false);
		sellItemBtn.setBounds(64, 357, 117, 25);
		frame.getContentPane().add(sellItemBtn);
		
		JList<Item> availableItemsList = new JList<>(availableItemsModel);
		availableItemsList.setBounds(48, 73, 615, 106);
		frame.getContentPane().add(availableItemsList);
		
		JList<Item> inventoryList = new JList<>(inventoryModel);
		inventoryList.setFont(new Font("Dialog", Font.BOLD, 12));
		inventoryList.setBounds(48, 239, 615, 106);
		JScrollPane itemsScrollPane = new JScrollPane(inventoryList);
		itemsScrollPane.setLocation(48, 239);
		itemsScrollPane.setSize(615, 106);
		frame.getContentPane().add(itemsScrollPane);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.OpenMarket();
				gui.closeBuyItemScreen();
			}
		});
		btnBack.setBounds(545, 357, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCurrentFunds = new JLabel("Current Funds:");
		lblCurrentFunds.setBounds(50, 30, 131, 15);
		frame.getContentPane().add(lblCurrentFunds);
		
		JLabel fundsLabel = new JLabel("New label");
		fundsLabel.setText("$"+game.getMoneyAmount());
		fundsLabel.setBounds(174, 30, 70, 15);
		frame.getContentPane().add(fundsLabel);
		
		JLabel availableItemsLabel = new JLabel("Available Items");
		availableItemsLabel.setBounds(305, 46, 131, 15);
		frame.getContentPane().add(availableItemsLabel);
		
		JLabel yourItemsLabel = new JLabel("Your items");
		yourItemsLabel.setBounds(324, 212, 94, 15);
		frame.getContentPane().add(yourItemsLabel);
		
		availableItemsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				if (!listSelectionEvent.getValueIsAdjusting()) {
					if (availableItemsList.getSelectedIndex() != -1) {
						buyItemBtn.setEnabled(true);
					}
					else {
						buyItemBtn.setEnabled(false);
					}
					
				}
			}
		});
		
		inventoryList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				if (!listSelectionEvent.getValueIsAdjusting()) {
					if (inventoryList.getSelectedIndex() != -1) {
						sellItemBtn.setEnabled(true);
					}
					else {
						sellItemBtn.setEnabled(false);
					}	
				}
			}
		});
		
		buyItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buyIndex = availableItemsList.getSelectedIndex();
				String message = availableItems.get(buyIndex).name()+" purchased for $"+availableItems.get(buyIndex).getPrice();
				if (market.buyItem(availableItems.get(buyIndex), game.getClub()) == "You cannot afford this!") {
					JOptionPane.showMessageDialog(frame, "You cannot afford this item", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					JOptionPane.showMessageDialog(frame, message, "Item Purchased", JOptionPane.INFORMATION_MESSAGE);

				}
				gui.closeBuyItemScreen();
				gui.OpenMarket();
			}
		});
		
		sellItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buyIndex = inventoryList.getSelectedIndex();
				String message = inventory.get(buyIndex).name()+" sold for $"+inventory.get(buyIndex).getPrice();
				market.sellItem(inventory.get(buyIndex), game.getClub());
				JOptionPane.showMessageDialog(frame, message,"item sold", JOptionPane.INFORMATION_MESSAGE);
				gui.closeBuyItemScreen();
				gui.OpenMarket();
			}
		});
	}
}
