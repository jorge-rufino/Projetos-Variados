package secao_19_GenericsSetMap.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Exercicio01_ComLogEntry {

	public static void main(String[] args) {
Locale.setDefault(Locale.US);
		
		Set<String> nomesSet = new TreeSet<String>();
		List<String> nomesList = new ArrayList<String>();
		
		String path = "C:\\temp\\exercicio01.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(" ");
				nomesSet.add(fields[0]);
				nomesList.add(fields[0]);
				
				line = br.readLine();
			}
						
			System.out.println("Nomes do Set:");
			nomesSet.forEach(System.out::println);
			System.out.println("\nNomes da Lista:");
			nomesList.forEach(System.out::println);
			
			System.out.println("\nSET NÃO PERMITE OBJETOS REPETIDOS!");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
