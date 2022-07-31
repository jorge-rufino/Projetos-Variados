package aplications;

import java.util.Scanner;

import entities.Rent;

public class TestaRent {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quantos quartos serão alugados?");		
		int n = sc.nextInt();
		
		Rent[] vector = new Rent[10];
		
		for (int i = 1; i <= n; i++) {
			System.out.println("Rent #"+i+":");
			System.out.print("Name:");
			String name = sc.next();
			
			System.out.print("Email:");
			String email = sc.next();
			
			System.out.print("Room:");
			int room = sc.nextInt();
			
			System.out.print("Quantos dias:");
			int dias = sc.nextInt();
			
			System.out.print("Normal ou Suíte:(1/2)?");
			int tipoQuarto = sc.nextInt();
									
			vector[room] = new Rent(name, email, dias, tipoQuarto);
		}
		
		System.out.println();
		System.out.println("Buys rooms:");
				
		//for (int i = 0; i < vector.length; i++) {
		for (Rent obj : vector) {
			if (obj != null) {
			//if (vector[i] != null) {
				//System.out.println(i+": "+vector[i]);
				System.out.println(obj);
			}
		}
		
		sc.close();

	}

}
