import java.util.*;


public class GroceryStoreChain {
	
	private String name;
	private ArrayList<GroceryStore> groceryStoreList; // list of chain stores
	
	
	public GroceryStoreChain() {
		groceryStoreList = new ArrayList<GroceryStore>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<GroceryStore> getGroceryStoreList() {
		return groceryStoreList;
	}

	public void setGroceryStoreList(ArrayList<GroceryStore> groceryStore) {
		this.groceryStoreList = groceryStore;
	}
	
	public void addGroceryStore(GroceryStore store) {
		
		groceryStoreList.add(store);
	}
		
	// display all the stores in the chain
	public void displayStores() {
		
		GroceryStore store;
		int storeNumber;
		
		for (int i=0; i<groceryStoreList.size(); i++)
		{
			store = groceryStoreList.get(i);
			storeNumber = i + 1;
			System.out.println(storeNumber + ". Store Name: " + store.getName() + ", Zip Code - " + store.getZipCode());
		}
		
	}
	
	// search for and return store in given zip code.  If one not found return null
	
	public GroceryStore findNearestStore(int zipCode) 
	{
		GroceryStore nextStore = null;
		
		Iterator<GroceryStore> it = this.groceryStoreList.iterator();
		
		while (it.hasNext())
		{
			nextStore = (GroceryStore) it.next();
			if (nextStore.getZipCode() == zipCode) 
			{
				break;
			} else {
				nextStore = null;
			}	
		}
		
		return nextStore;
	}
	
	
}
