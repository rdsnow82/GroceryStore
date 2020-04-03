

public class GroceryStore {
	private String name;
	private int zipCode;
	
	private Inventory inventory;

	// create new grocery store without inventory
	public GroceryStore(String name, int zipCode) {
		this.name = name;
		this.zipCode = zipCode;
	}

	// create new grocery store with inventory
	public GroceryStore(String name, int zipCode, Inventory inventory) {
		this.name = name;
		this.zipCode = zipCode;
		this.inventory = inventory;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	

}
