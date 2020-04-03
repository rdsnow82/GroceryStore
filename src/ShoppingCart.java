import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class ShoppingCart {	
	private static final float TAX_RATE = 1.02f;
	
	private ArrayList<Product> shoppingCartList;
	
	
	public ArrayList<Product> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(ArrayList<Product> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public ShoppingCart() {
		shoppingCartList = new ArrayList<Product>();
	}
	
	public void emptyShoppingCart() {
		shoppingCartList.clear();
	}
	
	// Add a new item to the shopping cart with the name and price from inventory and quantity from customer
	public void add(Product item, int quantity) {
		Product cartItem = new Product(item.getName(), item.getPrice());
		cartItem.setCount(quantity);
		
		this.shoppingCartList.add(cartItem);
		
	}
	
	public double getSubtotal(boolean withTax) {
		double total = 0;
		
		
		Iterator<Product> it = shoppingCartList.iterator();
		while (it.hasNext()) {
			Product product = (Product) it.next();
			total = total + (product.getCount() * product.getPrice());
		}
		
		if (withTax) {
			total = total * TAX_RATE;
		}
		
		BigDecimal bd = new BigDecimal(Double.toString(total));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}
	
	public void displayItems() {
		
		int itemNumber;
		Product item;
		
		
		if (!this.shoppingCartList.isEmpty()) 
		{	
			System.out.println("\nItems in Shopping Cart:");
			for (int i=0; i < this.shoppingCartList.size(); i++)
			{
				item = shoppingCartList.get(i);
				itemNumber = i+1;
				System.out.println("Item " + itemNumber + ", " + item.getName() + ", Quantity of " + item.getCount() + ", " + item.getPrice());
			}
			
		}
		
	}
	
	public boolean isEmpty() {
		return shoppingCartList.isEmpty();
	}

}
