package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ExemploComparator {

	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();
		
		list.add(new Product("TV", 900.0));
		list.add(new Product("Notebook", 1200.0));
		list.add(new Product("Tablet", 450.0));
		
//		Ordenando a lista pelo Comparable (Só funciona se a classe Product implementar a interface Comparable)
//		Collections.sort(list);

//		Ordenando pela classe criada por fora, MyComparator	
//		list.sort(new MyComparator());
		
//		Criando a "classe anonima" Comparator  
//		Comparator<Product> comp = new Comparator<Product>() {
//
//			@Override
//			public int compare(Product p1, Product p2) {				
//				return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
//			}
//		};
		
//		Definindo uma função anonima com lambda. Compilador consegue identificar que o p1 e p2 sao Products (Conhecida tb como função "Arrow Function")
//		Comparator<Product> comp = (p1, p2) -> {
//			return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
//		};
		
//		Codigo resumido
//		Comparator<Product> comp = (p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		
//		Usando a expressao lambda como argumento so metodo "sort"
		list.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));
		
		for (Product product : list) {
			System.out.println(product);
		}

	}

}

