package sportstournament.main;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements a RandomEvent.
 * While the team is resting (taking a bye) there is a chance that random events can occur.
 * The chance they occur depend on the difficulty.
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class RandomEvent {
	/**
	 * Instantiates a Club Object.
	 */
	private Club team;
	/**
	 * Instantiates a Random Object
	 */
	private Random rng = new Random();
	/**
	 * Constructor for Random
	 * 
	 * @param team Allows for access to Club methods 
	 */
	public RandomEvent(Club team) {
		this.team = team;
	}
	/**
	 * The athlete that quits
	 */
	private Athlete quitter;
	/**
	 * The athlete that joins
	 */
	private Athlete booster;
	/**
	 * The athlete that joined
	 */
	private Athlete joiner;
	/**
	 * Creates a random number from 0-100 which will be used to determine if a random athlete's stats are increased.
	 */
	private int statNumber = rng.nextInt(100);
	/**
	 * Creates a random number from 0-100 which will be used to determine if a random athlete quits
	 */
	private int quitNumber = rng.nextInt(100);
	/**
	 * Creates a random number from 0-100 which will be used to determine if a random athlete joins.
	 */
	private int joinNumber = rng.nextInt(100);
	/**
	 * Gets the athlete that quits
	 * 
	 * @return The athlete that quits
	 */
	public Athlete getQuitter() {
		return quitter;
	}
	/**
	 * Gets the athlete whose stats are boosted
	 * 
	 * @return The athlete whose stats are boosted
	 */
	public Athlete getBooster() {
		return booster;
	}
	/**
	 * Gets the athlete that joined
	 * 
	 * @return The athlete that joined
	 */
	public Athlete getJoiner() {
		return joiner;
	}
	
	/**
	 * Returns a random number used to determine if a random athlete's stats are increased.
	 * 
	 * @return A random number used to determine if a random athlete's stats are increased.
	 */
	public int getStatNumber() {
		return statNumber;
	}
	/**
	 * Returns a random number used to determine if a random athlete quits
	 * 
	 * @return A random number used to determine if a random athlete quits
	 */
	public int getQuitNumber() {
		return quitNumber;
	}
	/**
	 * Returns a random number used to determine if a random athlete joins.
	 * 
	 * @return A random number used to determine if a random athlete joins.
	 */
	public int getJoinNumber() {
		return joinNumber;
	}
	/**
	 * Sets the number used to determine if a random athlete's stats are increased.
	 * 
	 * @param num The number used to determine if a random athlete's stats are increased.
	 */
	public void setStatNumber(int num) {
		statNumber = num;
	}
	/**
	 * Sets the number used to determine if a random athlete joins
	 * 
	 * @param num The number used to determine if a random athlete joins
	 */
	public void setJoinNumber(int num) {
		joinNumber = num;
	}
	/**
	 * Sets the number used to determine if a random athlete quits.
	 * 
	 * @param num The number used to determine if a random athlete quits.
	 */
	public void setQuitNumber(int num) {
		quitNumber = num;
	}
	/**
	 * Randomly chooses an athlete and boosts their stats.
	 */
	public void randomAthleteBoost() {
		int randomInt = rng.nextInt(4);
		ArrayList<Athlete> activeTeam = team.viewActiveTeam();
		booster = activeTeam.get(randomInt);
		booster.increaseDefence(10);
		booster.increaseOffence(10);
	}
	/**
	 * Returns an integer which represents the chance an Athlete quits. 
	 * The chance is higher if the athlete is injured.
	 * 
	 * @return The chance that an athlete quits
	 */
	public int getQuitChance() {
		int randomInt = rng.nextInt(4);
		ArrayList<Athlete> reserves = team.viewReserves();
		ArrayList<Athlete> activeTeam = team.viewActiveTeam();
		quitter = activeTeam.get(randomInt);
		if (reserves.size()==0) {
			return 0;
		}
		else if (quitter.getInjuryStatus() == true) {
			int chance = 20;
			return chance;
		} else {
			int chance = 10;
			return chance;
		}
	}
	/**
	 * Removes a random Athlete from the team. The Athlete has more of a chance to get removed if they are injured.
	 */
	public void randomAthleteQuits() {
		ArrayList<Athlete> reserves = team.viewReserves();
		int randomSub = rng.nextInt(reserves.size());
		Athlete replacement = reserves.get(randomSub);
		team.subAthlete(replacement, quitter);
		team.removeReserve(quitter);
	}
	
	/**
	 * Returns an integer representing the chance a random athlete joins. 
	 * The chances increase depending on the number of free slots in the reserves.
	 * 
	 * @return The chance that an athlete joins
	 */
	public int getAthleteJoinChance() {
		ArrayList<Athlete> reserves = team.viewReserves();
		if(reserves.size() == 0) {
			return 30;
		}
		else if(reserves.size() == 1) {
			return 25;
		}
		else if(reserves.size() == 2) {
			return 20;
		}
		else if(reserves.size() == 3) {
			return 15;
		}
		else if(reserves.size() == 4) {
			return 10;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * A random Athlete joins the reserves of the Club
	 */
	public void randomAthleteJoins() {
		Athlete randomAthlete = Athlete.randomAthleteGenerator();
		joiner = randomAthlete;
		team.addNewAthlete(randomAthlete);
	}
	/**
	 * Each random event has a chance to occur, but only one can happen each week. A string is returned reflecting what random event occurred.
	 * 
	 * @param difficulty The difficulty of the game. It affects the chances of a random event to occur
	 * @return A string reflecting what random event occurred.
	 */
	public String doRandomEvent(String difficulty) {
		String randomEventOccurrence = "None";
		if (difficulty == "Hard") {
			if(getStatNumber() < 10) {
				randomAthleteBoost();
				randomEventOccurrence = "Athlete Boost";
			}
		
			else if(getQuitNumber() < getQuitChance()) {
				randomAthleteQuits();
				randomEventOccurrence = "Athlete Quits";
			}
		
			else if(getJoinNumber() < (getAthleteJoinChance() - 5)) {
				randomAthleteJoins();
				randomEventOccurrence = "Athlete Joins";
			}
		}
	
		else if(difficulty == "Easy") {
			if(getStatNumber() < 20) {
				randomAthleteBoost();
				randomEventOccurrence = "Athlete Boost";
			}
			
			else if(getQuitNumber() < (getQuitChance()) - 5) {
				randomAthleteQuits();
				randomEventOccurrence = "Athlete Quits";
			}
			
			else if(getJoinNumber() < (getAthleteJoinChance())) {
				randomAthleteJoins();
				randomEventOccurrence = "Athlete Joins";
			}
		}
		return randomEventOccurrence;
	
	}
	
}
