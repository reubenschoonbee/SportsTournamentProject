package sportstournament.ui;



import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.GameEnvironment;
import sportstournament.main.Item;
import sportstournament.main.Stadium;
import sportstournament.main.Team;



/**
 * command line interface for the game environment
 * can take input from console and perform actions
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public class CommandLine {
	
	/**
	 * new game environment class for this game
	 */
	private GameEnvironment game;
	/**
	 * scanner to read users input from the terminal
	 */
	private final Scanner scanner;
	
	/**
	* default constructor which creates an instance of the command line interface with a scanner
	*/
	public CommandLine() {

		this.scanner = new Scanner(System.in);
		
	}
	/**
	 * prompts the users name and athlete selection and returns the information to the game environment
	 * @param game The instance of GameEnvironment used to keep track of the game
	 */
	public void SetUp(GameEnvironment game) {
		
		this.game = game;
		final String name = getName();
		final ArrayList<Athlete> team = draftAthletes(game);
		final int numWeeks = getNumWeeks();
;		game.finishSetup(name, team, numWeeks, "Easy");
	
	}
	/**
	 * Returns the number of weeks the game shall be played for
	 * 
	 * @return the number of weeks the game shall be played for
	 */
	public int getNumWeeks() {
		
		System.out.println("Please enter the number of weeks you want the game to be played for (between 5 and 15)");
		while(true) {
			try {
				int numWeeks = scanner.nextInt();
				if (numWeeks <= 15 && numWeeks >= 5) {
					return numWeeks;
				}
				else {
					System.out.println("Please select a number between 5 and 15");
				}
			} catch (Exception error) {
				System.out.println("Please select a number between 5 and 15");
			}
		}
	}
	
	/**
	 * prompts player to enter a name for the team and checks it is valid
	 * @return The team name
	 */
	public String getName() {
		System.out.println("Enter team name (must be one word)");
		
		while (true) {
			String name = scanner.nextLine();
			if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
				System.out.println("Name cannot contain special characters");
			}
			else if (name.length() > 15 | name.length() < 3) {
				System.out.println("Name must be between 3 and 15 characters");
			}
			else {
				return name.toString();
			}
		}	
	}
	
	/**
	 * prompts player to select athletes and returns their selected team
	 * @param game The instance of GameEnvironment used to keep track of the game
	 * @return the drafted athletes
	 */
	public ArrayList<Athlete> draftAthletes(GameEnvironment game) {
		
		ArrayList<Athlete> draft = new ArrayList<Athlete>(game.getDraft());
		ArrayList<Athlete> selected = new ArrayList<Athlete>();
		
		
		while (selected.size() < 4 ) {
				
			printDraftOptions(draft);
			System.out.println("Please enter the number next to the athlete you want to draft");	
			int selection = getIntegerInput(draft.size());
			selected.add(draft.get(selection - 1));
			draft.remove(selection - 1);
					
		}
		return selected;
	
	}
	
	/**
	 * takes the list of available players to draft and displays the number to select them and their stats
	 * @param draft the list of available athletes to draft
	 */
	public void printDraftOptions(ArrayList<Athlete> draft) {
		
		int i = 1;
		for (Athlete athlete: draft) {
			System.out.println(i+": "+athlete);
			i += 1;
		}
		
	}
	/**
	 * starts the main game run loop where the user selects the action they want to perform
	 * terminates when no more weeks are remaining
	 */
	public void start() {
		
		while (game.getWeeksRemaining() > 0) {
			
			printMainOptions();
			int selection = getIntegerInput(4);
			handleMainOption(selection);
			
		}
		System.out.println("Game over");
	}
	
	/**
	 * prints out the options available to the user from the main screen
	 */
	public void printMainOptions() {
		
		System.out.println("1: Go to club");
		System.out.println("2: Go to stadium");
		System.out.println("3: Go to market");
		System.out.println("4: Take bye");
	}
	
	/**
	 * handles user selection from the main screen
	 * @param selection the chosen option
	 */
	public void handleMainOption(int selection) {
		
		switch (selection) {
			default:
				throw new IllegalArgumentException("Unexpected value");
			case(1):
				printClubOptions();
				int new_selection = getIntegerInput(5);
				game.handleClubOptions(new_selection); 
				break;
			case(2):
				StadiumOptions();
				break;
			case(3):
				MarketOptions();
				break;
			case(4):
				game.speciallyTrainAthlete();
				game.takeBye();
				break;
			}
		}	
	

	/**
	 * a helper method that takes an integer which represents the number of options the user can select
	 * it then prompts the user for input and checks if the input is valid
	 * then returns the integer
	 * @param numOptions the number of options
	 * @return an integer representing the selected option
	 */
	public int getIntegerInput(int numOptions) {
		
		System.out.println("Please enter a number between 1 and "+numOptions);
		
		while (true) {
			try {
				int selection = scanner.nextInt();
				if (selection >= 1 && selection <= numOptions) {
					return selection;
				}
				else {
					System.out.println("Please enter a number between 1 and "+numOptions);
				}
			} catch (InputMismatchException error) {
				System.out.println("Please enter a number between 1 and"+numOptions);
				
			} catch (NoSuchElementException error) {
				System.out.println("Please enter a number between 1 and"+numOptions);
			}
		}	
	}
	
	/**
	 * helper method that keeps prompting user for a specific input until it is received
	 * used for situations where there is only one options available
	 * @param optionNum the number of options
	 */
	public void singleIntegerInput(int optionNum) {
		
		while (true) {
			
			try {
				int selection = scanner.nextInt();
				if (selection == optionNum) {
					return;
				}
				else {
					System.out.println("Please enter the number "+optionNum+" to return");
				}
			} catch (InputMismatchException error) {
				System.out.println("Please enter the number "+optionNum+" to return");
				
			} catch (NoSuchElementException error) {
				System.out.println("Please enter the number "+optionNum+" to return");			}
		}
	}


	/**
	 * prints the initial options the user can select from the club screen
	 */
	public void printClubOptions() {
		
		System.out.println(game.getClub().viewName());
		System.out.println("1: View active team");
		System.out.println("2: View reserves");
		System.out.println("3: View inventory ");
		System.out.println("4: Substitute player");
		System.out.println("5: Return to main screen");

	}
	
	/**
	 * prints the options for the player to be subbed off
	 * @param club The user's Club
	 */
	public void printSubOffOptions(Club club) {
		
		System.out.println("Select the player you wish to take off the active team");
		int i = 1;
		for (Athlete athlete: club.viewActiveTeam()) {
			System.out.println(i+": "+athlete);
			i += 1;
		}
		System.out.println(i+": Return to club");

	}
		
		
	/**
	 * prints the options for the player to be subbed on
	 * @param club The user's Club
	 */
	public void printSubOnOptions(Club club) {
		System.out.println("Select the player you wish to put on your active team");
		int i = 1;
		for (Athlete reserve: club.viewReserves()) {
			System.out.println(i+": "+reserve);
			i += 1;
		}
		System.out.println(i+": Return to club");
	}
	
	/**
	 * prints the items in the inventory to be used plus an option to return to club
	 * @param club The user's Club
	 */
	public void printInventoryOptions(Club club) {
		System.out.println("Please select the item you wish to use");
		int i = 0;
		for (Item item: club.viewItems()) {
			System.out.println(i+": "+item);
			i += 1;
		}
		System.out.println(i+": Return to club");
	}
	
	/**
	 * displays all the athletes in the club including the active team and the reserves
	 * used so user can select an athlete to use an item on
	 * @param club The user's Club
	 */
	public void printWholeTeam(Club club) {
		
		ArrayList<Athlete> wholeTeam = new ArrayList<Athlete>(club.viewActiveTeam());
		wholeTeam.addAll(club.viewReserves());
		System.out.println("Please select the athlete you wish to use the item on");
		int i = 0;
		for (Athlete athlete: wholeTeam) {
			System.out.println(i+": "+athlete);
		}
		System.out.println(i+": Return to club");
			
	}
		
	
	/**
	 * Prints the options the user can select if they go to the Stadium.
	 */
	public void StadiumOptions() {
		while(true) {
	
			Stadium stadium = game.getStadium();
			ArrayList<Team> matches = stadium.getMatches();
			int m = 1;
			for(Team match : matches ) {
				System.out.println("("+m+") Match "+m+":\n" + match);
				m += 1;
			}
			System.out.println("("+m+") Return to Main Menu");
			int matchNum = getIntegerInput(matches.size() + 1);
			if (matchNum == m) {
				return;
			}
			else {
				String result = stadium.startMatch(game.getClub(), matches.get(matchNum - 1));
				if(result == "Cannot start match! Must have at least one healthy athlete") {
					System.out.println(result);
				}
				else {
				System.out.println("The result of the match is: " + result);
				System.out.println("The updated status of your team is\n" + game.getClub());
				System.out.println("Your total points are now: " + game.getPoints());
				System.out.println("Your total money is now: " + game.getMoneyAmount());
				}
		
			}
		}
	}
	/**
	 * Handles the options in market
	 */
	public void MarketOptions(){
		
		printMarketOptions();
		int selection = getIntegerInput(5);
		
		switch(selection) {
		
		default:
			throw new IllegalArgumentException("Unexpected value");
		case(1):
			System.out.println("1: Draft back reserve");
			System.out.println("3: Return to market");
			int choice = getIntegerInput(2);
			switch(choice) {
				
			case(1):
				System.out.println("Please select the reserve you wish to draft back to the market");
				int i = 1;
				for (Athlete athlete: game.getClub().viewReserves()) {
					System.out.println(i+": "+athlete);
					i += 1;
				}
				System.out.println(i+": Return to market");
				int athleteIndex = getIntegerInput(game.getClub().viewReserves().size() + 1);
				if (athleteIndex == game.getClub().viewReserves().size() + 1) {
					returnToMarket();
					break;
				}
				game.getMarket().sellReserve(game.getClub().viewReserves().get(athleteIndex), game.getClub());
				break;
			case(2):
				returnToMarket();
				break;
			}
		}
		
		
	}
	/**
	 * prints the market options
	 */
	public void printMarketOptions() {
		
		System.out.println("Current balance: $"+game.getMoneyAmount());
		System.out.println("1: Draft athlete back to market");
		System.out.println("2: Draft item back to the market");
		System.out.println("3: View available athletes for purchase");
		System.out.println("4: View available items for purchase");
		System.out.println("5: Return to main screen");
	}
	
	/**
	 * Returns to the main market screen
	 */
	public void returnToMarket() {
		
	}
}


