package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sportstournament.main.Athlete;
import sportstournament.main.Club;
import sportstournament.main.RandomEvent;

class RandomEventTest {

	private RandomEvent randomEvent;
	private Club club;
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<Athlete> myTeam = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			Athlete myAthlete = new Athlete("George", 67, 82, 7);
			myTeam.add(myAthlete);
		}
		club = new Club("My Club", myTeam);
		randomEvent = new RandomEvent(club);
		randomEvent.setJoinNumber(100);
		randomEvent.setQuitNumber(100);
		randomEvent.setStatNumber(100);
		
	}

	@Test
	void hardRandomAthleteBoostOccurrenceTest() {
		randomEvent.setStatNumber(5);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("Athlete Boost", randomEventOccurrence);
		Athlete booster = randomEvent.getBooster();
		assertEquals(77, booster.getOffenceStat());
		assertEquals(92, booster.getDefenceStat());
	}
	
	@Test
	void hardRandomAthleteBoostNoOccurrenceTest() {
		randomEvent.setStatNumber(12);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("None", randomEventOccurrence);
		Athlete booster = randomEvent.getBooster();
		assertTrue(booster == null);
	}
	
	@Test
	void easyRandomAthleteBoostOccurrenceTest() {
		randomEvent.setStatNumber(18);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("Athlete Boost", randomEventOccurrence);
		Athlete booster = randomEvent.getBooster();
		assertEquals(77, booster.getOffenceStat());
		assertEquals(92, booster.getDefenceStat());
	}
	
	@Test
	void easyRandomAthleteBoostNoOccurrenceTest() {
		randomEvent.setStatNumber(32);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("None", randomEventOccurrence);
		Athlete booster = randomEvent.getBooster();
		assertTrue(booster == null);
	}
	
	
	@Test
	void hardRandomInjuredAthleteQuitsOccurrenceTest() {
		randomEvent.setQuitNumber(17);
		for (Athlete athlete: club.viewActiveTeam()) {
			athlete.setInjuryStatus(true);
		}
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("Athlete Quits", randomEventOccurrence);
		Athlete quitter = randomEvent.getQuitter();
		assertFalse(club.viewActiveTeam().contains(quitter));
		assertTrue(club.viewActiveTeam().contains(reserve));
		assertTrue(club.viewReserves().size() == 0);
	}
	
	@Test
	void hardRandomInjuredAthleteQuitsNoOccurrenceTest() {
		randomEvent.setQuitNumber(23);
		for (Athlete athlete: club.viewActiveTeam()) {
			athlete.setInjuryStatus(true);
		}
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("None", randomEventOccurrence);
	}
	
	@Test
	void easyRandomInjuredAthleteQuitsOccurrenceTest() {
		randomEvent.setQuitNumber(13);
		for (Athlete athlete: club.viewActiveTeam()) {
			athlete.setInjuryStatus(true);
		}
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("Athlete Quits", randomEventOccurrence);
		Athlete quitter = randomEvent.getQuitter();
		assertFalse(club.viewActiveTeam().contains(quitter));
		assertTrue(club.viewActiveTeam().contains(reserve));
		assertTrue(club.viewReserves().size() == 0);
	}
	
	@Test
	void easyRandomInjuredAthleteQuitsNoOccurrenceTest() {
		randomEvent.setQuitNumber(15);
		for (Athlete athlete: club.viewActiveTeam()) {
			athlete.setInjuryStatus(true);
		}
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("None", randomEventOccurrence);
	}
	
	@Test
	void hardRandomHealthyAthleteQuitsOccurrenceTest() {
		randomEvent.setQuitNumber(9);
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("Athlete Quits", randomEventOccurrence);
		Athlete quitter = randomEvent.getQuitter();
		assertFalse(club.viewActiveTeam().contains(quitter));
		assertTrue(club.viewActiveTeam().contains(reserve));
		assertTrue(club.viewReserves().size() == 0);
	}
	
	@Test
	void hardRandomHealthyAthleteQuitsNoOccurrenceTest() {
		randomEvent.setQuitNumber(11);
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("None", randomEventOccurrence);
	}
	
	@Test
	void easyRandomHealthyAthleteQuitsOccurrenceTest() {
		randomEvent.setQuitNumber(4);
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("Athlete Quits", randomEventOccurrence);
		Athlete quitter = randomEvent.getQuitter();
		assertFalse(club.viewActiveTeam().contains(quitter));
		assertTrue(club.viewActiveTeam().contains(reserve));
		assertTrue(club.viewReserves().size() == 0);
	}
	
	@Test
	void easyRandomHealthyAthleteQuitsNoOccurrenceTest() {
		randomEvent.setQuitNumber(5);
		Athlete reserve = new Athlete("Bencher", 57, 86, 7);
		club.addNewAthlete(reserve);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("None", randomEventOccurrence);
	}
	
	@Test
	void hardRandomAthleteJoinsEmptyReservesTest() {
		randomEvent.setJoinNumber(24);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("Athlete Joins", randomEventOccurrence);
		Athlete joiner = randomEvent.getJoiner();
		assertTrue(club.viewReserves().contains(joiner));
	}
	@Test
	void hardRandomAthleteDoesNotJoinEmptyReservesTest() {
		randomEvent.setJoinNumber(25);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("None", randomEventOccurrence);
	}
	
	@Test
	void easyRandomAthleteJoinsEmptyReservesTest() {
		randomEvent.setJoinNumber(29);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("Athlete Joins", randomEventOccurrence);
		Athlete joiner = randomEvent.getJoiner();
		assertTrue(club.viewReserves().contains(joiner));
	}
	@Test
	void easyRandomAthleteDoesNotJoinEmptyReservesTest() {
		randomEvent.setJoinNumber(30);
		String randomEventOccurrence = randomEvent.doRandomEvent("Easy");
		assertEquals("None", randomEventOccurrence);
		
	}
	
	@Test
	void RandomAthleteJoinsFullReservesTest() {
		for(int i = 0; i < 5; i++) {
			Athlete reserve = new Athlete("Bencher", 57, 86, 7);
			club.addNewAthlete(reserve);
		}
		randomEvent.setJoinNumber(0);
		String randomEventOccurrence = randomEvent.doRandomEvent("Hard");
		assertEquals("None", randomEventOccurrence);
	}
	
}
