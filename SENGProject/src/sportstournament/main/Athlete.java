package sportstournament.main;


import java.util.Random;


/**
 * This class implements an Athlete which has different statistics (offence, defence, stamina), a nickname, an injury status, and a price.
 * The price of an athlete is determined by the average of their stats and scaled down by a factor of 10.
 * An athlete becomes injured when their stamina reaches 0 and can no longer perform in matches.
 * The position they play is determined by their index in the active team.
 * This class also contains a random Athlete generator which gives an athlete a random name and stats. 

 * @author Isaac Steele and Reuben Schoonbee
 *
 */ 


public class Athlete implements Purchasable {
	
	
	/**
	 * The name of the Athlete.
	 */
	private String athleteName;
	/**
	/**
	 * The injury status of the Athlete
	 */
	private boolean injuryStatus = false;
	/**
	 * The offence statistic of the Athlete
	 */
	private int offenceStat;
	/**
	 * The defence statistic of the Athlete
	 */
	private int defenceStat;
	/**
	 * The stamina of the Athlete. The default value is 100
	 */
	private int athleteStamina = 100;
	/** 
	 * The price of the Athlete if they are available in the market
	 */
	private int athletePrice;
	
	/**
	 * The default constructor for Athlete. The default name is set to 'Larry Costa'.
	 */
	public Athlete() {
		athleteName = new String("Larry Costa");
	}
	/**
	 * The main constructor for Athlete.
	 * 
	 * @param tempName The given name for the Athlete.and can have 
	 * @param tempOffenceStat The given offence stat of the Athlete.
	 * @param tempDefenceStat The given defence stat of the Athlete.
	 * @param tempPrice The given price of the Athlete.
	 */
	public Athlete(String tempName, int tempOffenceStat, int tempDefenceStat, int tempPrice) {
		athleteName = tempName;
		offenceStat = tempOffenceStat;
		defenceStat = tempDefenceStat;
		athletePrice = tempPrice;
		
	}
	
	/**
	 * Returns the name of the Athlete.
	 * 
	 * @return Either the default name or the nickname given by the Player.
	 */
	public String getName() {
		return athleteName;
	}
	
	/**
	 * The player can choose a nickname for the Athlete.
	 * 
	 * @param nickname The chosen nickname for the Athlete.
	 */
	public void setName(String nickname) {
		athleteName = nickname;
	}
	/**
	 * Returns the injury status of the Athlete.
	 * 
	 * @return The injury status of the Athlete.
	 */
	public boolean getInjuryStatus() {
		return injuryStatus;
	}
	
	/** Sets the injury status of the Athlete
	 * 
	 * @param status True if Athlete is injured. False is Athlete is healthy.
	 */
	public void setInjuryStatus(boolean status) {
		injuryStatus = status;
	}
	
	/** 
	 * Returns the Athlete's offence stat.and can have 
	 * 
	 * @return The offensive statistic of the Athlete.
	 */
	public int getOffenceStat() {
		return offenceStat;
	}
	
	/**
	 * Sets the value for the offence stat of the Athlete.
	 * 
	 * @param offence The value for the offence stat of the Athlete.
	 */
	public void setOffenceStat(int offence) {
		offenceStat = offence;
	}
	
	/**
	 * Returns the Athlete's defence stat.
	 * 
	 * @return The defensive statistic of the Athlete.
	 */
	public int getDefenceStat() {
		return defenceStat;
	}
	
	/**
	 * Sets the value for the defence stat of the Athlete.
	 * 
	 * @param defence The value for the defence stat of the Athlete.
	 */
	public void setDefenceStat(int defence) {
		defenceStat = defence;
	}
	
	/**
	 * Returns the stamina of the Athlete.
	 * 
	 * @return The stamina of the Athlete.
	 */
	public int getStamina() {
		return athleteStamina;
	}
	
	/**
	 * Sets the stamina of the Athlete.
	 * 
	 * @param stamina The value for the stamina of the Athlete.
	 */
	public void setStamina(int stamina) {
		athleteStamina = stamina;
		if (athleteStamina > 0 && injuryStatus == true) {
			injuryStatus = false;
		}
	}
	
	/**
	 * Returns the price of the Athlete if they are available in the market.
	 * 
	 * @return The price of the Athlete
	 */
	public int getPrice() {
		return athletePrice; 
	}
	
	/**
	 * Sets the price of the Athlete if they are available in the market.
	 * 
	 * @param price The price of the Athlete.
	 */
	public void setPrice(int price) {
		athletePrice = price;
	}
	
