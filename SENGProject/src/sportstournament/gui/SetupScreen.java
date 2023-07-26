package sportstournament.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;

import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * This class implements a SetupScreen which prompts the user to enter a team name, select the season length, choose the difficulty and draft a starting team.
 * The user will not be able to start the game until they have finished the setup and met all the requirements.
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class SetupScreen {

	/**
	 * The frame that displays all the contents
	 */
	private JFrame setupWindow;
	/**
	 * The text field where the user inputs their desired team name
	 */
	private JTextField teamNameTextField;
	/**
	 * The list of athletes they user can draft
	 */
	private ArrayList<Athlete> athletesToDraft;
	/**
	 * The instance of GameEnvironment used to update and keep track of the game
	 */
	private GameEnvironment game;
	/**
	 * The chosen team name
	 */
	private String teamName;
	/**
	 * The chosen difficulty
	 */
	private String difficulty;
	/**
	 * The drafted athletes
	 */
	private ArrayList<Athlete> chosenAthletes;
	/**
	 * The selected season length
	 */
	private int numWeeks;
	/**
	 * The current list of selected athletes from the JList
	 */
	private List<Athlete> selectedAthletes =  new ArrayList<Athlete>();
	/**
	 * The amount of funds the user can use to draft athletes
	 */
	private int draftFunds = 22;
	/**
	 * The previously selected list of athletes from the JList
	 */
	private List<Athlete> oldAthletes = new ArrayList<Athlete>();

	

	/**
	 * Constructor for SetupScreen 
	 * @param game Used to make updates to the current state of the game
	 * @param athletesToDraft The list of athletes the user can draft
	 */
	public SetupScreen(GameEnvironment game, ArrayList<Athlete> athletesToDraft) {
		this.game = game;
		this.athletesToDraft = athletesToDraft;
		initialize();
		setupWindow.setVisible(true);
	}
	
	/**
	 * Closes the window
	 */
	public void closeWindow() {
		
		setupWindow.dispose();
	}
	
	/**
	 * This method is called once all requirements and conditions are met for the setup and it takes in all selections and passes them to game to handle.
	 */
	public void setupComplete() { 
		teamName = teamNameTextField.getText();
		chosenAthletes = (ArrayList<Athlete>) selectedAthletes;
		game.finishSetup(teamName, chosenAthletes, numWeeks, difficulty);;
	}
	/**
	 * Returns a boolean that depends on whether a set of conditions are met.
	 * Used to check if the user input meets the setup requirements.
	 * 
	 * @return true if all conditions are met and false otherwise
	 */
	public boolean checkAllSelected() {
		String name = teamNameTextField.getText();
		boolean acceptableName = (Pattern.matches("[a-zA-Z0-9]+(\\s)?[a-zA-Z0-9]+", name) && name.length() <= 15 && name.length() >=3);
		// enable button if a difficulty is chosen, name is right, season length is selected, 4 players are selected, and the balance > 0.
		return (acceptableName && difficulty!= null && numWeeks >=5 && numWeeks<=15 && selectedAthletes.size() == 4 && draftFunds >= 0);
	}
	/**
	 * Checks for a change in the selected athletes and updates the draftfunds based on changes made.
	 */
	public void changeChecker() {
		for (Athlete oldAthlete : oldAthletes) {
			if (!selectedAthletes.contains(oldAthlete)) {
				draftFunds += oldAthlete.getPrice();
			}
		}
		for (Athlete newAthlete : selectedAthletes) {
			if (!oldAthletes.contains(newAthlete)) {
				draftFunds -= newAthlete.getPrice();
			}
		}
		oldAthletes = selectedAthletes;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupWindow = new JFrame();
		setupWindow.setTitle("4-A-Side Football");
		setupWindow.setBounds(100, 100, 866, 454);
		setupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupWindow.getContentPane().setLayout(null);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Athlete athlete: selectedAthletes) {
					String message = "Choose a nickname for " + athlete.getName() +"\n(Between 3 to 15 characters)";
					String selection = (String) JOptionPane.showInputDialog(setupWindow, message, "Nickname athlete", JOptionPane.PLAIN_MESSAGE);
					if (selection != null) {
						while (selection.length() < 3 | selection.length() > 15 | !(Pattern.matches("[a-zA-Z0-9]+(\\s)?[a-zA-Z0-9]+", selection))) {
							if (selection.length() == 0) {
								break;
							}
							JOptionPane.showMessageDialog(setupWindow, "Please enter a valid nickname!", "Invalid Nickname", JOptionPane.ERROR_MESSAGE);
							selection = (String) JOptionPane.showInputDialog(setupWindow, message, "Nickname athlete", JOptionPane.PLAIN_MESSAGE);
							if (selection == null) {
								break;
							}
						}
					}
					if (selection != null) {
						if (selection.length() >=3 && selection.length() <=15 && (Pattern.matches("[a-zA-Z0-9]+(\\s)?[a-zA-Z0-9]+", selection))) {
							athlete.setName(selection);
						}	
					}
				}
				setupComplete();
			}
		});
		btnAccept.setEnabled(false);
		btnAccept.setBounds(700, 380, 117, 25);
		setupWindow.getContentPane().add(btnAccept);
		
		JLabel lblWelcomeToGame = new JLabel("Welcome to 4-A-Side Football!");
		lblWelcomeToGame.setBounds(285, 12, 313, 15);
		setupWindow.getContentPane().add(lblWelcomeToGame);
		
		JLabel lblChooseTeamName = new JLabel("Choose your team name:");
		lblChooseTeamName.setBounds(71, 50, 202, 15);
		setupWindow.getContentPane().add(lblChooseTeamName);
		
		JLabel lblTeamNameRequirement = new JLabel("(Must be between 3 - 15 characters and no special characters)");
		lblTeamNameRequirement.setBounds(285, 67, 458, 15);
		setupWindow.getContentPane().add(lblTeamNameRequirement);
		
		Integer[] weeks = {5,6,7,8,9,10,11,12,13,14,15};
		JComboBox<Integer> weekSelection = new JComboBox<Integer>(weeks);
		weekSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numWeeks = (int) weekSelection.getSelectedItem();
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}
		});
		weekSelection.setBounds(295, 104, 74, 24);
		setupWindow.getContentPane().add(weekSelection);
		
		JLabel lblSelectTheLength = new JLabel("Select the length of the season:");
		lblSelectTheLength.setBounds(28, 104, 245, 20);
		setupWindow.getContentPane().add(lblSelectTheLength);
		
		JLabel lblChooseDifficulty = new JLabel("Choose difficulty:");
		lblChooseDifficulty.setBounds(115, 149, 144, 15);
		setupWindow.getContentPane().add(lblChooseDifficulty);
		
		JButton btnEasy = new JButton("Easy");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = "Easy";
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}
		});
		btnEasy.setBounds(285, 145, 117, 25);
		setupWindow.getContentPane().add(btnEasy);
		
		JButton btnHard = new JButton("Hard");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = "Hard";
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}
		});
		btnHard.setBounds(485, 145, 117, 25);
		setupWindow.getContentPane().add(btnHard);
		
		JLabel lblDraftStartingAthletes = new JLabel("Draft 4 starting athletes:");
		lblDraftStartingAthletes.setBounds(71, 177, 212, 15);
		setupWindow.getContentPane().add(lblDraftStartingAthletes);
		
		teamNameTextField = new JTextField();
		teamNameTextField.setBounds(285, 48, 212, 19);
		setupWindow.getContentPane().add(teamNameTextField);
		teamNameTextField.setColumns(10);
		//This DocumentListener comes from the RocketManager Example in Lab 6
		teamNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}
		});
		
		
		JLabel lblBalance = new JLabel("Balance:  $"+ draftFunds);
		lblBalance.setBounds(700, 308, 117, 16);
		setupWindow.getContentPane().add(lblBalance);
		
		DefaultListModel<Athlete> athleteListModel = new DefaultListModel<Athlete>();
		athleteListModel.addAll(athletesToDraft);
		JList<Athlete> draftAthletes = new JList<Athlete>(athleteListModel);
		draftAthletes.setVisibleRowCount(10);
		draftAthletes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedAthletes = draftAthletes.getSelectedValuesList();
				changeChecker();
				lblBalance.setText("Balance:  $"+ draftFunds);
				
				if(checkAllSelected()) {
					btnAccept.setEnabled(true);
				}
				else {
					btnAccept.setEnabled(false);
				}
			}
		});
		draftAthletes.setBounds(23, 220, 659, 185);
		draftAthletes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setupWindow.getContentPane().add(draftAthletes);
		
		JLabel lblholdCtrlWhileSelecting = new JLabel("(Hold Ctrl while selecting)");
		lblholdCtrlWhileSelecting.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblholdCtrlWhileSelecting.setBounds(78, 193, 195, 15);
		setupWindow.getContentPane().add(lblholdCtrlWhileSelecting);
		
		JLabel lblBudgetWarning = new JLabel("<html>Choose wisely and<br/>be aware of your budget!");
		lblBudgetWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudgetWarning.setBounds(700, 233, 144, 47);
		setupWindow.getContentPane().add(lblBudgetWarning);
		
		
	}
}
