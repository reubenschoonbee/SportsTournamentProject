package sportstournament.main;

import java.util.ArrayList;

/**
 * This class implements a Market where Athlete's and Item's can be purchased and sold.
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class Market {
	
	/**
	 * List of available Items for purchase
	 */
	private ArrayList<Item> availableItems = new ArrayList<Item>();
	/**
	 * List of available Athletes for purchase.
	 */
	private ArrayList<Athlete> freeAgents = new ArrayList<Athlete>();
	/**
	 * The game environment. 
	 */
	private GameEnvironment game;
	/**
	 * Default constructor for Market
	 * Adds 3 items to the market
	 * Creates a list of random athletes to be available for purchase.
	 * @param game The instance of GameEnvironment used to keep track of the game
	 */
	public Market(GameEnvironment game) {
		this.game = game;
		for (Item item : Item.values()) {
			availableItems.add(item);
		}
		
		for (int i = 0; i < 5; i++ ) {
			freeAgents.add(Athlete.randomAthleteGenerator());
		}
		

	}
	/**
	 * View the available funds
	 * 
	 * @return The money in the current state of the game.
	 */
	public int getMoney() {
		return game.getMoneyAmount();
	}
	
	/**
	 * Returns a list of Items available for purchase.
	 * @return  A list of Items available for purchase.
	 */
	public ArrayList<Item> viewAvailableItems() {
		return availableItems;
	}
	/**
	 * Returns a list of available Athletes in the Market.
	 * 
	 * @return A list of available Athletes in the Market.
	 */
	public ArrayList<Athlete> getFreeAgents() {
		return freeAgents;
	}
	
	/**
	 * Adds a new Athlete to be available for purchase in the Market.
	 * 
	 * @param athlete The Athlete to be added.
	 */
	public void addFreeAgent(Athlete athlete) {
		freeAgents.add(athlete);
	}
	/**
	 * Sets the list of available Athletes in the market.
	 * 
	 * @param freeAgents a list of Athletes to be made available for purchase.
	 */
	public void setFreeAgents(ArrayList<Athlete> freeAgents) {
		this.freeAgents = freeAgents;
	}
	
	/**
	 * Adds an Athlete from the Market to the Club's reserves and removes the Athlete from the Market.
	 * 
	 * @param newAthlete The Athlete to be bought and added to the reserves
	 * @param club The instance of Club that the Athlete will join 
	 * 
	 * @return Returns a String that is either an error message because there's insufficient funds or a confirmation of purchase.
	 */
	public String buyReserve(Athlete newAthlete, Club club) {
		try {
			if (club.viewReserves().size() < 5) {
				game.decreaseMoney(newAthlete.getPrice());
				club.addNewAthlete(newAthlete);
				freeAgents.remove(newAthlete);
			} else {
				return "Maximum size of reserves is 5";
			}
		
			if (freeAgents.size() == 2) {
				freeAgents.add(Athlete.randomAthleteGenerator());
			}
			return newAthlete.getName() + " purchased.";
		}
		catch(ArithmeticException error) {
			return error.getMessage();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return e.getMessage();
		}
		
	}
	/**
	 * Buys a new Athlete from the Market, puts them on the reserves and then substitutes the chosen Athlete in the active team with the new Athlete.
	 * @param newAthlete The Athlete purchased from the Market
	 * @param subAthlete The Athlete to be subbed off the active team and put on the reserves.
	 * @param club The instance of Club that the Athlete will join.
	 * 
	 *  @return Returns a String that is either an error message because there's insufficient funds or a confirmation of purchase.
	 */
	public String buyStarter(Athlete newAthlete, Athlete subAthlete, Club club) {
		try {
			if (club.viewReserves().size() < 5) {
				game.decreaseMoney(newAthlete.getPrice());
				club.addNewAthlete(newAthlete);
				club.subAthlete(newAthlete, subAthlete);
				freeAgents.remove(newAthlete);
			} else {
				return "Maximum size of reserves is 5";
			}
			
			
			if (freeAgents.size() == 2) {
				freeAgents.add(Athlete.randomAthleteGenerator());
			}
			return newAthlete.getName() + " purchased.";
		}
		catch (ArithmeticException error) {
			return error.getMessage();
			
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Buys an Item from the Market.
	 * 
	 * @param item The Item to be purchased
	 * @param club The instance of Club that the Item will be added to
	 * 
	 * @return Returns a String that is either an error message because there's insufficient funds or a confirmation of purchase.
	 */
	public String buyItem(Item item, Club club) {
		try {
		game.decreaseMoney(item.getPrice());
		club.addItem(item);
		return item.name() + " purchased.";
		}
		catch(ArithmeticException error) {
			return error.getMessage();
		}
	}
	
	/**
	 * Sells an Item back to the market for half its price.
	 * 
	 * @param item The Item to be sold
	 * @param club The instance of Club that the item belongs to.
	 */
	public void sellItem(Item item, Club club) {
		club.removeItem(item);
		game.updateMoney(item.getPrice());
	}
	/**
	 * Sell a reserve back to the Market for half its price.
	 * 
	 * @param reserve The reserve to be sold
	 * @param club The instance of Club the reserve belongs to
	 * 
	 */
	public void sellReserve(Athlete reserve, Club club) {
		
		game.updateMoney((reserve.getPrice() / 2));
		club.removeReserve(reserve);
	}
	
	/**
	 * Sells a starter back to the Market for half its price.
	 * @param starter The starter to be sold back to the Market
	 * @param replacement The reserve that replaces the starter in the active team.
	 * @param club The instance of Club that the starter and replacement belong to.
	 */
	public void sellStarter(Athlete starter, Athlete replacement,Club club) {
		game.updateMoney((starter.getPrice() / 2));
		club.subAthlete(replacement, starter);
		club.removeReserve(starter);
	}


	
	

}
