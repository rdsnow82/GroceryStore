
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Inventory {
	
	private ArrayList<Product> productList;
	private int size;

	public Inventory() {
		productList = new ArrayList<Product>();
	}
	
	public int getSize() {
		return productList.size();
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> product) {
		this.productList = product;
	}
	
	public void displayInventoryItems() 
	{	
		Product item; 
		int itemNumber;
		
		
		for (int i=0; i<getSize(); i++)
		{
			item = productList.get(i);
			itemNumber = i+1;
			System.out.println("Item " + itemNumber + ", " + item.getName() + ", " + item.getCount() + " on hand" + " , " + item.getPrice());
		}
	}	
		
	// update inventory based on contents of shopping cart and write to text file
	public void updateInventory(ShoppingCart cart, String store) {
			
		Product cartItem, inventoryItem;
		int cartItemQty;
		int inventoryCount;
		int updatedInventoryCount;
		
	    // update store inventory
		
	    // iterate over each item in cart and subtract and subtract quantity from the store inventory	
		Iterator<Product> it = cart.getShoppingCartList().listIterator();
		while (it.hasNext()) {
			cartItem = (Product) it.next();
			
			cartItemQty = cartItem.getCount();
			
			
			// Find item in inventory array
			
			for (int i =0; i< this.getProductList().size(); i++) {
				inventoryItem = productList.get(i);
				if (inventoryItem.getName().equals(cartItem.getName())) {
					inventoryCount = inventoryItem.getCount();
					updatedInventoryCount = inventoryCount - cartItemQty;
					inventoryItem.setCount(updatedInventoryCount);
					break;
				}
			}	
			
		}
		
		
		//display updated inventory
		displayInventoryItems();	
			
		// write data to text file
		writeInventory(store);
		
			
		
	}
	
	public void addProduct(Product product) {
		this.getProductList().add(product);
	}
	
	public void restockInventory() {
		Product inventoryItem;
		
		// iterate through list of products and set the count to default stock level
		for (int i=0; i<productList.size(); i++) {
			inventoryItem = productList.get(i);
			inventoryItem.replenishStockLevel();
		}
	}
	
	
	public void writeInventory(String store)
	{
		
		try {
			Product item;
			File fileName = new File(store + ".txt");
			FileOutputStream fos = new FileOutputStream(fileName);
		 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
			for (int i=0; i<productList.size(); i++) {
				item = productList.get(i);
				bw.write(item.getName() + ", " + item.getStockLevel() + ", " + item.getCount() + ", " + item.getPrice());
				bw.newLine();
			}
			
			bw.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	


}
