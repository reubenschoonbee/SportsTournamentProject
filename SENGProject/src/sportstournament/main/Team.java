package sportstournament.main;

import java.util.ArrayList;
import java.util.Random;



/**
 * This class implements a Team which has a name, an active team that contains 4 players.
 * It is also the parent class of Club
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class Team {
	/**
	 * A GameEnvironment object which holds the current state of the application.
	 */
	protected GameEnvironment game;
	/**
	 * The team name
	 */
	protected String name;
	/**
	 * A list of Athletes which make up the active team.
	 */
	protected ArrayList<Athlete> activeTeam;
	/**
	 * Default constructor for Team
	 * 
	 * @param name The team name
	 * @param activeTeam A list of Athletes which make up the active team
	 * @param game The current state of the game
	 */
	public Team(String name, ArrayList<Athlete> activeTeam, GameEnvironment game) {
		setName(name);
		this.activeTeam = activeTeam;
		this.game = game;
	}
	/** 
	 * Constructor for Team without game environment.
	 * 
	 * @param name The team name
	 * @param activeTeam A list of Athletes which make up the active team
	 */
	public Team(String name, ArrayList<Athlete> activeTeam) {
		setName(name);
		this.activeTeam = activeTeam;
	}
	/**
	 * Team Constructor which is used when the active team is not provided.
	 * 
	 * @param game The current state of the game
	 */
	public Team(GameEnvironment game) {
		this.game = game;
	}
	/**
	 * Returns the team name
	 * @return The name of team
	 */
	public String viewName() {
		return name;
	}
	
	/**
	 * Sets the team name
	 * @param name The name of the team
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns a list of Athletes on the active team
	 * @return a list of Athletes on the active team
	 */
	public ArrayList<Athlete> viewActiveTeam() {
		return activeTeam;
	}
	/** 
	 * To string method for team
	 * 
	 * @return A string that contains the team name, and all the athletes and their positions
	 */
	public String toString() {
		return "Team Name: " + name + "\n\nDefenders:\n" + activeTeam.get(0).toString() + "\n" + activeTeam.get(1).toString() + "\n\nAttackers:\n" + activeTeam.get(2).toString() + "\n" + activeTeam.get(3).toString();
	}
	/**
	 * Generates a random Team based on the weeks remaining and the total weeks
	 * 
	 * @return A randomly generated Team
	 */
	public Team randomTeamGenerator() {
		int defenceStat;
		int offenceStat;
		ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
		Random rng = new Random();
		String[] teamNames = {"Eagles", "Hornets", "Crusaders", "Titans", "Sharks", "Wolverines", "Hurricanes", "Hawks", "Snakes", "Serpents", "Cheetahs", "Panthers", "Lions", "Tigers"};
		String randomName = teamNames[rng.nextInt(teamNames.length)];
		int weeksRemaining = game.getWeeksRemaining();
		int totalWeeks = game.getTotalWeeks();
		// As the weeks increases the opponents should be harder
		for(int i = 0; i < 4; i++ ) {
			if (totalWeeks <= 15 && totalWeeks >= 12) {
				if (weeksRemaining < 15 && weeksRemaining > 10) {
					defenceStat = rng.nextInt(16) + 50;
					offenceStat = rng.nextInt(16) + 50;
				}
				else if (weeksRemaining < 11 && weeksRemaining > 5) {
					defenceStat = rng.nextInt(16) + 60;
					offenceStat = rng.nextInt(16) + 60;
				}
				else {
					defenceStat = rng.nextInt(26) + 75;
					offenceStat = rng.nextInt(26) + 75;
				}
			}
			else if (totalWeeks <= 11 && totalWeeks >=9) {
				if (weeksRemaining < 11 && weeksRemaining > 7) {
					defenceStat = rng.nextInt(16) + 50;
					offenceStat = rng.nextInt(16) + 50;
				}
				else if (weeksRemaining < 8 && weeksRemaining > 3) {
					defenceStat = rng.nextInt(16) + 60;
					offenceStat = rng.nextInt(16) + 60;
				}
				else {
					defenceStat = rng.nextInt(26) + 75;
					offenceStat = rng.nextInt(26) + 75;
				}
			}
			else if (totalWeeks <= 8 && totalWeeks >=6) {
				if (weeksRemaining < 8 && weeksRemaining > 4) {
					defenceStat = rng.nextInt(16) + 50;
					offenceStat = rng.nextInt(16) + 50;
				}
				else if (weeksRemaining < 5 && weeksRemaining > 2) {
					defenceStat = rng.nextInt(16) + 60;
					offenceStat = rng.nextInt(16) + 60;
				}
				else {
					defenceStat = rng.nextInt(26) + 75;
					offenceStat = rng.nextInt(26) + 75;
				}
			} 
			else {
				if (weeksRemaining < 5 && weeksRemaining > 3) {
					defenceStat = rng.nextInt(16) + 50;
					offenceStat = rng.nextInt(16) + 50;
				}
				else if (weeksRemaining < 4 && weeksRemaining > 1) {
					defenceStat = rng.nextInt(16) + 60;
					offenceStat = rng.nextInt(16) + 60;
				}
				else {
					defenceStat = rng.nextInt(26) + 75;
					offenceStat = rng.nextInt(26) + 75;
				}
			}

			Athlete randomAthlete = Athlete.randomAthleteGenerator(defenceStat, offenceStat);
			athleteList.add(randomAthlete);
		}
		Team randomTeam = new Team(randomName, athleteList);
		return randomTeam;
	}
	
	
}



 
   


