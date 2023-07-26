package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sportstournament.main.*;
import java.util.ArrayList;
class TeamTest {

	@Test
	public void TeamtoStringTest() {
		GameEnvironment game = new GameEnvironment();
		ArrayList<Athlete> team = new ArrayList<Athlete>();
		for (int i = 0; i < 4; i++) {
			Athlete athlete = Athlete.randomAthleteGenerator();
			team.add(athlete);
		}
		Team newTeam = new Team("Panthers", team, game);
		String expectedString = new String("Team Name: Panthers\n\nDefenders:\n"+team.get(0).toString()+"\n"+team.get(1).toString()+"\n\nAttackers:\n"+team.get(2).toString()+"\n"+team.get(3).toString());
		assertEquals(expectedString, newTeam.toString());
	}
}
