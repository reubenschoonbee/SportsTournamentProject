package sportstournament.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sportstournament.main.Item;

class ItemTest {

	@Test
	public void proteinShakeTest() {
		Item proteinShake = Item.PROTEIN_SHAKE; 
		int statBoost = proteinShake.getStatBoost();
		int price = proteinShake.getPrice();
		String stat = proteinShake.getStat();
		assertEquals(3, price);
		assertEquals(5, statBoost);
		assertEquals("Defence", stat);
		assertEquals("PROTEIN_SHAKE: Boosts a players Defence by 5  ($3)", proteinShake.toString());
	}
	
	@Test
	public void energyDrinkTest() {
		Item energyDrink = Item.ENERGY_DRINK;
		int price = energyDrink.getPrice();
		int statBoost = energyDrink.getStatBoost();
		String stat = energyDrink.getStat();
		assertEquals(5, price);
		assertEquals(10, statBoost);
		assertEquals("Stamina", stat);
		assertEquals("ENERGY_DRINK: Boosts a players Stamina by 10  ($5)", energyDrink.toString());
	}
	 
	@Test
	public void kneeSleeveTest() {
		Item kneeSleeve = Item.KNEE_SLEEVE;
		int price = kneeSleeve.getPrice();
		int statBoost = kneeSleeve.getStatBoost();
		String stat = kneeSleeve.getStat();
		assertEquals(4, price);
		assertEquals(5, statBoost);
		assertEquals("Offence", stat);
		assertEquals("KNEE_SLEEVE: Boosts a players Offence by 5  ($4)", kneeSleeve.toString());
		
	}

}
