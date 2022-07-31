package aplications;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class TesteProduct {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Enter product data:");
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Price: ");
		double price = sc.nextDouble();
		System.out.print("Quantity: ");
		int quantity = sc.nextInt();
		
		Product produto = new Product(name, price, quantity);
		
		System.out.println("Product data: "+produto);
		
		System.out.print("Number of products added in stock:");
		produto.addProduct(sc.nextInt());
		
		System.out.println("Update data: "+produto);
		
		System.out.print("Number of products removed in stock:");
		produto.removeProduct(sc.nextInt());
		
		System.out.println("Update data: "+produto);
			
		sc.close();

	}

}
