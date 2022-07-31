package secao_18_Interfaces.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import secao_18_Interfaces.entities.Employee;

public class ProgramInterfaceComparable2 {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>();
		String path = "C:\\temp\\in2.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String employeeCsv = br.readLine();
			while (employeeCsv != null) {
				String[] fields = employeeCsv.split(",");
				list.add(new Employee(fields[0], Double.parseDouble(fields[1])));
				employeeCsv = br.readLine();
			}
			Collections.sort(list);		//Ordena os dados de uma coleção/lista
			for (Employee emp : list) {
				System.out.println(emp.getName()+", "+emp.getSalary());
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
