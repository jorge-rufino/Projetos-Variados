package entities;

public class Product {
	private String name;
	private double price;;
	private int quantity;
	
	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
			
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	
	public double totalValueStock() {
		return price * quantity;
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
