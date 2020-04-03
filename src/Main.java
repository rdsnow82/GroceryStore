import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner kb = new Scanner(System.in);
		String userRole;
		Role role = null;
		boolean validRole = false;
		GroceryStoreChain chain = new GroceryStoreChain();
		int menuSelection;
	
		
		// Create test data
		// TODO:  Add code to read in saved store inventory data from text file
		
		chain.setName("Publix");
		chain.addGroceryStore(new GroceryStore("Publix - Market Square", 28078, new CommonInventory()));
		chain.addGroceryStore(new GroceryStore("Publix - Prosperity Village Square", 28269, new CommonInventory()));
		chain.addGroceryStore(new GroceryStore("Publix - Magnolia Village", 28031, new AllInventory()));
		chain.addGroceryStore(new GroceryStore("Publix - Cambridge Village", 28037, new CommonInventory()));
		
		
		System.out.println("Grocery Store Management System");
		
		try {
			do {
				
				
				System.out.println("Are you a Customer or a Manager? (Enter M for Manager or C for Customer)");
				userRole = kb.nextLine();
				
				if (userRole.equalsIgnoreCase("C")) {
					System.out.println("Enter your name");
					role = new Customer(kb.nextLine());
					validRole = true;
					}
				else if (userRole.equalsIgnoreCase("M")) {
					System.out.println("enter your name");
					role = new Manager(kb.nextLine());
					validRole = true;
				}
				else {
					System.out.println("Not a valid role - Please enter C or M");
				
				}
			} while (!validRole);
		
		
			role.setChain(chain);
			
			do {
				
				menuSelection = role.displayMainMenu();
			} while (menuSelection != 0);
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		finally { 
			System.exit(0);
		}
	}

}
