package sportstournament.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import sportstournament.main.GameEnvironment;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the EndGameScreen which appears once all the weeks have been complete.
 * It displays the team name, length of the season, money gained, and points gained. 
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class EndGameScreen extends Screen{

	/**
	 * The main frame that displays all the content.
	 */
	private JFrame endGameWindow;


	/**
	 * The constructor for EndGameScreen which calls its parent's constructor (Screen)
	 * 
	 * @param game An instance of GameEnvironment that controls the current state of the game
	 * @param gui An instance of Gui that allows for the opening and closing of windows.
	 */
	public EndGameScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		initialize();
		super.window = endGameWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		endGameWindow = new JFrame();
		endGameWindow.setTitle("Game Complete");
		endGameWindow.setBounds(100, 100, 794, 462);
		endGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGameWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Team Name:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(42, 28, 159, 102);
		endGameWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblSeasonDuration = new JLabel("Season Duration:");
		lblSeasonDuration.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSeasonDuration.setBounds(42, 142, 210, 50);
		endGameWindow.getContentPane().add(lblSeasonDuration);
		
		JLabel lblMoneyGained = new JLabel("Money Gained:");
		lblMoneyGained.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMoneyGained.setBounds(42, 231, 179, 57);
		endGameWindow.getContentPane().add(lblMoneyGained);
		
		JLabel lblPointsGained = new JLabel("Points Gained:");
		lblPointsGained.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPointsGained.setBounds(42, 326, 173, 57);
		endGameWindow.getContentPane().add(lblPointsGained);
		
		JLabel lblCongratulationsYourSeason = new JLabel("Congratulations! Your season is complete.");
		lblCongratulationsYourSeason.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCongratulationsYourSeason.setBounds(244, 22, 419, 15);
		endGameWindow.getContentPane().add(lblCongratulationsYourSeason);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnQuit.setBounds(655, 395, 117, 25);
		endGameWindow.getContentPane().add(btnQuit);
		
		JLabel lblGetTeamName = new JLabel("");
		lblGetTeamName.setText(game.getClubName());
		lblGetTeamName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGetTeamName.setBounds(199, 67, 382, 25);
		endGameWindow.getContentPane().add(lblGetTeamName);
		
		JLabel lblGetSeasonDuration = new JLabel("");
		lblGetSeasonDuration.setText(game.getTotalWeeks()+ " weeks");
		lblGetSeasonDuration.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGetSeasonDuration.setBounds(253, 155, 150, 25);
		endGameWindow.getContentPane().add(lblGetSeasonDuration);
		
		JLabel lblGetMoneyGained = new JLabel("");
		lblGetMoneyGained.setText("$"+game.getMoneyAmount());
		lblGetMoneyGained.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGetMoneyGained.setBounds(221, 247, 150, 25);
		endGameWindow.getContentPane().add(lblGetMoneyGained);
		
		JLabel lblGetPointsGained = new JLabel("");
		lblGetPointsGained.setText(""+game.getPoints());
		lblGetPointsGained.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGetPointsGained.setBounds(221, 342, 128, 25);
		endGameWindow.getContentPane().add(lblGetPointsGained);
	}
}
