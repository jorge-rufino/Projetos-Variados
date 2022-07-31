package aplications;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Vetor2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Product[] vector = new Product[n];
		
		for (int i=0; i < n ; i++) {
			sc.nextLine();
			String name = sc.nextLine();
			double price = sc.nextDouble();
			vector[i] = new Product(name, price, 0);
		}
		
		double soma = 0.0;
		for (int i=0; i < vector.length ; i++) {
			soma += vector[i].getPrice();
		}
		
		double media = soma / vector.length;
		
		System.out.println("Media dos preços: " + String.format("%.2f", media));
		sc.close();
	}

}
