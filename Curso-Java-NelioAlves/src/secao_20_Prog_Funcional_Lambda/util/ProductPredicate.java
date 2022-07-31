package secao_20_Prog_Funcional_Lambda.util;

import java.util.function.Predicate;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ProductPredicate implements Predicate<Product>{
	
	//A regra é retonar "true" se o "price" for maior igual a 100
	@Override
	public boolean test(Product product) {
		return product.getPrice() >=100.0;
	}
	
}
