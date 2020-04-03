import java.util.Scanner;


public class Manager extends Role {
	
	public Manager(String name) 
	{
		this.setName(name);
	}

	public int displayMainMenu() {
	
		int selection = 0;
		Scanner kb = new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. Add Store");
		System.out.println("2. Add Product");
		System.out.println("3. Display Store Inventory");
		System.out.println("4. Restock Inventory");
		System.out.println("5. Display Stores");
		System.out.println("Enter a number from the menu (0 to quit)");
		
		if (kb.hasNextLine()) {
			selection = Integer.parseInt(kb.nextLine());
		}
		
		
		switch (selection) {
		case (0):
			break;
		case (1):
			this.addStore();
		    break;
		case (2):
			this.addProduct();
		    break;
		case (3):
			this.displayStoreInventory();
			break;
		case (4):
			this.restockInventory();
			break;
		case (5):
			this.getChain().displayStores();
			break;
		}
		
		return selection;
		
				
	}
	
	// restock the inventory levels for a given store back to the default levels
	public void restockInventory() {
		int storeNum;
		this.getChain().displayStores();
		
		Scanner kb = new Scanner(System.in);
		System.out.println("\nEnter the store number to restock inventory");
		storeNum = kb.nextInt();
		
		this.getChain().getGroceryStoreList().get(storeNum).getInventory().restockInventory();
		
	}
	
	// add a new store to the chain
	
	public void addStore() {
		Scanner kb = new Scanner(System.in);
		String name;
		int zip;
		int choice;
			
		Inventory initialInventory;
		
		GroceryStore newStore;
		
		System.out.println("\nEnter Store Name");
		name = kb.nextLine();
		System.out.println("Enter Zip Code");
		zip = Integer.parseInt(kb.nextLine());
		
		do {
			try {
				System.out.println("Enter 1 to stock store with common inventory or enter 2 to stock with both common and uncommon invemtory");
				
				 choice = kb.nextInt();
				 
				if (choice == 1) {
					initialInventory = new CommonInventory();
					break;
				}	
				else if (choice == 2) {
					initialInventory = new AllInventory();
					break;
				}	
				else 
					throw new Exception();
				
			}
			catch (Exception e) {
				System.out.println("Please enter 1 or 2");
				continue;
			}
		} while (true);
		
		newStore = new GroceryStore(name, zip, initialInventory);
		
		// add the new store to the chain
		this.getChain().addGroceryStore(newStore);
		
	}
	
   // add a new product to a store's inventory

	public void addProduct() {
		Scanner kb = new Scanner(System.in);
		String name;
		int count;
		int stockLevel;
		double price;
		Product newProduct;
		GroceryStore store;
		
		this.getChain().displayStores();
		
		System.out.println("\nSelect store number to add product");
		store = this.getChain().getGroceryStoreList().get(Integer.parseInt(kb.nextLine())-1);
		System.out.println("Enter Product Name");
		name = kb.nextLine();
		System.out.println("Enter Default Stock Level");
		stockLevel = Integer.parseInt(kb.nextLine());
		System.out.println("Enter Count on Hand");
		count = Integer.parseInt(kb.nextLine());
		System.out.println("Enter Price");
		price = kb.nextDouble();
		
		newProduct = new Product(name, stockLevel, count, price);
		
		store.getInventory().addProduct(newProduct);
		
	}
	
	public void displayStoreInventory() {
		
		Scanner kb = new Scanner(System.in);
		GroceryStore store;
		
		this.getChain().displayStores();
		
		System.out.println("\nSelect store number");
		store = this.getChain().getGroceryStoreList().get(kb.nextInt()-1);
		
		store.getInventory().displayInventoryItems();
		
	}
	

}
