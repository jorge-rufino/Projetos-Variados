package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ExemploFunction {

	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();
		
		list.add(new Product("TV", 900.0));
		list.add(new Product("Notebook", 1200.0));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		list.add(new Product("HD Case", 50.0));

//		TODOS OS CODIGOS COMENTADOS FAZEM A MESMA COISA MAS DE FORMAS DIFERENTES
		
//		Para aplicar a Function, precisamos converter a lista em "stream", aplicar a function e depois converter o stream e lista novamente
//		Repare que a function "UpperCaseNameFunction" retorna String, criando assim um stream de String, por isso precisamos de uma nova lista
				
//		Exemplo Interface de Function
//		List<String> nomes = list.stream().map(new UpperCaseNameFunction()).collect(Collectors.toList());
		
//		Reference Method estático
//		List<String> nomes = list.stream().map(product -> Product.staticUpperCaseNameFunction(product)).collect(Collectors.toList());
//		List<String> nomes = list.stream().map(Product::staticUpperCaseNameFunction).collect(Collectors.toList());
		
//		Reference Method Não estático
//		List<String> nomes = list.stream().map(p -> p.nonStaticUpperCaseNameFunction()).collect(Collectors.toList());
//		List<String> nomes = list.stream().map(Product::nonStaticUpperCaseNameFunction).collect(Collectors.toList());
		
//		Expressao Lambda Declarada - Poderia ser em uma linha só mas usei chaves para demonstrar
//		Function<Product,String> function = p -> p.getName().toUpperCase();
//		List<String> nomes = list.stream().map(function).collect(Collectors.toList());
		
//		Expressao Lambda inlines
		List<String> nomes = list.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList());
		
		nomes.forEach(System.out::println);

	}

}

