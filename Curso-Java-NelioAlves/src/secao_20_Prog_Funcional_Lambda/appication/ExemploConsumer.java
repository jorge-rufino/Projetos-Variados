package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ExemploConsumer {

	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();
		
		list.add(new Product("TV", 900.0));
		list.add(new Product("Notebook", 1200.0));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		list.add(new Product("HD Case", 50.0));

//		TODOS OS CODIGOS COMENTADOS FAZEM A MESMA COISA MAS DE FORMAS DIFERENTES
		
//		Exemplo com implementação da Interface Consumer
//		list.forEach(new PriceUpdateConsumer());
		
//		Reference Method estático
//		list.forEach(p -> Product.staticUpdatePrice(p));
//		list.forEach(Product::staticUpdatePrice);
		
//		Reference Method Não estático
//		list.forEach(p -> p.nonStaticUpdatePrice());
//		list.forEach(Product::nonStaticUpdatePrice);
		
//		Expressao Lambda Declarada - Poderia ser em uma linha só mas usei chaves para demonstrar
//		double factor = 1.1;
//		Consumer<Product> consumer = p -> {
//			p.setPrice(p.getPrice() * factor);
//		};		
//		list.forEach(consumer);
		
//		Expressao Lambda inline
		list.forEach(p -> p.setPrice(p.getPrice() * 1.1));
		
		list.forEach(System.out::println);

	}

}

