package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.GameEnvironment;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

/**
 * the class for the team properties screen where player can view their active team and reserves, aswell as make substitutions and position swaps
 * extends the abstract class screen
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class TeamPropertiesScreen extends Screen {

	/**
	 * the classes local reference to the frame
	 */
	private JFrame teamWindow;
	/**
	 * the classes local reference to the club class
	 */
	private Club club;
	/**
	 * the active team list
	 */
	private ArrayList<Athlete> activeTeam;
	/**
	 * the reserves list
	 */
	private ArrayList<Athlete> reserves;

    
	/**
	 * creates an instance of the TeamPropertiesScreen class
	 * calls the parent classes constructor
	 * initializes the required class level variables
	 * @param game The instance of GameEnvironment that keeps track of the game
	 * @param gui The instance of Gui that can open and close windows
	 */
	public TeamPropertiesScreen(GameEnvironment game, Gui gui) {
		super(game,gui);
		club = game.getClub();
		activeTeam = club.viewActiveTeam();
		reserves = club.viewReserves();
		initialize();
		super.window = teamWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		teamWindow = new JFrame();
		teamWindow.setTitle("Team");
		teamWindow.setBounds(100, 100, 780, 461);
		teamWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teamWindow.getContentPane().setLayout(null);
		
		JLabel lblTeamName = new JLabel("Team Name:");
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamName.setBounds(23, 13, 101, 27);
		teamWindow.getContentPane().add(lblTeamName);
		
		JLabel lblStarters = new JLabel("Starters:");
		lblStarters.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStarters.setBounds(23, 52, 84, 15);
		teamWindow.getContentPane().add(lblStarters);
		
		JLabel lblReserves = new JLabel("Reserves:");
		lblReserves.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReserves.setBounds(23, 228, 101, 15);
		teamWindow.getContentPane().add(lblReserves);
		
		
		
		JButton btnClub = new JButton("Back to club");
		btnClub.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeTeamPropertiesScreen();
				gui.openClub();
			}
		});
		btnClub.setBounds(599, 367, 142, 52);
		teamWindow.getContentPane().add(btnClub);
		
		JLabel teamName = new JLabel("");
		teamName.setFont(new Font("Tahoma", Font.BOLD, 14));
		teamName.setText(club.viewName());
		teamName.setBounds(136, 13, 226, 26);
		teamWindow.getContentPane().add(teamName);
		
		DefaultListModel<Athlete> activeTeamModel = new DefaultListModel<>();
		activeTeamModel.addAll(activeTeam);
		JList<Athlete> activeTeamList = new JList<>(activeTeamModel);
		activeTeamList.setFont(new Font("Dialog", Font.BOLD, 11));
		activeTeamList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		activeTeamList.setBounds(94, 79, 644, 84);
		teamWindow.getContentPane().add(activeTeamList);
		
		DefaultListModel<Athlete> reservesModel = new DefaultListModel<>();
		reservesModel.addAll(reserves);
		JList<Athlete> reservesList = new JList<>(reservesModel);
		reservesList.setFont(new Font("Dialog", Font.BOLD, 11));
		reservesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservesList.setBounds(23, 255, 715, 100);
		teamWindow.getContentPane().add(reservesList);
		
		JButton btnSwapAthletes = new JButton("Sub athlete");
		btnSwapAthletes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSwapAthletes.setEnabled(false);
		
		JButton btnSwapPositions = new JButton("Swap positions");
		btnSwapPositions.setBounds(424, 191, 154, 52);
		btnSwapPositions.setEnabled(false);
		teamWindow.getContentPane().add(btnSwapPositions);
		
		 ListSelectionListener selectionListener = new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selections = activeTeamList.getSelectedIndices();
		        	if (selections.length == 2) {
			        	

		        		btnSwapPositions.setEnabled(true);
		        		btnSwapAthletes.setEnabled(false);
		        	}
		        	else if (selections.length == 1 && reservesList.getSelectedIndex()!= -1) {
			        	

		            	btnSwapAthletes.setEnabled(true);
		            	btnSwapPositions.setEnabled(false);
		            }
		            else {
			      

		            	btnSwapAthletes.setEnabled(false);
		            	btnSwapPositions.setEnabled(false);
		            }
		        }
		 };
		   
		activeTeamList.addListSelectionListener(selectionListener);
		reservesList.addListSelectionListener(selectionListener);
		
		btnSwapAthletes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int activeIndex = activeTeamList.getSelectedIndices()[0];
				int reserveIndex = reservesList.getSelectedIndex();
				String message =  activeTeam.get(activeIndex).getName()+" subbed off for "+reserves.get(reserveIndex).getName();
				club.subAthlete(reserves.get(reserveIndex), activeTeam.get(activeIndex));
				JOptionPane.showMessageDialog(teamWindow, message, "Athletes swapped", JOptionPane.INFORMATION_MESSAGE);
				gui.closeTeamPropertiesScreen();
				gui.openClub();
				
			}
		});
		
		btnSwapPositions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indices = activeTeamList.getSelectedIndices();
				club.swapPositions(indices[0], indices[1]);
				String message = activeTeam.get(indices[0]).getName()+" swapped position with "+ activeTeam.get(indices[1]).getName();
				JOptionPane.showMessageDialog(teamWindow, message, "Athletes swapped positions", JOptionPane.INFORMATION_MESSAGE);
				gui.closeTeamPropertiesScreen();
				gui.openClub();
				
			}
		});
		
		
		btnSwapAthletes.setBounds(590, 193, 146, 52);
		teamWindow.getContentPane().add(btnSwapAthletes);
		
		JLabel defenderLbl = new JLabel("Defender");
		defenderLbl.setFont(new Font("Dialog", Font.BOLD, 11));
		defenderLbl.setBounds(22, 79, 70, 15);
		teamWindow.getContentPane().add(defenderLbl);
		
		JLabel defenderLbl2 = new JLabel("Defender");
		defenderLbl2.setFont(new Font("Dialog", Font.BOLD, 11));
		defenderLbl2.setBounds(23, 95, 70, 15);
		teamWindow.getContentPane().add(defenderLbl2);
		
		JLabel attackerLbl1 = new JLabel("Attacker");
		attackerLbl1.setFont(new Font("Dialog", Font.BOLD, 11));
		attackerLbl1.setBounds(23, 110, 70, 15);
		teamWindow.getContentPane().add(attackerLbl1);
		
		JLabel attackerLbl2 = new JLabel("Attacker");
		attackerLbl2.setFont(new Font("Dialog", Font.BOLD, 11));
		attackerLbl2.setBounds(23, 126, 70, 15);
		teamWindow.getContentPane().add(attackerLbl2);
		
		
	}
}
