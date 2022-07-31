package application;

public class Programm {

	public static void main(String[] args) {
		int fatorial = 1;
		int x = 5;
		
		System.out.println("Fatorial:");
		for (int i = 1 ; i <= x; i++) {
			fatorial = fatorial * i;
			System.out.println("Fatorial de "+ i+" é ("+(i-1)+"!) * "+i +" = "+fatorial);
		}
		
		System.out.println("\nFibonacci:");
		x = 10;
		int anterior = 0, atual = 0, proximo = 0;
		for (int i = 0; i < x ;i++) {
			if(i == 0) {
				atual = 1;
			}
			anterior = atual;
			atual = proximo;
			proximo = atual + anterior;
			
			System.out.print(atual + ", ");
		}
		
		anterior = 0; atual = 0;
		System.out.println("\nFibonacci com outra logica:");
		for (int i = 0; i < x ;i++) {
			if(i == 1 || i == 2) {
				atual = 1;
				anterior = 0;
			}
			atual = atual + anterior;
			anterior = atual - anterior;
			
			System.out.print(atual + ", ");
		}
	}

}
