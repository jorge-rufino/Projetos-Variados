package secao_19_GenericsSetMap.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import secao_19_GenericsSetMap.entities.Product;
import secao_19_GenericsSetMap.services.CalculationService;

public class TestaCalculationService {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		List<Product> list = new ArrayList<>();
		List<String> listString = new ArrayList<>();
		List<Double> listDouble = new ArrayList<>();

		String path = "C:\\temp\\in3.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));

				listString.add(fields[0]);
				listDouble.add(Double.parseDouble(fields[1]));

				line = br.readLine();
			}

			Product x = CalculationService.max(list);
			// O metodo "max" é generico e portanto pode receber listas de qualquer tipo e
			// retonar o maior
			String s = CalculationService.max(listString);
			Double d = CalculationService.max(listDouble);

			System.out.println("Items of list:");
			for (Product product : list) {
				System.out.println(product);
			}

			System.out.println();
			System.out.println("Most expensive product:");
			System.out.println(x);
			System.out.println();
			System.out.println("Most String:");
			System.out.println(s);
			System.out.println();
			System.out.println("Most double:");
			System.out.println(d);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
