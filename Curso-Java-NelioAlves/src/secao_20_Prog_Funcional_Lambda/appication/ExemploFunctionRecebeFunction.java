package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import secao_20_Prog_Funcional_Lambda.entities.Product;
import secao_20_Prog_Funcional_Lambda.services.ProductService;

public class ExemploFunctionRecebeFunction {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<Product>();
		
		list.add(new Product("TV", 900.0));
		list.add(new Product("Notebook", 1200.0));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		list.add(new Product("Mouse", 50.0));

		ProductService ps = new ProductService();
		
		double sum = ps.filteredSum(list);
		
		System.out.println("Sum = " + String.format("%.2f", sum));
		System.out.println();
		
		//Soma dos produtos que começam com letra T e M
		sum = ps.filteredSumPlus(list, p -> p.getName().charAt(0) == 'T' || p.getName().charAt(0) == 'M');
		System.out.println("Sum = " + String.format("%.2f", sum));
	}

}

