import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite a senha: ");
		int x = sc.nextInt();
		int tentativas = 0;
		
		while(x != 2002) {
			tentativas++;
			
			if (tentativas == 3) {
				System.out.println("Voce errou 3 vezes a senha, o programa será bloqueado!");
				System.exit(0);
			}
			else {
				System.out.println("Restam " + (3 -tentativas) + " tentativas");
				System.out.println("Digite a senha: ");
				x = sc.nextInt();				
			}
			
		}
		System.out.println("Acesso permitido!");
		
		sc.close();
	}

}
