package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdernaStrings {

	public static void main(String[] args) {
		
//		Cria uma lista IMUTAVEL, ou seja, não é possível adicionar mais elementos
		List<String> palavrasImutavel = Arrays.asList("Maria é gentil","Pedro é o marido de Maria","Antonio é carinhoso");		
		
//		Se tentar rodar com a linha abaixo, vai disparar uma exception
//		palavrasImutavel.add("Joao");
		
//		Criou uma lista MUTAVEL, ou seja, que pode ser alterada com base na lista acima.
		List<String> palavras = new ArrayList<>(palavrasImutavel);
		palavras.add("Jorge");
		
//		Ordena por ordem alfabetica devido ser String
		Collections.sort(palavras);		
		System.out.println(palavras);
		
		Comparator<String> comparador = new ComparadorPorTamanho();
//		Collections.sort(palavras, comparador);
		
		palavras.sort(comparador);
		System.out.println(palavras);
		
		for (String palavra : palavras) {
			System.out.println(palavra);
		}
		System.out.println();
		Consumer<String> consumidor = new ImprimeNaLinha();
		palavras.forEach(consumidor);
	}

}

class ImprimeNaLinha implements Consumer<String>{

	@Override
	public void accept(String string) {
		System.out.println(string);		
	}
	
}

//		Se o s1 vier antes do s2, deve retornar um numero negativo
//		Se o s1 vier depois do s2, deve retornar um numero positivo
//		Se forem iguais, deve retornar zero
class ComparadorPorTamanho implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) {
		
		if(s1.length() < s2.length())
			return -1;
		
		if(s1.length() > s2.length())
			return 1;
		
		return 0;
	}
	
}