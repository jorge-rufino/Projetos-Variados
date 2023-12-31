package application;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GeradorApostas {

	public static void main(String[] args) {

        int quantidadeApostas = 50;
        int numerosPorAposta = 6;
        int quantidadeNumerosSorteio = 60;
        Set<Set<Integer>> apostas = gerarApostasMegaSena(quantidadeApostas, numerosPorAposta, quantidadeNumerosSorteio);

        for (Set<Integer> aposta : apostas) {
            System.out.println(aposta);
        }
    }

    public static Set<Set<Integer>> gerarApostasMegaSena(int quantidadeApostas, int numerosPorAposta, int quantidadeNumerosSorteio) {
        Set<Set<Integer>> apostas = new HashSet<>();
        Random random = new Random();

        gerarAposta(apostas, random, quantidadeApostas, numerosPorAposta, quantidadeNumerosSorteio);

        return apostas;
    }

    private static void gerarAposta(Set<Set<Integer>> apostas, Random random, int quantidade, int numerosPorAposta, 
    		int quantidadeNumerosSorteio) {
    	
        if (apostas.size() == quantidade) {
            return; // Condição de parada
        }

        Set<Integer> novaAposta = new TreeSet<>(); // Usando TreeSet para manter a ordenação
        while (novaAposta.size() < numerosPorAposta) {
            novaAposta.add(random.nextInt(quantidadeNumerosSorteio) + 1);
        }

        apostas.add(novaAposta);
        gerarAposta(apostas, random, quantidade, numerosPorAposta, quantidadeNumerosSorteio);
    }
}
