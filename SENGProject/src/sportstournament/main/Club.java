package sportstournament.main;


import java.util.ArrayList;
import java.util.Collections;


/**
 * This class implements a club which is a subclass of Team.
 * The club contains methods which affect the active team, the reserves and the inventory. 
 * 
 * @author Reuben Schoonbee and Isaac Steele
 */
public class Club extends Team{
	

	/**
	 * List of reserves
	 */
	private ArrayList<Athlete> reserves;
	/**
	 * List of items available for use
	 */
	private ArrayList<Item> inventory;
	/**
	 * A boolean that is true only when the reserves are full
	 * (when a player cannot be added to the team without replacing another)
	 */
	private boolean teamFull;

	/**
	 * A constructor for Club which is used when there are no reserves.
	 * 
	 * @param name The chosen name for the Club
	 * @param activeTeam A list of 4 Athletes which is the active team of the Club.
	 */
	public Club(String name, ArrayList<Athlete> activeTeam) {
		super(name, activeTeam);
		inventory = new ArrayList<Item>();
		reserves = new ArrayList<Athlete>();
	}
	/**
	 * A constructor for Club which contains reserves.
	 * 
	 * @param name The chosen name for the Club
	 * @param activeTeam A list of 4 Athletes which is the active team of the Club.
	 * @param reserves A list of up to 5 Athletes which is the reserves of the Club.
	 */
	public Club(String name, ArrayList<Athlete> activeTeam, ArrayList<Athlete> reserves) {
		super(name, activeTeam);
		inventory = new ArrayList<Item>();
		this.reserves = reserves;
	}
	/**
	 * Returns a boolean that is true if the reserves is full.
	 * 
	 * @return boolean of whether reserves is full or not.
	 */
	public boolean isTeamFull() {
		return teamFull;
	}
	
	/**
	 * Sets a boolean depending on whether the reserves is full or not.
	 * 
	 * @param teamFull true or false, depending on whether the reserves is full or not.
	 */
	public void setTeamFull(boolean teamFull) {
		this.teamFull = teamFull;
	}
	/**
	 * Returns a list of Athletes on the bench.
	 * 
	 * @return A list of Athletes in the reserves.
	 */
	public ArrayList<Athlete> viewReserves() {
		return reserves;
	}
	
	/**
	 * Returns a list of Items in inventory.
	 * 
	 * @return A list of Items in inventory.
	 */
	public ArrayList<Item> viewItems(){
		return inventory;
	}
	
	/**
	 * Boosts the attribute of a given Athlete with the property and value of a given Item then removes the Item from the inventory.
	 * @param item The Item to be used
	 * @param athlete The Athlete to use the Item on
	 */
	public void useItem(Item item, Athlete athlete) {
		
		
		switch(item) {
		case PROTEIN_SHAKE:
			athlete.increaseDefence(item.getStatBoost());
			break;
		case ENERGY_DRINK:
			athlete.increaseStamina(item.getStatBoost());
			break;
		case KNEE_SLEEVE:
			athlete.increaseOffence(item.getStatBoost());
			break;
		}
		
		inventory.remove(item);
	}
	
	/**
	 * Adds an Item to the inventory
	 * 
	 * @param item The Item to be added to inventory
	 */
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	/**
	 * Removes given Item from inventory
	 * 
	 * @param item The Item to be removed from inventory
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}

	/**
	 * Sets the inventory to the supplied list of items.
	 * 
	 * @param inventory The list of Items to set the inventory to.
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Takes an Athlete who is in the reserves and an Athlete from the active team and adds the reserve to the active team and the active Athlete to the reserves.
	 * @param player Athlete to be put on the active team.
	 * @param sub Athlete to be benched.
	 */
	public void subAthlete(Athlete player, Athlete sub){
		
		activeTeam.remove(sub);
		activeTeam.add(player);
		reserves.remove(player);
		reserves.add(sub);
	}
	
	/**
	 * Takes an Athlete and puts them on the reserves. 
	 * @param newPlayer the Athlete to be added.
	 */
	public void addNewAthlete(Athlete newPlayer) {
		if(reserves.size() == 5) {
			setTeamFull(true);
			throw new ArrayIndexOutOfBoundsException("Maximum size of reserves is 5");
		}
		else {
			reserves.add(newPlayer);
			if(reserves.size() == 5) {
				setTeamFull(true); 
			}
		} 	
	}	
	/**
	 * removes the given Athlete from the reserves.
	 * 
	 * @param athlete The Athlete to be removed from the reserves.
	 */
	public void removeReserve(Athlete athlete) {
		
		reserves.remove(athlete);
		setTeamFull(false);
			
	}
	
	/**
	 * takes two indices of athletes in active team and swaps their position in the list
	 * this allows the user to change the athletes positions
	 * @param index1 The index of the first athlete
	 * @param index2 The index of the second athlete
	 */
	public void swapPositions(int index1, int index2) {
		Collections.swap(activeTeam, index1, index2);
	}
	
}
		
		
