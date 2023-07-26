package sportstournament.main;

import java.util.ArrayList;

/**
 * This class implements a Stadium where the player can see 3 matches and select which one they want to play
 * 
 * @author Isaac Steele and Reuben Schoonbee
 */
public class Stadium {
	/**
	 * A list of possible matches to play
	 */
	private ArrayList<Team> matches = new ArrayList<Team>();
	/**
	 *Instantiates a GameEnvironment object
	 */
	private GameEnvironment game;
	/**
	 * Instantiates a Team object
	 */
	private Team team;
	/**
	 * A default constructor for Stadium which creates a list of possible matches to play
	 * 
	 * @param game An instance of GameEnvironment which holds the current state of the game
	 */
	public Stadium(GameEnvironment game) {
		this.team = new Team(game);
		this.game = game;
		
		for (int i = 0; i <3; i ++) {
			Team match = team.randomTeamGenerator();
			matches.add(match);
		}
	}
	
	/** 
	 * Returns a list of Team objects which represent the matches available to play.
	 * 
	 * @return The matches available to play
	 */
	public ArrayList<Team> getMatches() {
		return matches;
	}

	/**
	 * Checks if all the players on the active team are injured
	 * 
	 * @param team The instance of Club that will be checked to see if the active team has any injuries.
	 * @return A boolean that is true if the whole team is injured and false if at least one player is healthy.
	 */
	public static boolean checkTeamInjuries(Club team) {
		
		for (Athlete athlete: team.viewActiveTeam()){
			if (athlete.getInjuryStatus() == false) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Checks if the team is eligible to play a match and if so, it starts the match and rewards the user depending on the result of the match.
	 * 
	 * @param club An instance of Club that contains the user's team.
	 * @param opponent The selected opponent that the user's team will verse
	 * 
	 * @return A string representing the result of the match.
	 */
	public String startMatch(Club club, Team opponent) {
		String difficulty = game.getDifficulty();
		if (checkTeamInjuries(club)) {
			return "Cannot start match! Must have at least one healthy athlete";
		}
		else {

			String result = Match.playMatch(club, opponent);
			switch(result) {
			case "Win":
				if(difficulty == "Easy") {
					game.updatePoints(3);
					game.updateMoney(10);
				} else if(difficulty == "Hard") {
					game.updatePoints(5);
					game.updateMoney(5);
				}
				break;
			case "Draw":
				if(difficulty == "Easy") {
					game.updatePoints(1);
					game.updateMoney(5);
				}
				else if(difficulty == "Hard") {
					game.updatePoints(2);
					game.updateMoney(3);
				}
				break;
			case "Loss":
				break;
			}
			return result; 
		}
	}

}
