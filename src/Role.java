import java.util.Scanner;

public abstract class Role {
	
	private String name;
	private GroceryStoreChain chain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public GroceryStoreChain getChain() {
		return chain;
	}

	public void setChain(GroceryStoreChain chain) {
		this.chain = chain;
	}

	public abstract int displayMainMenu();

	
}
