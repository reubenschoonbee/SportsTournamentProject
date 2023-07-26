package sportstournament.main;

import java.util.ArrayList;
import java.util.List;


import sportstournament.gui.Gui;
import sportstournament.ui.CommandLine;

/**
 * This class implements a GameEnvironment. It keeps track of the game, such as the weeks, money, and points. 
 * It handles requests from the UI and updates the state of the game where necessary.
 * 
 
 * @author Isaac Steele + Reuben Schoonbee
 *
 */

public class GameEnvironment {
	/**
	 * The list of athletes available to be drafted
	 */
	private ArrayList<Athlete> draft;
	/**
	 * An instance of RandomEvent which is used when a bye is taken
	 */
	private RandomEvent randomEvent;
	/**
	 * An instance of Club which will be used for the entirety of the game
	 */
	private Club club;
	/**
	 * An instance of Market which will be refreshed when a bye is taken
	 */
	private Market market;
	
	/**
	 *An instance of Stadium which will be refreshed when a bye is taken
	 */
	private Stadium stadium;
	/**
	 * An instance of CommandLine which is used when the game is run as a command line application
	 */
	private CommandLine ui;
	/**
	 * An instance of Gui which is used when the game is run as a graphical application
	 */
	private Gui gui;
	/**
	 * The total number of weeks.
	 */
	private int totalWeeks;
	/**
	 * The current week. Starts at 1 by default.
	 */
	private int currentWeek = 1;
	/**
	 * The number of weeks remaining.
	 */
	private int weeksRemaining = totalWeeks - currentWeek;
	/**
	 * The amount of money the player has. Varies based on difficulty
	 */
	private int moneyAmount;
	/**
	 * The difficulty of the game.
	 */
	private String difficulty;
	/**
	 * The points gained
	 */
	private int points = 0;
	/**
	 * GameEnvironment constructor used for testing
	 */
	public GameEnvironment() {
	}
	/**
	 * A constructor for GameEnvironment, called from the Main class to begin the setup of the game.
	 * @param ui The instance of CommandLine being used.
	 * @param draft A list of Athletes to be drafted.
	 */
	public GameEnvironment(CommandLine ui, ArrayList<Athlete> draft ) {
		
		this.draft = draft;
		this.ui = ui;
	}	
	/**
	 * A constructor for GameEnvironment, called from the Main class to begin the setup of the game.
	 * @param gui The instance of Gui being used.
	 * @param draft A list of Athletes to be drafted.
	 */
	public GameEnvironment(Gui gui, ArrayList<Athlete> draft ) {
		
		this.draft = draft;
		this.gui = gui;
	}	
	/**
	 * Starts the game for a command line application
	 */
	public void startUi() {
		ui.SetUp(this);
	}
	/**
	 * Starts the game for a graphical application
	 */
	public void startGui() {
		gui.setup(this);
	}
	/**
	 * Finishes setup and starts the main game
	 * 
	 * @param name The chosen name for the team
	 * @param team The list of Athletes drafted.
	 * @param numWeeks The chosen number of weeks for the game
	 * @param difficulty The selected difficulty
	 */
	public void finishSetup(String name, ArrayList<Athlete> team, int numWeeks, String difficulty) {
		this.difficulty = difficulty;
		this.totalWeeks = numWeeks;
		if (difficulty == "Easy") {
			this.moneyAmount = 50;
		}
		else if (difficulty == "Hard") {
			this.moneyAmount = 30;
		}
		this.weeksRemaining = this.totalWeeks - this.currentWeek;
		this.club = new Club(name,team);
		this.market = new Market(this);
		this.stadium = new Stadium(this);
		if (gui == null) {
			ui.start(); 
		}
		else if (ui == null) {
			gui.start();
		}
	}
	/**
	 * Starts the match
	 * 
	 * @param num The index of the match to start
	 * @return The result of the match
	 */
	public String startMatch(int num) {
		ArrayList<Team> matches = getMatches();
		return stadium.startMatch(club, matches.get(num));
		
	}
	/**
	 * Gets the matches
	 * 
	 * @return A list of teams that you can play.
	 */
	public ArrayList<Team> getMatches() {
		return stadium.getMatches();
	}
	/**
	 * Returns the team name
	 * 
	 * @param teams A list of Teams
	 * @param index The index of the team to get the name of
	 * @return the team name
	 */
	public String getTeamName(ArrayList<Team> teams, int index) {
		Team team = teams.get(index);
		return team.viewName();
	}
	/**
	 * Gets the Athletes in the team
	 * 
	 * @param teams A list of Teams
	 * @param index The index of the team to return the Athletes of.
	 * @return A list of Athletes, representing the team.
	 */
	public ArrayList<Athlete> getPlayers(ArrayList<Team> teams, int index) {
		Team team = teams.get(index);
		return team.viewActiveTeam();
	}
	/**
	 * Gets the total price of the team
	 * 
	 * @param athletes A list of Athletes
	 * @return The sum of the prices of all Athletes in the team
	 */
	public int getTeamPrice(List<Athlete> athletes) {
		int price = 0;
		for (Athlete athlete: athletes) {
			price += athlete.getPrice();
		}
		return price;
	}
	/**
	 * Gets the active team
	 * 
	 * @return the active team of the Club
	 */
	public ArrayList<Athlete> getActiveTeam() {
		return club.viewActiveTeam();
	}
	/**
	 * Gets the reserves
	 * 
	 * @return The reserves of the Club
	 */
	public ArrayList<Athlete> getReserves() {
		return club.viewReserves();
	}
	/**
	 * Specially trains an Athlete
	 * 
	 * @param athlete The chosen Athlete to be specially trained.
	 */
	public void trainAthlete(Athlete athlete) {
		athlete.increaseDefence(5);
		athlete.increaseOffence(5);
	}
	/**
	 * Returns the Club name
	 * 
	 * @return the name of the Club
	 */
	public String getClubName() {
		return club.viewName();
	}
	/**
	 * Gets the Club
	 * 
	 * @return the Club
	 */
	public Club getClub() {
		return club;
	}	
	/**
	 * Returns the Market.
	 * 
	 * @return the Market
	 */
	public Market getMarket() {
		return market;
	}
	/**
	 * Gets the Stadium
	 * 
	 * @return the Stadium
	 */
	public Stadium getStadium() {
		return stadium;
	}
	/**
	 * Returns a list of drafted Athletes
	 * 
	 * @return The drafted Athletes
	 */
	public ArrayList<Athlete> getDraft() {
		return draft;
	}
	/**
	 * Returns the total weeks
	 * 
	 * @return the amount of weeks the game shall be played for
	 */
	public int getTotalWeeks() {
		return totalWeeks;
	}
	/**
	 * Sets the total number of weeks.
	 * 
	 * @param totalWeeks The desired number of weeks chosen by the player, ranging from 5 - 15.
	 */
	public void setTotalWeeks(int totalWeeks) {
		this.totalWeeks = totalWeeks;
	}
	/**
	 * Returns the current week of the game.
	 * 
	 * @return The current week of the game.
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}
	/**
	 * Returns the number of weeks remaining.
	 * 
	 * @return The number of weeks remaining.
	 */
	public int getWeeksRemaining() {
		return weeksRemaining;
	}
	/**
	 * Sets the weeks remaining
	 * 
	 * @param week The number of weeks remaining
	 */
	public void setWeeksRemaining(int week) {
		weeksRemaining = week;
	}
	/**
	 * Returns the amount of money the player currently has.
	 * 
	 * @return The amount of money the player currently has.
	 */
	public int getMoneyAmount() {
		return moneyAmount;
	}
	/**
	 * Sets the amount of money the player has based on their difficulty setting.
	 * 
	 *@param money The amount of money the player gets.
	 */
	public void setMoneyAmount(int money) {
		this.moneyAmount = money;
	}
	/**
	 * Updates the amount of money 
	 * 
	 * @param gains The amount of money gained.
	 */
	public void updateMoney(int gains) {
		this.moneyAmount += gains;
	}
	/**
	 * Decreases the total money
	 * 
	 * @param withdraw The amount of money to decrease the total by.
	 */
	public void decreaseMoney(int withdraw) {
		if (this.moneyAmount - withdraw < 0) {
			throw new ArithmeticException("You cannot afford this!");
		} else {
			this.moneyAmount -= withdraw;
		}
	}
	/** 
	 * The amount of points the player has
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Adds points for winning a match.
	 * 
	 * @param points number of points to add
	 */
	public void updatePoints(int points) {
		this.points += points;
	}
	/**
	 * Updates the current week. This will occur after a bye.
	 */
	public void updateCurrentWeek() {
		currentWeek += 1;
	}
	/**
	 * Updates the weeks remaining. This will occur after a bye.
	 */
	public void updateWeeksRemaining() {
		weeksRemaining -= 1;
	}
	/**
	 * Sets the difficulty of the game
	 * 
	 * @param difficulty The difficulty chosen in the setup
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * Gets the difficulty of the game.
	 * 
	 * @return The game difficulty
	 */
	public String getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Takes and handles users input selection from the club screen
	 * 
	 * @param selection The selected number which each correlate to a different option.
	 */
	public void handleClubOptions(int selection) {
		
		switch (selection) {
			
		default:
			throw new IllegalArgumentException("Unexpected value");
			
		case(1):
			for (Athlete athlete: club.viewActiveTeam()) {
				System.out.println(athlete);
			}
			System.out.println("1: Return to club");
			ui.singleIntegerInput(1);
			returnToClub();
			break;
			
		case(2):
			for (Athlete athlete: club.viewReserves()) {
				System.out.println(athlete);
			}
			System.out.println("1: Return to club");
			ui.singleIntegerInput(1);
			returnToClub();
			break;
			
		case(3):
			ui.printInventoryOptions(club);
			int itemIndex = ui.getIntegerInput(club.viewItems().size()+1);
			if (itemIndex == club.viewItems().size()+1) {
				returnToClub();
				break;
			}
			ui.printWholeTeam(club);
			int athleteIndex = ui.getIntegerInput(club.viewActiveTeam().size() + club.viewReserves().size() + 1);
			if (athleteIndex == club.viewActiveTeam().size() + club.viewReserves().size() + 1) {
				returnToClub();
				break;
			}
			handleItemUse(itemIndex - 1, athleteIndex - 1);
			break;
			
		case(4):
			ui.printSubOffOptions(club);
			int subOffIndex = ui.getIntegerInput(5);
			if (subOffIndex == 5) {
				returnToClub();
				break;
			}
			ui.printSubOnOptions(club);
			int subOnIndex = ui.getIntegerInput(club.viewReserves().size() + 1);
			if (subOnIndex == club.viewReserves().size() + 1) {
				returnToClub();
				break;
			}
			club.subAthlete(club.viewActiveTeam().get(subOffIndex - 1), club.viewReserves().get(subOnIndex - 1));
			break;
		case(5):
			ui.start();
			break;
			
		}
	}
	/**
	 * Moves the game to the next week. Updates the Market and Stadium and has a chance a random event occurs.
	 * 	
	 * @return A string that contains the random event that occurred or "None" if none occur.
	 */
	public String takeBye() {
		updateCurrentWeek();
		updateWeeksRemaining();
		market = new Market(this);
		stadium = new Stadium(this);
		randomEvent = new RandomEvent(club);
		String randomEventOccurrence = randomEvent.doRandomEvent(difficulty);
		for(Athlete athlete : club.activeTeam) {
			athlete.restoreStamina();
		}
		return randomEventOccurrence;
	}
	
