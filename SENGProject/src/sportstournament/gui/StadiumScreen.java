package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;
import sportstournament.main.Team;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;

/**
 * This class implements a StadiumScreen where matches can be played against different teams
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class StadiumScreen extends Screen {

	/**
	 * The JFrame that displays all the contents
	 */
	private JFrame stadiumWindow;
	
	/**
	 * The list of matches available to be played
	 */
	private ArrayList<Team> matches;


	/**
	 * The constructor for StadiumScreen
	 * 
	 * @param game The GameEnvironment object that keeps track of the game
	 * @param gui The Gui object used to open and close windows
	 */
	public StadiumScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		matches = game.getMatches();
		initialize();
		super.window = stadiumWindow;
	}
	/**
	 * The message displayed at the completion of a match
	 * 
	 * @param result The result of the match
	 */
	public void displayMessage(String result) {
		String message = "The result of the match is: " + result + "\nThe updated status of your team is:\n\n" + game.getClub() + "\n\nYour total points are now: " + game.getPoints() + "\nYour total money is now: " + game.getMoneyAmount();
		JOptionPane.showMessageDialog(stadiumWindow, message, "Match Result", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		stadiumWindow = new JFrame();
		stadiumWindow.setTitle("Stadium");
		stadiumWindow.setBounds(100, 100, 788, 464);
		stadiumWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stadiumWindow.getContentPane().setLayout(null);
		
		JButton firstMatchButton = new JButton("Match 1");
		firstMatchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = game.startMatch(0);
				if (result == "Cannot start match! Must have at least one healthy athlete") {
					JOptionPane.showMessageDialog(stadiumWindow, result, "Match Result", JOptionPane.ERROR_MESSAGE);
				} else {
					displayMessage(result);
					firstMatchButton.setEnabled(false);
				}
					
			}
		});
		firstMatchButton.setBounds(677, 123, 89, 25);
		stadiumWindow.getContentPane().add(firstMatchButton);
		
		JButton secondMatchButton = new JButton("Match 2");
		secondMatchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = game.startMatch(1);
				if (result == "Cannot start match! Must have at least one healthy athlete") {
					JOptionPane.showMessageDialog(stadiumWindow, result, "Match Result", JOptionPane.ERROR_MESSAGE);
				} else {
					displayMessage(result);
					secondMatchButton.setEnabled(false);
				}
			}
		});
		secondMatchButton.setBounds(677, 234, 89, 25);
		stadiumWindow.getContentPane().add(secondMatchButton);
		
		JButton thirdMatchButton = new JButton("Match 3");
		thirdMatchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = game.startMatch(2);
				if (result == "Cannot start match! Must have at least one healthy athlete") {
					JOptionPane.showMessageDialog(stadiumWindow, result, "Match Result", JOptionPane.ERROR_MESSAGE);
				} else {
					displayMessage(result);
					thirdMatchButton.setEnabled(false);
				}
			}
		});
		thirdMatchButton.setBounds(677, 351, 89, 25);
		stadiumWindow.getContentPane().add(thirdMatchButton);
		
		JLabel lblChooseAMatch = new JLabel("Choose a match to play:");
		lblChooseAMatch.setBounds(30, 17, 229, 15);
		stadiumWindow.getContentPane().add(lblChooseAMatch);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeStadium();
				gui.openMainScreen();
			}
		});
		btnMainMenu.setBounds(649, 12, 117, 25);
		stadiumWindow.getContentPane().add(btnMainMenu);
		
		DefaultListModel<Athlete> match1ListModel = new DefaultListModel<>();
		match1ListModel.addAll(game.getPlayers(matches, 0));
		JList<Athlete> match1 = new JList<>(match1ListModel);
		match1.setFont(new Font("Dialog", Font.BOLD, 11));
		match1.setBounds(81, 98, 584, 71);
		stadiumWindow.getContentPane().add(match1);
		
		DefaultListModel<Athlete> match2ListModel = new DefaultListModel<>();
		match2ListModel.addAll(game.getPlayers(matches, 1));
		JList<Athlete> match2 = new JList<>(match2ListModel);
		match2.setFont(new Font("Dialog", Font.BOLD, 11));
		match2.setBounds(81, 209, 584, 71);
		stadiumWindow.getContentPane().add(match2);
		

		DefaultListModel<Athlete> match3ListModel = new DefaultListModel<>();
		match3ListModel.addAll(game.getPlayers(matches, 2));
		JList<Athlete> match3 = new JList<>(match3ListModel);
		match3.setFont(new Font("Dialog", Font.BOLD, 11));
		match3.setBounds(81, 328, 584, 71);
		stadiumWindow.getContentPane().add(match3);
		
		JLabel teamName1 = new JLabel("");
		teamName1.setText(game.getTeamName(matches, 0));
		teamName1.setBounds(91, 71, 131, 15);
		stadiumWindow.getContentPane().add(teamName1);
		
		JLabel teamName2 = new JLabel("");
		teamName2.setText(game.getTeamName(matches, 1));
		teamName2.setBounds(91, 182, 131, 15);
		stadiumWindow.getContentPane().add(teamName2);
		
		JLabel teamName3 = new JLabel("");
		teamName3.setText(game.getTeamName(matches, 2));
		teamName3.setBounds(91, 301, 141, 15);
		stadiumWindow.getContentPane().add(teamName3);
		
		JButton btnViewCurrentTeam = new JButton("View Current Team");
		btnViewCurrentTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(stadiumWindow, game.getClub(), "Current Team", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnViewCurrentTeam.setBounds(434, 12, 168, 25);
		stadiumWindow.getContentPane().add(btnViewCurrentTeam);
		
		JLabel lblFirstDefender = new JLabel("Defender:");
		lblFirstDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFirstDefender.setBounds(12, 98, 70, 15);
		stadiumWindow.getContentPane().add(lblFirstDefender);
		
		JLabel lblSecondDefender = new JLabel("Defender:");
		lblSecondDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSecondDefender.setBounds(12, 112, 70, 15);
		stadiumWindow.getContentPane().add(lblSecondDefender);
		
		JLabel lblThirdDefender = new JLabel("Defender:");
		lblThirdDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblThirdDefender.setBounds(12, 209, 70, 15);
		stadiumWindow.getContentPane().add(lblThirdDefender);
		
		JLabel lblFourthDefender = new JLabel("Defender:");
		lblFourthDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFourthDefender.setBounds(12, 225, 70, 15);
		stadiumWindow.getContentPane().add(lblFourthDefender);
		
		JLabel lblFifthDefender = new JLabel("Defender:");
		lblFifthDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFifthDefender.setBounds(12, 328, 70, 15);
		stadiumWindow.getContentPane().add(lblFifthDefender);
		
		JLabel lblSixthDefender = new JLabel("Defender:");
		lblSixthDefender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSixthDefender.setBounds(12, 343, 70, 15);
		stadiumWindow.getContentPane().add(lblSixthDefender);
		
		JLabel lblFirstAttacker = new JLabel("Attacker:");
		lblFirstAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFirstAttacker.setBounds(12, 128, 70, 15);
		stadiumWindow.getContentPane().add(lblFirstAttacker);
		
		JLabel lblSecondAttacker = new JLabel("Attacker:");
		lblSecondAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSecondAttacker.setBounds(12, 144, 70, 15);
		stadiumWindow.getContentPane().add(lblSecondAttacker);
		
		JLabel lblThirdAttacker = new JLabel("Attacker:");
		lblThirdAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblThirdAttacker.setBounds(12, 239, 70, 15);
		stadiumWindow.getContentPane().add(lblThirdAttacker);
		
		JLabel lblFourthAttacker = new JLabel("Attacker:");
		lblFourthAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFourthAttacker.setBounds(12, 252, 70, 15);
		stadiumWindow.getContentPane().add(lblFourthAttacker);
		
		JLabel lblFifthAttacker = new JLabel("Attacker:");
		lblFifthAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFifthAttacker.setBounds(12, 356, 70, 15);
		stadiumWindow.getContentPane().add(lblFifthAttacker);
		
		JLabel lblSixthAttacker = new JLabel("Attacker:");
		lblSixthAttacker.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSixthAttacker.setBounds(12, 371, 70, 15);
		stadiumWindow.getContentPane().add(lblSixthAttacker);
		
		JButton InfoButton = new JButton("Info");
		InfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "During a match, the athletes in your team will face off against the athletes in the opposition team.\n"
						+ "Each of your defenders will face off against their respective defender in the opposition team.\n"
						+ "The defender with the highest defence stat will gain their team 10 points. If their stats are the same, both teams will gain 5 points.\n"
						+ "However, if your defender is injured they will lose their face off.\n"
						+ "The same applies for attackers, except their offence stat will be compared.\n"
						+ "The team with the highest score will be victorious.\n"
						+ "Caution: If your whole team is injured you cannot compete in a match. Also, if your whole team is injured during a match you will lose.\n\n"
						+ "Rewards for winning:\n\nEasy - 3 points, $10\nHard - 5 points, $5\n\n"
						+ "Rewards for a draw:\n\nEasy - 1 point, $5\nHard - 2 points, $3\n\n"
						+ "No rewards for a loss.\n\n"
						+ "Have fun!";
				JOptionPane.showMessageDialog(stadiumWindow, message, "How to Play", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		InfoButton.setBounds(278, 12, 117, 25);
		stadiumWindow.getContentPane().add(InfoButton);
	}
}
