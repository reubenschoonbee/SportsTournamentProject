package sportstournament.gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements a TakeByeScreen which is opened when the take bye button is pressed.
 * It gives the option for the player to specially train an athlete
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class TakeByeScreen extends Screen {

	/**
	 * The JFrame where all contents are displayed
	 */
	private JFrame takeByeWindow;
	/**
	 * The list of athletes in the active team
	 */
	private ArrayList<Athlete> starters;
	/** 
	 * The list of athletes in the reserves
	 */
	private ArrayList<Athlete> reserves;
	/**
	 * The selected athlete to specially train
	 */
	private Athlete chosenAthlete;

	/**
	 * The constructor for TakeByeScreen. Sets the starters and the reserves.
	 * 
	 * @param game The GameEnvironment object that keeps track of the game
	 * @param gui The Gui object that is used to open and close windows
	 */
	public TakeByeScreen(GameEnvironment game, Gui gui) {
		super(game, gui);
		starters = game.getActiveTeam();
		reserves = game.getReserves();
		initialize();
		super.window = takeByeWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		takeByeWindow = new JFrame();
		takeByeWindow.setTitle("Bye");
		takeByeWindow.setBounds(100, 100, 791, 468);
		takeByeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		takeByeWindow.getContentPane().setLayout(null);
		
		JLabel lblChooseAthleteToTrain = new JLabel("Choose athlete to specially train:");
		lblChooseAthleteToTrain.setBounds(43, 25, 317, 15);
		takeByeWindow.getContentPane().add(lblChooseAthleteToTrain);
		
		JButton btnTrainAthlete = new JButton("Train athlete");
		btnTrainAthlete.setEnabled(false);
		btnTrainAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.trainAthlete(chosenAthlete);
				gui.takeBye();
			}
		});
		btnTrainAthlete.setBounds(548, 357, 167, 25);
		takeByeWindow.getContentPane().add(btnTrainAthlete);
		
		JLabel lblSpeciallyTrainInfo = new JLabel("<html>The chosen athlete will get a +5 boost for<br/>both their offence and defence stats.<html>");
		lblSpeciallyTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeciallyTrainInfo.setBounds(43, 344, 317, 38);
		takeByeWindow.getContentPane().add(lblSpeciallyTrainInfo);
		
		JLabel lblStarters = new JLabel("Starters:");
		lblStarters.setBounds(53, 55, 70, 15);
		takeByeWindow.getContentPane().add(lblStarters);
		
		JLabel lblReserves = new JLabel("Reserves:");
		lblReserves.setBounds(53, 203, 70, 15);
		takeByeWindow.getContentPane().add(lblReserves);
		
		DefaultListModel<Athlete> startersListModel = new DefaultListModel<Athlete>();
		startersListModel.addAll(starters);
		JList<Athlete> startersList = new JList<Athlete>(startersListModel);
		startersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		startersList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnTrainAthlete.setEnabled(true);
				chosenAthlete = startersList.getSelectedValue();
			}
		});
		startersList.setBounds(43, 82, 672, 102);
		takeByeWindow.getContentPane().add(startersList);
		
		DefaultListModel<Athlete> reservesListModel = new DefaultListModel<Athlete>();
		reservesListModel.addAll(reserves);
		JList<Athlete> reservesList = new JList<Athlete>(reservesListModel);
		reservesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnTrainAthlete.setEnabled(true);
				chosenAthlete = reservesList.getSelectedValue();
			}
		});
		reservesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservesList.setBounds(43, 230, 672, 102);
		takeByeWindow.getContentPane().add(reservesList);
		
		JLabel informationLabel = new JLabel("The last clicked athlete will be the chosen athlete.");
		informationLabel.setBounds(43, 388, 365, 15);
		takeByeWindow.getContentPane().add(informationLabel);
	}
}
