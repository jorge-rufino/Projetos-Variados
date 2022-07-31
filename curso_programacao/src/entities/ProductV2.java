package entities;

public class ProductV2 {
	public String name;
	public double price;;
	public int quantity;
	
	public double totalValueStock() {
		return price * quantity;
	}
	
	public ProductV2(String name, double price, int quanity) {
		this.name = name;
		this.price = price;
		this.quantity = quanity;
	}
	
	public void addProduct(int quantity) {
		this.quantity += quantity;
	}
	
	public void removeProduct(int quantity) {
		this.quantity -= quantity;
	}
	
	public String toString() {
		return  name 
				+ ", $ "
				+String.format("%.2f", price)
				+", "
				+quantity
				+" units, Total: $ "
				+ String.format("%.2f", totalValueStock());
	}

}
