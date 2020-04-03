
public class UncommonInventory extends Inventory {
	
	public UncommonInventory() {
		createUncommonInventory();
	}
	
	public void createUncommonInventory() {
		this.getProductList().add(new Product("Ginger Beer - 6 Pack", 25, 25, 6.99));
		this.getProductList().add(new Product("Ben & Jerry's Ice Cream - 1 pint", 40, 40, 4.50));
	}

}
