package application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExemploLista {

	public static void main(String[] args) {
		List<String> nomes = new ArrayList<>();
		
		nomes.add("Jorge");
		nomes.add("Maria");
		nomes.add("Edu");
		nomes.add("Ana");

		for (int i = 0 ; i < nomes.size(); i++) {
			System.out.println(nomes.get(i));
		}
		
		System.out.println();
		for (String lista : nomes) {
			System.out.println(lista);
		}
		
		System.out.println();
		nomes.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);				
			}
		});
		
		System.out.println();
		nomes.forEach(nome -> {			//Lambda expression
			System.out.println(nome);
		});
		
		System.out.println();
		nomes.forEach(nome -> System.out.println(nome));	//Forma resumida
		
		System.out.println();
		nomes.forEach(System.out::println);	//Forma ainda mais resumida 
	}

}
