package sportstournament.main;

import java.util.ArrayList;


/**
 * This class implements a Match in which two teams compete based on the stats of the athletes in each team
 * 
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */

public class Match {
	
	/**
	 * Takes your team and an opposition team and goes through each team comparing each athletes stats. 
	 * The athlete with the higher stat gains points for their team.
	 * The team with the most points wins
	 * @param me The user's club
	 * @param opponent The opposition team
	 * @return The result of the match.
	 */
	public static String playMatch(Club me, Team opponent) {
		int myScore = 0;
		int opponentScore = 0;
		ArrayList<Athlete> myTeam = me.viewActiveTeam();
		ArrayList<Athlete> opponentTeam = opponent.viewActiveTeam();
		for(int i = 0; i < 2 ; i++) {
			Athlete myDefender = myTeam.get(i);
			Athlete oppDefender = opponentTeam.get(i);
			if (myDefender.getInjuryStatus()) {
				opponentScore += 10;
			}
			else if(myDefender.getDefenceStat() > oppDefender.getDefenceStat()) {
				myScore += 10;
			}
			else if (myDefender.getDefenceStat() < oppDefender.getDefenceStat()) {
				opponentScore += 10;
				myDefender.decreaseStamina(20);
			}
			else {
				myScore += 5;
				opponentScore += 5;
			}
			myDefender.decreaseStamina(30);
			if(Stadium.checkTeamInjuries(me)) {
				return "Loss";	
			}			
		}
		for(int i = 2; i < 4; i++) {
			Athlete myAttacker = myTeam.get(i);
			Athlete oppAttacker = opponentTeam.get(i);
			if(myAttacker.getInjuryStatus()) {
				opponentScore += 10;
			}
			else if(myAttacker.getOffenceStat() > oppAttacker.getOffenceStat()) {
				myScore += 10;
			}
			else if (myAttacker.getOffenceStat() < oppAttacker.getOffenceStat()) {
				opponentScore += 10;
				myAttacker.decreaseStamina(20);
			}
			else {
				myScore +=5;
				opponentScore += 5;
			}
			myAttacker.decreaseStamina(30);
			if(Stadium.checkTeamInjuries(me)) {
				return "Loss";
			}
			
		}
		if(myScore > opponentScore) {
			return "Win";
		}
		else if(myScore < opponentScore) {
			return "Loss";
		}
		else {
			return "Draw";
		}
		
		
	}
	
}

