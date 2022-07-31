package aplications;

import java.util.Locale;
import java.util.Scanner;

public class Vetor {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quantas alturas serão lidas?");
		int n = sc.nextInt();
		double[] vekt = new double[n];
		
		for (int i=0 ; i < n; i++) {
			System.out.println("Qual a altura?");
			vekt[i] = sc.nextDouble();
		}
		
		double soma = 0.0;
		
		for (int i=0 ; i < n; i++) {			
			soma += vekt[i] ;
		}
		
		double media = soma / n;
		
		System.out.println("Media:" + String.format("%.2f", media));
		sc.close();

	}

}
