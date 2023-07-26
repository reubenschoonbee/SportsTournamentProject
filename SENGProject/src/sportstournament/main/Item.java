package sportstournament.main;

/**
 * An enum with three possible types of items.
 * Each item has a price, a stat boost, and the stat that the boost is applied to.
 * 
 * @author Isaac Steele and Reuben Schoonbee
 *
 */
public enum Item implements Purchasable {
	
	/**
	 * A protein shake that boosts the athlete's defence.
	 */
	PROTEIN_SHAKE(3, 5, "Defence"), 
	/**
	 * An energy drink that boosts the athlete's stamina
	 */
	ENERGY_DRINK(5,10, "Stamina"),
	/**
	 * A knee sleeve that boosts the athlete's offence
	 */
	KNEE_SLEEVE(4,5, "Offence");
	
	/**
	 * The price of the item.
	 */
	private int price;
	/**
	 * The item's stat boost value
	 */
	private int statBoost;
	
	/**
	 * The type of stat the item boosts
	 */
	
	private String stat;
	
	/**
	 * The to string method for the given item.
	 * 
	 * @return Returns the representation of an item in string format.
	 */
	@Override
	public String toString() {
		return this.name()+": Boosts a players "+getStat()+" by "+getStatBoost() +"  ($"+getPrice()+")";
	}
	
	/**
	 * returns the type of stat the item boosts.
	 * 
	 * @return The stat type the item boosts
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * Returns the price of the item
	 * 
	 * @return the item's price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the value of the item's stat boost.
	 * 
	 * @return the item,s stat boost
	 */
	public int getStatBoost() {
		return statBoost;
	}


	/**
	 * The default constructor for an Item, which sets all of its attributes.
	 * 
	 * @param price The price of the Item
	 * @param statBoost The stat boost of the Item
	 * @param stat The stat the item boosts.
	 */
	Item(int price, int statBoost, String stat){
		this.price = price;
		this.statBoost = statBoost;
		this.stat = stat;
	}
}
