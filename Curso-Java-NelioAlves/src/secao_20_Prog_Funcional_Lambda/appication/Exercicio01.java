package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class Exercicio01 {

//	Dada uma lista de Produtos, mostrar a media e listar no nome em ordem decrescente dos produtos que tiverem preço abaixo da media
	
	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();

		list.add(new Product("TV", 900.0));		
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		list.add(new Product("Mouse", 50.0));
		list.add(new Product("Computer", 850.0));
		list.add(new Product("Monitor", 290.0));
		
		double avgPrice = list.stream()
				.map(p -> p.getPrice())
				.reduce(0.0, (n1,n2) -> n1 + n2) / list.size();
		
//		Outra forma de fazer a media dos preços
//		double avgPrice = list.stream()
//				.map(p -> p.getPrice())
//				.collect(Collectors.averagingDouble(p -> p.doubleValue()));
		
		System.out.println("Average price: " + String.format("%.2f", avgPrice));
		
		Comparator<String> comparator = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		List<String> names = list.stream().filter(p -> p.getPrice() <= avgPrice).map(Product::getName)
		.sorted(comparator.reversed())			//Inverte a ordem da lista, nesse caso tornando Decrescentes		
		.collect(Collectors.toList());
		
		names.forEach(System.out::println);
		
		System.out.println();
		
		//Faz o mesmo que as linhas acima
		list.stream()
		.filter(p -> p.getPrice() <= avgPrice)	//filtro os produtos com preço menor que a média
		.map(p -> p.getName())					//pegou somente os nomes trasforomando a Stream em Stream de Strings
		.sorted((p2, p1) -> p1.toUpperCase().compareTo(p2.toUpperCase()))	//Ordenou em ordem decrescente colocando p2 como primeiro argumento
		.forEach(System.out::println);			//chama o println para mostrar cada elemento da lista
	}

}
