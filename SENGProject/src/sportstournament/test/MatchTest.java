package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.Match;
import sportstournament.main.Team;

import java.util.ArrayList;


class MatchTest {

	@Test
	public void playMatchTest() {
		ArrayList<Athlete> myTeam = new ArrayList<Athlete>();
		ArrayList<Athlete> oppTeam = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			Athlete myAthlete = new Athlete("George", 60, 72, 5);
			Athlete oppAthlete = new Athlete("Harry", 81, 60, 3);
			myTeam.add(myAthlete); 
			oppTeam.add(oppAthlete); 
		}
		Club myClub = new Club("Fellas", myTeam);
		Team opps = new Team("Devils", oppTeam);
		String result = Match.playMatch(myClub, opps);
		assertEquals("Draw", result);
		ArrayList<Athlete> clubTeam = myClub.viewActiveTeam();
		Athlete myFirstDefender = clubTeam.get(0);
		Athlete myFirstAttacker = clubTeam.get(2);
		assertEquals(50,myFirstAttacker.getStamina());
		assertEquals(70,myFirstDefender.getStamina());
		myFirstAttacker.setStamina(3);
		myFirstAttacker.setOffenceStat(83);
		String newResult = Match.playMatch(myClub, opps);
		assertEquals("Win", newResult);
		assertEquals(0, myFirstAttacker.getStamina());
		assertEquals(40, myFirstDefender.getStamina());
		assertTrue(myFirstAttacker.getInjuryStatus());
		myFirstDefender.setDefenceStat(57);
		myFirstDefender.setStamina(15);
		Athlete mySecondDefender = clubTeam.get(1);
		mySecondDefender.setDefenceStat(60);
		myFirstAttacker.setOffenceStat(81);
		myFirstAttacker.setStamina(30);
		String lossResult = Match.playMatch(myClub, opps);
		assertEquals("Loss", lossResult);
		assertEquals(0, myFirstDefender.getStamina());
		assertTrue(myFirstDefender.getInjuryStatus());
		String injuredDefenceLossResult = Match.playMatch(myClub, opps);
		assertEquals("Loss", injuredDefenceLossResult);
		Athlete mySecondAttacker = myTeam.get(3);
		mySecondAttacker.setStamina(40);
		String injuredAttackLoss = Match.playMatch(myClub, opps);
		assertEquals("Loss", injuredAttackLoss);
		
	}

}
