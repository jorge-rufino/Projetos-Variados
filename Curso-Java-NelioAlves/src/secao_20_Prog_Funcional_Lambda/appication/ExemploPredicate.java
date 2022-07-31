package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ExemploPredicate {

	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();
		
		list.add(new Product("TV", 900.0));
		list.add(new Product("Notebook", 1200.0));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		list.add(new Product("HD Case", 50.0));

//		TODOS OS CODIGOS COMENTADOS FAZEM A MESMA COISA MAS DE FORMAS DIFERENTES
		
//		Exemplo com implementaçao da Interface Predicate
//		Passa como parametro o predicate que criamos e faz o teste para cada elemento da lista, se a condiçao for true ele remove
//		list.removeIf(new ProductPredicate());
		
//		Reference Method com metodo Estatico (Referencia para Metodos) - Syntaxe: Nome_da_Clase::nome_do_metodo
//		list.removeIf(p -> Product.staticProductPredicate(p));	Forma padrao
//		list.removeIf(Product::staticProductPredicate);			Forma resumida
		
//		Reference Method com metodo Não Estatico
//		list.removeIf(p -> p.nonStaticProductPredicate());	
//		list.removeIf(Product::nonStaticProductPredicate);					
		
//		Expressao Lambda Declarada
//		Predicate<Product> pred = p -> p.getPrice() >= 100;		
//		list.removeIf(pred);
		
//		Expressao Lambda inline
		list.removeIf(p -> p.getPrice() >= 100);
		
		for (Product product : list) {
			System.out.println(product);
		}

	}

}

