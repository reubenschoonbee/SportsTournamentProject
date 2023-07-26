package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.Item;


class ClubTest {

	@Test
	public void useItemTest() {
		Athlete tempathlete = new  Athlete("Miles", 70, 70, 7);
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		athletes.add(tempathlete);
		Club club = new Club("BOB", athletes);
		club.addItem(Item.ENERGY_DRINK);
		club.addItem(Item.KNEE_SLEEVE);
		club.addItem(Item.PROTEIN_SHAKE);
		Athlete athlete = athletes.get(0);
		athlete.setStamina(70);
		club.useItem(Item.ENERGY_DRINK, athlete);
		club.useItem(Item.KNEE_SLEEVE, athlete);
		club.useItem(Item.PROTEIN_SHAKE, athlete);
		assertEquals(80, athlete.getStamina());
		assertEquals(75, athlete.getOffenceStat());
		assertEquals(75, athlete.getDefenceStat());
		ArrayList<Item> items = club.viewItems();
		assertEquals(0, items.size());
		
	}
	
	@Test
	public void subAthletesTest() {
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		ArrayList<Athlete> reserves = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			athletes.add(Athlete.randomAthleteGenerator());
		}
		for(int i = 0; i < 5; i++) {
			reserves.add(Athlete.randomAthleteGenerator());
		}
		Club club = new Club("BOYS", athletes, reserves);
		ArrayList<Athlete> clubAthletes = club.viewActiveTeam();
		ArrayList<Athlete> clubReserves = club.viewReserves();
		Athlete swappedReserve = clubReserves.get(0);
		Athlete swappedActive = clubAthletes.get(0);
		club.subAthlete(swappedReserve, swappedActive);
		assertTrue(clubReserves.contains(swappedActive));
		assertTrue(clubAthletes.contains(swappedReserve));
	}
	
	@Test
	public void addAthleteTest() {
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		ArrayList<Athlete> reserves = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			athletes.add(Athlete.randomAthleteGenerator());
		}
		for(int i = 0; i < 5; i++) {
			reserves.add(Athlete.randomAthleteGenerator());
		}
		final Club club = new Club("BOYS", athletes, reserves);
		ArrayList<Athlete> clubReserves = club.viewReserves();
		ArrayList<Athlete> clubStarters = club.viewActiveTeam();
		Athlete newAthlete = Athlete.randomAthleteGenerator();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> club.addNewAthlete(newAthlete));
		assertEquals(5, clubReserves.size());
		assertTrue(club.isTeamFull());
		Athlete reserve = clubReserves.get(0);
		club.removeReserve(reserve);
		assertEquals(4,clubReserves.size());
		assertFalse(club.isTeamFull());
		club.addNewAthlete(newAthlete);
		assertEquals(5, clubReserves.size());
		assertTrue(clubReserves.contains(newAthlete));
		assertFalse(clubStarters.contains(newAthlete));
		assertTrue(club.isTeamFull());
		
	}
	
	@Test
	public void removeItemTest() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(Item.PROTEIN_SHAKE);
		Athlete tempathlete = new  Athlete("Miles", 70, 70, 7);
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		athletes.add(tempathlete);
		Club club = new Club("BOB", athletes);
		club.setInventory(items);
		club.removeItem(Item.PROTEIN_SHAKE);
		assertTrue(club.viewItems().size() == 0);
	}

}
