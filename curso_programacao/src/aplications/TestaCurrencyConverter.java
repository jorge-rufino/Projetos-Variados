package aplications;

import java.util.Locale;
import java.util.Scanner;

import entities.CurrencyConverter;

public class TestaCurrencyConverter {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is the dollar price?");
		double cotacaoDolar= sc.nextDouble();
		System.out.println("How many dollars will be bought?");
		double dollar = sc.nextDouble();
		System.out.printf("Amount to be paid in reais: %.2f", CurrencyConverter.conversor(cotacaoDolar, dollar));
		
		sc.close();
	}

}
