package secao_20_Prog_Funcional_Lambda.services;

import java.util.List;
import java.util.function.Predicate;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class ProductService {
	
//	Recebe um lista de Product e soma os prices de quem começa com a letra T
	public double filteredSum (List<Product> list) {
		double sum = 0.0;
		for (Product product : list) {
			if (product.getName().charAt(0) == 'T') {
				sum += product.getPrice();
			}
		}
		return sum;
	}
	
//	Este método pode receber qualquer parametro/predicate para determinar a soma
	public double filteredSumPlus (List<Product> list, Predicate<Product> criterio) {
		double sum = 0.0;
		for (Product product : list) {
			if (criterio.test(product)) {
				sum += product.getPrice();
			}
		}
		return sum;
	}
}
