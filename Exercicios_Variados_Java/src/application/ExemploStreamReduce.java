package application;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class ExemploStreamReduce {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		
//		---------------REDUCE recebendo apenas uma função----------------
		
//		reduce - soma
		Optional<Integer> soma = list.stream().reduce((n1, n2) -> n1 + n2);		
		System.out.println(soma.get());
		
//		reduce - multiplicacao
		Optional<Integer> multiplicacao = list.stream().reduce((n1, n2) -> n1 * n2);		
		System.out.println(multiplicacao.get());
		
//		reduce - String (Concatenacao)		
		List<String> listStr = Arrays.asList("Jorge","Angela","Nick", "Carlos", "Larissa");
		Optional<String> concatenacao= listStr.stream().reduce((s1, s2) -> s1.concat(s2));
		System.out.println(concatenacao.get());
		concatenacao= listStr.stream().reduce((s1, s2) -> s1 +", " + s2);
		System.out.println(concatenacao.get());
	
//		reduce - subtracao - NÃO FAÇA
//		Não é aconselhavel usar subtracao pois subtracao não é um funçao associativa, ou seja, se dividirmos em blocos, o resultado é diferente
//		(-1-2-3-4-5-6), tem um resultado diferente de (1-2) - (3-4) - (5-6)  
		Optional<Integer> subtracao = list.stream().reduce((n1, n2) -> n1 - n2);		
		System.out.println(subtracao.get());
		System.out.println();
		
//		------------------REDUCE recebendo o argumento "identidade" e uma funçao----------------------
		
//		Identidade é o valor padrão de n1 para que retorne o n2
//		REPARE que agora os resutados do Reduce nao retornam um Optional mas sim o TIPO DA LISTA
		
//		reduce - soma (Identidade da Soma é 0 pois (0 + n2 = n2))
		int soma2 = list.stream().reduce(0, (n1, n2) -> n1 + n2);		
		System.out.println(soma2);
		
//		reduce - multiplicacao (Identidade da Multiplicacao é 1 pois (1 * n2 = n2))
		int multiplicacao2 = list.stream().reduce(1, (n1, n2) -> n1 * n2);		
		System.out.println(multiplicacao2);
		
//		reduce - String (Concatenacao)	(Identidade de String é "" pois ("" + n2 = n2))	
		String concatenacao2= listStr.stream().reduce("",(s1, s2) -> s1.concat(s2));
		System.out.println(concatenacao2);
		
//		reduce - menor valor (Curiosidade)
//		Identidade com o valor de "Double.POSITIVE_INFINITY" SEMPRE vai ser um valor maior do que qualquer elemento da lista 
		double menorValor = DoubleStream.of(1.6, 5.9, 1.2)
				.reduce(Double.POSITIVE_INFINITY , (n1,n2) -> Math.min(n1,n2));
		System.out.println(menorValor);
				
	}

}
