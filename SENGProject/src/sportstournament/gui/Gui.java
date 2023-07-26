package sportstournament.gui;

import java.util.ArrayList;

import sportstournament.main.Athlete;
import sportstournament.main.GameEnvironment;

/**
 * This class controls the opening and closing of all screens.
 * 
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class Gui {
	
	/**
	 * The instance of GameEnvironment it interacts with
	 */
	private GameEnvironment game;
	/**
	 * The setup screen
	 */
	private SetupScreen setupScreen;
	/**
	 * The main screen
	 */
	private MainScreen mainScreen;
	
	/**
	 * The club screen
	 */
	private ClubScreen clubScreen;
	
	/**
	 * The stadium screen
	 */
	private StadiumScreen stadiumScreen;
	
	/**
	 * The market screen
	 */
	private MarketScreen marketScreen;
	
	/**
	 * The take bye screen
	 */
	private TakeByeScreen takeByeScreen;
	
	
	/**
	 * The team properties screen
	 */
	private TeamPropertiesScreen teamPropertiesScreen;
	
	/**
	 * The inventory screen
	 */
	private InventoryScreen inventoryScreen;
	
	/**
	 * The screen where athletes can be purchased or sold
	 */
	private BuyAthleteScreen buyAthleteScreen;
	
	/**
	 * The screen where items can be purchased or sold
	 */
	private BuyItemScreen buyItemScreen;
	/**
	 * The end game screen
	 */
	private EndGameScreen endGameScreen;
	/**
	 * Launches the setup window
	 * @param game the instance of GameEnvironment that this class interacts with
	 */
	public void setup(GameEnvironment game) {
		this.game = game;
		ArrayList<Athlete> draftAthletes = game.getDraft();
		setupScreen = new SetupScreen(game, draftAthletes);	
	}
	
	/**
	 * Instantiates all the windows after setup is complete.
	 * Launches the main screen.
	 */
	public void start() {
		setupScreen.closeWindow();
		mainScreen = new MainScreen(game, this);
		clubScreen = new ClubScreen(game, this);
		stadiumScreen = new StadiumScreen(game, this);
		marketScreen = new MarketScreen(game, this);
		buyAthleteScreen = new BuyAthleteScreen(game, this);
		buyItemScreen = new BuyItemScreen(game, this);
		takeByeScreen = new TakeByeScreen(game, this);
		teamPropertiesScreen = new TeamPropertiesScreen(game, this);
		inventoryScreen = new InventoryScreen(game, this);
		
		
		openMainScreen();
	}
	/**
	 * Closes take bye screen and updates the stadium and market screens.
	 */
	public void takeBye() {
		closeTakeByeScreen();
		stadiumScreen = new StadiumScreen(game, this);
		marketScreen = new MarketScreen(game, this);
		takeByeScreen = new TakeByeScreen(game, this);
		openMainScreen();
	}
	/**
	 * Ends the game by closing the main screen and opening the end game screen.
	 */
	public void endGame() {
		closeMainScreen();
		endGameScreen = new EndGameScreen(game, this);
		endGameScreen.open();
	}	
	
	/**
	 * Opens the main screen
	 */
	public void openMainScreen() {
		mainScreen = new MainScreen(game, this);
		mainScreen.open();
	}
	/**
	 * Closes the main screen
	 */
	public void closeMainScreen() {
		mainScreen.closeWindow();
	}
	
	/**
	 * Opens the club screen
	 */
	public void openClub() {
		clubScreen = new ClubScreen(game, this);
		clubScreen.open();
	}
	/**
	 * Closes the club screen
	 */
	public void closeClub() {
		clubScreen.closeWindow();
	}
	
	/**
	 * Opens the stadium screen
	 */
	public void OpenStadium() {
		stadiumScreen.open();
	}
	/**
	 * Closes the stadium screen
	 */
	public void closeStadium() {
		stadiumScreen.closeWindow();
	}
	
	/**
	 * Opens the market screen
	 */
	public void OpenMarket() {
		marketScreen = new MarketScreen(game, this);
		marketScreen.open();
	}
	/**
	 * Closes the market screen
	 */
	public void closeMarket() {		
		marketScreen.closeWindow();
	}
	
	/**
	 * Opens the take bye screen
	 */
	public void openTakeByeScreen() {
		takeByeScreen = new TakeByeScreen(game, this);
		takeByeScreen.open();
	}
	/**
	 * Closes the take bye screen
	 */
	public void closeTakeByeScreen() {
		takeByeScreen.closeWindow();
	}
	
	/**
	 * Opens the screen where the team can be viewed and substitutions can be made
	 */
	public void openTeamPropertiesScreen() {
		teamPropertiesScreen = new TeamPropertiesScreen(game, this);
		teamPropertiesScreen.open();
	}
	
	/**
	 * Opens the screen where the inventory can be viewed and items can be used
	 */
	public void openInventoryScreen() {
		inventoryScreen = new InventoryScreen(game, this);
		inventoryScreen.open();
	}
	/**
	 * Closes the screen where the team can be viewed and substitutions can be made
	 */
	public void closeTeamPropertiesScreen() {
		teamPropertiesScreen.closeWindow();
	}
	/**
	 * Closes the screen where the inventory can be viewed and items can be used
	 */
	public void closeInventoryScreen() {
		inventoryScreen.closeWindow();
	}
	/**
	 * Opens the screen where athletes can be purchased and sold
	 */
	public void openBuyAthleteScreen() {
		buyAthleteScreen = new BuyAthleteScreen(game, this);
		buyAthleteScreen.open();
	}
	/**
	 * Opens the screen where items can be purchased and sold
	 */
	public void openBuyItemScreen() {
		buyItemScreen = new BuyItemScreen(game, this);
		buyItemScreen.open();
	}
	/**
	 * Closes the screen where athletes can be purchased and sold
	 */
	public void closeBuyAthleteScreen() {
		buyAthleteScreen.closeWindow();
	}
	/**
	 * Closes the screen where items can be purchased and sold.
	 */
	public void closeBuyItemScreen() {
		buyItemScreen.closeWindow();
	}
}