	/** and can have 
	 * Increases the offence stat of the Athlete.
	 * 
	 * @param offenceBoost The value to increase the offence stat by.
	 */
	public void increaseOffence(int offenceBoost) {
		offenceStat += offenceBoost;
		if (offenceStat > 100) {
			offenceStat = 100;
		}
	}
	
	/**
	 * Increases the defence stat of the Athlete.
	 * 
	 * @param defenceBoost The value to increase the defence stat by. 
	 */
	public void increaseDefence(int defenceBoost) {
		defenceStat += defenceBoost;
		if (defenceStat > 100) {
			defenceStat = 100;
		}
	}
	
	/**
	 * Increases the stamina of the Athlete.
	 * 
	 * @param staminaBoost The value to increase the stamina of the Athlete by.
	 */
	public void increaseStamina(int staminaBoost) {
		athleteStamina += staminaBoost;
		if (athleteStamina > 100) {
			athleteStamina = 100;
		}
		if (athleteStamina > 0 && injuryStatus == true) {
			injuryStatus = false;
		}
	}
	
	/**
	 * Decreases the stamina of the Athlete.
	 * 
	 * @param staminaReduction The value to decrease the stamina of the Athlete by.
	 */
	public void decreaseStamina(int staminaReduction) {
		athleteStamina -= staminaReduction;
		if (athleteStamina <= 0) {
			athleteStamina = 0;
			injuryStatus = true;
		}
	}
	
	/**
	 * Restores the stamina of the Athlete.
	 */
	public void restoreStamina() {
		athleteStamina = 100;
		injuryStatus = false;
	}
	/**
	 * To String method for the Athlete Class
	 * 
	 * @return Returns the representation of an athlete in the form of a string.
	*/
	@Override
	public String toString() {
		return "Name: " + athleteName + ", " + "Offence: " + offenceStat + ", " + "Defence: " + defenceStat + ", " + "Stamina: " + athleteStamina + ", " + "Price: " + athletePrice + ", Injured: " + injuryStatus;
	}
	
	/**
	 * Random Athlete Generator
	 * 
	 * @return Returns a random athlete with their stats between 50 and 100 and a name randomly selected from a default list of names.
	 */
	public static Athlete randomAthleteGenerator() {
		Random rng = new Random();
		int randomDefence = rng.nextInt(51) + 50;
		int randomOffence = rng.nextInt(51) + 50;
		int price = ((randomDefence + randomOffence)) / 20;
		String[] firstNames = {"John", "Matt", "Henry", "David", "Harry", "Joe", "Will", "Jack", "Luke", "Zach", "Ben", "James", "Adam", "Eric"};
		String[] lastNames = {"Smith", "Wilson", "Lee", "Turner", "Adams", "Baker", "Young", "Thompson", "Clark", "Garcia", "Rodriguez", "Martinez", "Patel", "Miller"};
		String randomFirstName = firstNames[rng.nextInt(14)];
		String randomLastName = lastNames[rng.nextInt(14)];
		String randomName = randomFirstName + " " + randomLastName;
		Athlete randomAthlete =  new Athlete(randomName, randomDefence, randomOffence, price);
		return randomAthlete;
		
	}
	/**
	 * Random Athlete Generator based on the number of weeks remaining
	 * 
	 * @param randomDefence A randomly generated defence stat based on the weeks remaining and total weeks
	 * @param randomOffence A randomly generated offence stat based on the weeks remaining and total weeks
	 * @return Returns a random athlete with stats generated based on the total number of weeks and weeks remaining. This method is called through the Team class.
	 */
	public static Athlete randomAthleteGenerator(int randomDefence, int randomOffence ) {
		Random rng = new Random();
		int price = ((randomDefence + randomOffence)) / 20;
		String[] firstNames = {"John", "Matt", "Henry", "David", "Harry", "Joe", "Will", "Jack", "Luke", "Zach", "Ben", "James", "Adam", "Eric"};
		String[] lastNames = {"Smith", "Wilson", "Lee", "Turner", "Adams", "Baker", "Young", "Thompson", "Clark", "Garcia", "Rodriguez", "Martinez", "Patel", "Miller"};
		String randomFirstName = firstNames[rng.nextInt(14)];
		String randomLastName = lastNames[rng.nextInt(14)];
		String randomName = randomFirstName + " " + randomLastName;
		Athlete randomAthlete =  new Athlete(randomName, randomOffence, randomDefence, price);
		return randomAthlete;
		
	}
	
	
	
	
	
	
}
