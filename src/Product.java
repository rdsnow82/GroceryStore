
public class Product {
	String name;
	int count; // amount on hand
	int stockLevel;  // default stock level
	double price;
	
	
	public Product(String name, int count, double price) {
		this.name = name;
		this.count = count;
		this.price = price;
	}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, int stockLevel, int count, double price) {
		this.name = name;
		this.stockLevel = stockLevel;
		this.count = count;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}	
	
	public void replenishStockLevel() {
		this.count = this.stockLevel;
	}
	
}
