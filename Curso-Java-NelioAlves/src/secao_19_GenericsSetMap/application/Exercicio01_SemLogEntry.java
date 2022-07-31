package secao_19_GenericsSetMap.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import secao_19_GenericsSetMap.entities.LogEntry;

public class Exercicio01_SemLogEntry {

	public static void main(String[] args) {
Locale.setDefault(Locale.US);

//Como precisamos somente contar a quantidade de usuários, não importa a ordem então usamos o Hashset por ser o mais rápido apesar de nao ordenar
		Set<LogEntry> logs = new HashSet<>();
				
		String path = "C:\\temp\\exercicio01.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(" ");
				Date moment = Date.from(Instant.parse(fields[1]));
				
				logs.add(new LogEntry(fields[0], moment));				
				
				line = br.readLine();
			}
						
			System.out.println("Total of users: " + logs.size());
						
			System.out.println("\nSET NÃO PERMITE OBJETOS REPETIDOS DESDE QUE ESTEJA IMPLEMENTADO O EQUALS E HASHCODE!");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
