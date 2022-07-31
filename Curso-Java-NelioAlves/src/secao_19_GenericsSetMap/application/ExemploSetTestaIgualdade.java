package secao_19_GenericsSetMap.application;

import java.util.HashSet;
import java.util.Set;

import secao_19_GenericsSetMap.entities.Product;

public class ExemploSetTestaIgualdade {

	public static void main(String[] args) {
		Set<Product> set = new HashSet<>();
		set.add(new Product("TV", 900.0));
		set.add(new Product("Notebook", 1200.0));
		set.add(new Product("Tablet", 400.0));
		
		Product prod = new Product("Notebook", 1200.0);
		
		//Vai retornar "false" se a classe Product nao tiver os metodos Hashocode e Equals implementados pois serão comparadas as referencias dos objetos
		//Vai retornar "true" caso os metodos estejam implementados pois assim será feita a comparação por conteúdo e não mais por referencia
		System.out.println(set.contains(prod));

	}

}
