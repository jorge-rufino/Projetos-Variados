package aplications;

import java.util.Locale;
import java.util.Scanner;

import entities.Conta;

public class TestaConta {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
			
		Conta conta1;
		
		System.out.print("Enter account number:");
		int number = sc.nextInt();
		System.out.print("Enter account holder:");
		sc.nextLine();
		String name = sc.nextLine();
		
		System.out.print("Is there an initial deposit? (y/n)");
		char aux = sc.next().charAt(0);
				
		if (aux == 'y') {
			System.out.print("Enter initial deposit value:");
			double deposito = sc.nextDouble();
			conta1 = new Conta(number, name, deposito);			
		}		
		else {
			conta1 = new Conta(number, name);
		}
		
		System.out.println();		
		System.out.println("Account data:");
		System.out.println(conta1);
		
		System.out.println();
		System.out.print("Enter a deposit value: ");
		conta1.deposito(sc.nextDouble());
		System.out.println("Update account data:");		
		System.out.println(conta1);
		
		System.out.println();
		System.out.print("Enter a withdraw value: ");
		conta1.saque(sc.nextDouble());
		System.out.println("Update account data:");		
		System.out.println(conta1);
		
		sc.close();

	}

}
