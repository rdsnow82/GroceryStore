
public class CommonInventory extends Inventory {
	
	public CommonInventory() {
		createCommonInventory();
	}
	
	public void createCommonInventory() {
		this.getProductList().add(new Product("Milk", 60, 60, 2.89));
		this.getProductList().add(new Product("Bread", 80, 80, 2.39));
		this.getProductList().add(new Product("Coke - Liter", 100, 100, 1.29));
		this.getProductList().add(new Product("Bananas - 2 lbs", 40, 40, 2.49));
		this.getProductList().add(new Product("Dannon Yogurt - 4 Pack", 30, 30, 3.59));
		this.getProductList().add(new Product("Hamburger - 1 lb", 25, 25, 6.50));
	}

}