	/**
	 * Returns the athlete that randomly joined the team.
	 * 
	 * @return the athlete that randomly joined the team.
	 */
	public Athlete getJoiner() {
		return randomEvent.getJoiner();
	}
	/**
	 * Returns the athlete that randomly quit your team.
	 * 
	 * @return the athlete that randomly quit your team.
	 */
	public Athlete getQuitter() {
		return randomEvent.getQuitter();
	}
	/**
	 * Returns the athlete that got the stat boost.
	 * @return the athlete that got the stat boost.
	 */
	public Athlete getBooster() {
		return randomEvent.getBooster();
	}
	
	/**
	 * Takes the users' selection for item and athlete and uses the item on the athlete
	 * @param itemIndex the index of the item to use
	 * @param athleteIndex the index of the athlete that is using the item
	 */
	public void handleItemUse(int itemIndex, int athleteIndex) {
		
		Item item = club.viewItems().get(itemIndex);
		Athlete athlete;
		
		if (athleteIndex > 4) {
			athleteIndex -= 4;
			athlete = club.viewReserves().get(athleteIndex);	
		}
		else {
			athlete = club.viewActiveTeam().get(athleteIndex);
		}
		club.useItem(item, athlete);
	}
	

	/**
	 * Returns to club
	 */
	public void returnToClub() {
		ui.printClubOptions();
		int selection = ui.getIntegerInput(5);
		handleClubOptions(selection);
	}
	
