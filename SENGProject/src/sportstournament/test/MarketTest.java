package sportstournament.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import sportstournament.main.*;
class MarketTest {

	@Test
	public void buyReserveTest() {
		GameEnvironment game = new GameEnvironment();
		Team team = new Team(game);
		game.setMoneyAmount(250);
		int totalFunds = game.getMoneyAmount();
		Market market = new Market(game);
		ArrayList<Athlete> myTeam = team.randomTeamGenerator().viewActiveTeam();
		Club club = new Club("Pumas", myTeam);
		ArrayList<Athlete> reserves = club.viewReserves();
		ArrayList<Athlete> freeAgents = market.getFreeAgents();
		Athlete athleteToBuy = freeAgents.get(0);
		String purchase = market.buyReserve(athleteToBuy, club);
		int expectedFunds = totalFunds - athleteToBuy.getPrice();
		assertEquals(athleteToBuy.getName() + " purchased.", purchase);
		assertEquals(expectedFunds, game.getMoneyAmount());
		assertFalse(freeAgents.contains(athleteToBuy));
		assertTrue(reserves.contains(athleteToBuy));
		game.setMoneyAmount(2);
		freeAgents.remove(0);
		Athlete freeAgent = freeAgents.get(0);
		purchase = market.buyReserve(freeAgent, club);
		assertEquals("You cannot afford this!", purchase);
		game.setMoneyAmount(11);
		market.buyReserve(freeAgent, club);
		assertEquals(3, freeAgents.size());
	}
	
	@Test
	public void buyStarterTest() {
		GameEnvironment game = new GameEnvironment();
		Team team = new Team(game);
		game.setMoneyAmount(250);
		int totalFunds = game.getMoneyAmount();
		Market market = new Market(game);
		ArrayList<Athlete> myTeam = team.randomTeamGenerator().viewActiveTeam();
		Club club = new Club("Pumas", myTeam);
		ArrayList<Athlete> reserves = club.viewReserves();
		ArrayList<Athlete> starters = club.viewActiveTeam();
		ArrayList<Athlete> freeAgents = market.getFreeAgents();
		Athlete athleteToBuy = freeAgents.get(0);
		Athlete sub = starters.get(0);
		String purchase = market.buyStarter(athleteToBuy, sub, club);
		int expectedFunds = totalFunds - athleteToBuy.getPrice();
		assertEquals(athleteToBuy.getName() + " purchased.", purchase);
		assertEquals(expectedFunds, game.getMoneyAmount());
		assertFalse(freeAgents.contains(athleteToBuy));
		assertTrue(starters.contains(athleteToBuy));
		assertTrue(reserves.contains(sub));
		game.setMoneyAmount(2);
		freeAgents.remove(0);
		Athlete freeAgent = freeAgents.get(0);
		sub = starters.get(0);
		purchase = market.buyStarter(freeAgent, sub, club);
		assertEquals("You cannot afford this!", purchase);
		game.setMoneyAmount(11);
		market.buyStarter(freeAgent, sub, club);
		assertEquals(3, freeAgents.size());
	}
	
	@Test
	public void buyItemTest() {
		GameEnvironment game = new GameEnvironment();
		Team team = new Team(game);
		game.setMoneyAmount(250);
		Market market = new Market(game);
		ArrayList<Athlete> myTeam = team.randomTeamGenerator().viewActiveTeam();
		Club club = new Club("Pumas", myTeam);
		ArrayList<Item> items = market.viewAvailableItems();
		int index = items.indexOf(Item.PROTEIN_SHAKE);
		Item proteinShake =  items.get(index);
		String purchase = market.buyItem(proteinShake , club);
		ArrayList<Item> inventory = club.viewItems();
		assertEquals("PROTEIN_SHAKE purchased.", purchase);
		assertEquals(247, game.getMoneyAmount());
		assertTrue(inventory.contains(proteinShake));
		game.setMoneyAmount(1);
		purchase = market.buyItem(items.get(0) , club);
		assertEquals("You cannot afford this!", purchase);
		
	}
	
	@Test
	public void sellReserveTest() {
		ArrayList<Athlete> myTeam = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			Athlete myAthlete = new Athlete("George", 60, 72, 5);
			myTeam.add(myAthlete); 
		}
		GameEnvironment game = new GameEnvironment();
		game.setMoneyAmount(250);
		Market market = new Market(game);
		Club club = new Club("Fellas", myTeam);
		ArrayList<Athlete> reserves = club.viewReserves();
		Athlete reserve = new Athlete("Bob", 57, 82, 6);
		reserves.add(reserve);
		market.sellReserve(reserve, club);
		assertEquals(253, game.getMoneyAmount());
		assertFalse(reserves.contains(reserve));
	}
	
	@Test
	public void sellStarterTest() {
		ArrayList<Athlete> myTeam = new ArrayList<Athlete>();
		for(int i = 0; i < 4; i++) {
			Athlete myAthlete = new Athlete("George", 60, 72, 5);
			myTeam.add(myAthlete); 
		}
		GameEnvironment game = new GameEnvironment();
		game.setMoneyAmount(250);
		Market market = new Market(game);
		Club club = new Club("Fellas", myTeam);
		ArrayList<Athlete> reserves = club.viewReserves();
		ArrayList<Athlete> starters = club.viewActiveTeam();
		Athlete reserve = new Athlete("Bob", 57, 82, 6);
		Athlete starter = starters.get(0);
		reserves.add(reserve);
		market.sellStarter(starter, reserve, club);
		assertEquals(252, game.getMoneyAmount());
		assertEquals(0, reserves.size());
		assertTrue(starters.contains(reserve));
		assertFalse(starters.contains(starter));
	}
	
	@Test
	public void sellItemTest() {
		GameEnvironment game = new GameEnvironment();
		Team team = new Team(game);
		game.setMoneyAmount(250);
		Market market = new Market(game);
		ArrayList<Athlete> myTeam = team.randomTeamGenerator().viewActiveTeam();
		Club club = new Club("Pumas", myTeam);
		club.addItem(Item.ENERGY_DRINK);
		market.sellItem(Item.ENERGY_DRINK, club);
		assertEquals(255, market.getMoney());
		assertFalse(club.viewItems().contains(Item.ENERGY_DRINK));
	}

}
