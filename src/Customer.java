import java.util.Scanner;

public class Customer extends Role {

	private int customerZip;
	private GroceryStore nearestStore;
	private GroceryStore preferredStore;
	private ShoppingCart cart;
	Scanner kb = new Scanner(System.in);
	
	
	public Customer(String name, int zip) {
		setName(name);
		customerZip = zip;
		nearestStore = this.getChain().findNearestStore(customerZip);
		preferredStore = nearestStore;
		cart = new ShoppingCart();
	}
	
	public Customer(String name) 
	{
		setName(name);
		cart = new ShoppingCart();
	}
	
	public int getCustomerZip() {
		return customerZip;
	}
	
	public void setCustomerZip(int zip) {
		this.customerZip = zip;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
	
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	
	public GroceryStore getNearestStore() {
		return nearestStore;
	}
	
	public void setNearestStore(GroceryStore nearestStore) {
		this.nearestStore = nearestStore;
	}
	
	// set and get default store
	public GroceryStore getStore() {
		return preferredStore;
	}

	public void setStore(GroceryStore store) {
		this.preferredStore = store;
	}

	
	public int displayMainMenu() {
		//String selectionString;
		int selection = 0;
		Scanner kb = new Scanner(System.in);
		
		System.out.println();
		System.out.println("1. Display Stores");
		System.out.println("2. Find Nearest Store");
		System.out.println("3. Select Store to Shop");
		System.out.println("4. Shop");
		System.out.println("5. Display Shopping Cart");
		System.out.println("6. Display Shopping Cart Total");
		System.out.println("7. Check Out");
		System.out.println("Enter a number from the menu (0 to quit)");
		
		if (kb.hasNextLine()) {
			selection = Integer.parseInt(kb.nextLine());
		}
		
		
		switch (selection) {
		case (0):
			break;
		case (1):
			this.getChain().displayStores();
		    break;
		case (2):
			this.findNearestStore();
		    break;
		case (3):
			this.selectStore();
			break;
		case (4):
			this.shop();
			break;
		case (5):
			this.displayShoppingCart();
			break;
		case (6):
			this.displayCartTotal();
			break;
		case (7):
			this.checkOut();
			break;
		
		}
		
		return selection;
		
		
	}
	
	public void checkOut()
	{
		Scanner kb = new Scanner(System.in);
		
		if (!cart.isEmpty()) {
			this.getCart().displayItems();
			this.displayCartTotal();
			System.out.println("\nDo you want to check out now (Y/N)?");
			if (kb.nextLine().equalsIgnoreCase("Y")) {
				this.getStore().getInventory().updateInventory(cart, this.getStore().getName());
			}
		}	
		else {
			System.out.println("\nYour cart is empty");
		}
		
		
	}
	
	public void findNearestStore() {
		
	    int zip = 0;
	    GroceryStore nearestStore;
		Scanner kb = new Scanner(System.in);
		String response;
		
		boolean validZipCode = false;
		
		do {
			try {
				System.out.println("\nEnter your zip code: ");
				zip = Integer.parseInt(kb.nextLine());
				this.setCustomerZip(zip);
				validZipCode = true;
			} catch (NumberFormatException e) {
				System.out.println("\nInvalid Zip Code");
				continue;
			}
		} while (!validZipCode);
		
		// Find the nearest store
		nearestStore = this.getChain().findNearestStore(zip);
	
		
		if (nearestStore == null) {
			System.out.println("\nThere is no store in your zip code");
			selectStore();
		}	
		else {
			System.out.println("\nThe nearest grocery store is " + nearestStore.getName());
			this.setNearestStore(nearestStore);
			System.out.println("Would you like to make this your preferred store? (Y/N)");
			
			response = kb.nextLine();
			
			if (response.equalsIgnoreCase("Y")) {
				setStore(this.getNearestStore());
			}
		}	
		
	
	}
	
	public void selectStore() {
		
		Scanner kb = new Scanner(System.in);
		int storeNum;
		GroceryStore oldStore = this.getStore();
		GroceryStore newStore;
		
		this.getChain().displayStores();;
		
		System.out.println("\nEnter the number of your preferred store");
		storeNum = kb.nextInt();
		
		this.setStore(this.getChain().getGroceryStoreList().get(storeNum-1));
		newStore = this.getStore();
		
		// clear any items in shopping cart 
		if (oldStore != null) {
			if (oldStore != newStore) {
				this.getCart().emptyShoppingCart();
			}
		}
		
		System.out.println("Your store is set to " + this.getStore().getName());
		
	}
	
	//display items in cart with quantity and price
	public void displayShoppingCart() 
	{
		if (!cart.isEmpty()) {
			System.out.println("\nStore is " + this.getStore().getName());
			this.getCart().displayItems();
		}
		else {
			System.out.println("\nThere are no items in your shopping cart");
		}
		
	}
	
	// display the total with and without tax
	public void displayCartTotal() 
	{
		if (this.getCart().isEmpty()) {
			System.out.println("Cart is Empty");
		}
		else {
			System.out.println("\nStore is " + this.getStore().getName());
			System.out.println("Subtotal of shopping cart is " + this.getCart().getSubtotal(false));
			System.out.println("Total with tax is " + this.getCart().getSubtotal(true));
		}
	}
	
	public void shop()
	{
		Scanner kb = new Scanner(System.in);
		int itemNum;
		int qty;
		boolean quit = false;
		String menuChoice;
		Product item;
		
		
		if (this.getStore() == null)
		{
			selectStore();
		}
		else {
			System.out.println("\nYour preferred store is set to " + this.getStore().getName());
			System.out.println("Do you want to shop a different store? (Y or N");
			if (kb.nextLine().equalsIgnoreCase("Y")) {
				selectStore();
			}
		}
		
		System.out.println("\nInventory List - " + this.getStore().getName());
		this.getStore().getInventory().displayInventoryItems();
		
		
		// Populate customer shopping cart
		
		do {
			try {
				System.out.println("\nEnter item number to add to cart (Enter Q to quit");
				menuChoice = kb.nextLine();
				
				if (menuChoice.equalsIgnoreCase("Q")) {
					quit = true;
					break;
				}
				else {
					itemNum = Integer.parseInt(menuChoice);
					item = this.getStore().getInventory().getProductList().get(itemNum-1);
				}
				
				System.out.println("Enter quantity of " + item.getName() + " to purchase");
				qty = Integer.parseInt(kb.nextLine());
				
				if (qty > item.count) {
					throw new Exception();
				}
				
				this.getCart().add(item, qty);
				
			}	
			catch (NumberFormatException ne) {
				System.out.println("Please enter a number");
				continue;
			}
			catch (IndexOutOfBoundsException ie) {
				System.out.println("Not a valid item number");
				continue;
			}
			catch (Exception e) {
				System.out.println("Quantity exceeds inventory");
				continue;
			}
			
			
		} while (!quit);
		
	}
	
}