	/**
	 *Offers the user the option of specially training an athlete
	 */
	public void speciallyTrainAthlete() {
		

	   	ArrayList<Athlete> activeTeam = club.viewActiveTeam();
		ArrayList<Athlete> reserves = club.viewReserves();
		int i = 1;
		System.out.println("Choose an athlete to specially train:");
		for(Athlete starter : activeTeam) {
			System.out.println("("+i+") " + starter);
			i += 1;
		}
		for(Athlete reserve : reserves) {
			System.out.println("("+i+") " + reserve);
			i += 1;	
		}
		int chosenAthleteNum = ui.getIntegerInput(activeTeam.size() + reserves.size()) - 1;
		if (chosenAthleteNum < activeTeam.size()) {
			Athlete chosenAthlete = activeTeam.get(chosenAthleteNum);
			chosenAthlete.increaseDefence(10);
			chosenAthlete.increaseOffence(10);
		} 
		else if (chosenAthleteNum >= activeTeam.size() && chosenAthleteNum < (reserves.size() + activeTeam.size())) {
			chosenAthleteNum -= activeTeam.size();
			Athlete chosenAthlete = reserves.get(chosenAthleteNum);
			chosenAthlete.increaseDefence(10);
			chosenAthlete.increaseOffence(10);
		}
	}
}
	

 
	
	

		
	
	 
		
		

