package secao_19_GenericsSetMap.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Exercicio02_Map {

	public static void main(String[] args) {
Locale.setDefault(Locale.US);
		
		Map<String, Integer> votos = new TreeMap<>();

		String path = "C:\\temp\\exercicio02-Map.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				
				String key = fields[0];
				int quantidadeVotos = Integer.parseInt(fields[1]);
				
				if (votos.containsKey(key)) {
					quantidadeVotos += votos.get(key);
				}
				
				votos.put(key, quantidadeVotos);
				
				line = br.readLine();
			}
						
			for (String key : votos.keySet()) {
				System.out.println(key + ": " + votos.get(key));
			}
			
			System.out.println("\nMAP NÃO PERMITE OBJETOS REPETIDOS!");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
