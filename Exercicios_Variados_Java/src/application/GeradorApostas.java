package application;

import java.util.Arrays;
import java.util.Random;

public class GeradorApostas {

	public static void main(String[] args) {

        int quantidadeApostas = 8;
        int numerosPorAposta = 6;
        int quantidadeNumerosSorteio = 60;
        
        int[][] apostas = gerarApostasMegaSena(quantidadeApostas, numerosPorAposta, quantidadeNumerosSorteio);

        for (int i = 0; i < quantidadeApostas; i++) {
            System.out.println(Arrays.toString(apostas[i]));
        }
    }

	public static int[][] gerarApostasMegaSena(int quantidadeApostas, int numerosPorAposta, int quantidadeNumerosSorteio){
		int [][] apostas = new int[quantidadeApostas][numerosPorAposta];
		
		Random random = new Random();
		
		for(int i = 0; i < quantidadeApostas; i++) {
			for(int j = 0; j < numerosPorAposta; j++) {
				
				int numero;

				//Gera o número e verifica se já existe para evitar números duplicados
				do {
					numero = random.nextInt(quantidadeNumerosSorteio) + 1;					
				}while(contemNumero(apostas[i], numero));
				
				apostas[i][j] = numero;
			}			
			
			// Ordenando os números
            Arrays.sort(apostas[i]);
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
