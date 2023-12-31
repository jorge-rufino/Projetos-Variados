package application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorApostas {

	public static void main(String[] args) {

        int quantidadeApostas = 80;
        int numerosPorAposta = 6;
        int quantidadeNumerosSorteio = 60;
        
        int[][] apostas = gerarApostasMegaSena(quantidadeApostas, numerosPorAposta, quantidadeNumerosSorteio);

        for (int i = 0; i < quantidadeApostas; i++) {
            System.out.println(Arrays.toString(apostas[i]));
        }
    }

	public static int[][] gerarApostasMegaSena(int quantidadeApostas, int numerosPorAposta, int quantidadeNumerosSorteio){
		int [][] apostas = new int[quantidadeApostas][numerosPorAposta];
		Set<String> apostasGeradas = new HashSet<>();
		
		Random random = new Random();
		
		for (int i = 0; i < quantidadeApostas; i++) {

            int[] novaAposta;
            do {
                novaAposta = new int[numerosPorAposta];
                
                for (int j = 0; j < numerosPorAposta; j++) {                	
                	int numero;
                	
            		do {
            			numero = random.nextInt(quantidadeNumerosSorteio) + 1;
            		}while (contemNumero(novaAposta, numero));
            		
            		novaAposta[j] =  numero;
                }
                Arrays.sort(novaAposta);
            } while (!apostasGeradas.add(Arrays.toString(novaAposta))); // Verifica duplicatas

            apostas[i] = novaAposta;
        }

        return apostas;
	}

	public static boolean contemNumero(int[] aposta, int numero) {
		for (int i : aposta) {
			if(i == numero) {
				return true;
			}
		}
		return false;
	}
    
}
