package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sportstournament.main.Athlete;

class AthleteTest {
	

	@Test 
	public void increaseOffenceTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.setOffenceStat(50);
		testAthlete.increaseOffence(20);
		assertEquals(70, testAthlete.getOffenceStat());
		testAthlete.increaseOffence(40);
		assertEquals(100, testAthlete.getOffenceStat());
	}
	
	@Test
	public void increaseDefenceTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.setDefenceStat(63);
		testAthlete.increaseDefence(27);
		assertEquals(90, testAthlete.getDefenceStat());
		testAthlete.increaseDefence(47);
		assertEquals(100, testAthlete.getDefenceStat());
	} 
	
	@Test
	public void increaseStaminaTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.setStamina(72);
		testAthlete.increaseStamina(12);
		assertEquals(84, testAthlete.getStamina());
		testAthlete.increaseStamina(43);
		assertEquals(100, testAthlete.getStamina());
	}
	
	@Test
	public void decreaseStaminaTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.decreaseStamina(72);
		assertEquals(28, testAthlete.getStamina());
		testAthlete.decreaseStamina(45);
		assertEquals(0, testAthlete.getStamina());
		
	}
	
	@Test
	public void restoreStaminaTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.decreaseStamina(89);
		testAthlete.restoreStamina();
		assertEquals(100, testAthlete.getStamina());
	}
	
	@Test
	public void toStringTest() {
		Athlete testAthlete = new Athlete("Harry", 87, 67, 7);
		String expectedString = new String("Name: Harry, Offence: 87, Defence: 67, Stamina: 100, Price: 7, Injured: false");
		assertEquals(expectedString, testAthlete.toString());
	}
	
		

}
