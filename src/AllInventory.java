
public class AllInventory extends Inventory {
	
	public AllInventory() {
		this.getProductList().addAll(new CommonInventory().getProductList());
		this.getProductList().addAll(new UncommonInventory().getProductList());
		
	}
}
