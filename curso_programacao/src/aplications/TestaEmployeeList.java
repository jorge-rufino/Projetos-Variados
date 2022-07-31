package aplications;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class TestaEmployeeList {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();
		
		System.out.println("How many employees will be registered? ");
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			System.out.println("Employee #"+(i + 1)+":");
			System.out.print("Id:");
			int id = sc.nextInt();
			
			while(foundId(list, id) != null) {
				System.out.print("Id already taken! Try again:");
				id = sc.nextInt();
			}
			
			System.out.print("Name:");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Salary:");
			double salary = sc.nextDouble();
			
			list.add(new Employee(name, id, salary));
		}
		
		System.out.println();
		System.out.print("Enter the employee Id that will have salary increase:");
		int id = sc.nextInt();
		
		if (foundId2(list, id) == null) {
			System.out.println("ID not exist!");			
		}
		else {
			System.out.print("Enter the percentage:");
			double percent = sc.nextDouble();
			foundId2(list, id).increaseSalary(percent);
		}
		System.out.println();
		System.out.println("List of Employees:");
		for (Employee obj : list) {
			System.out.println(obj);
		}
		
		sc.close();

	}
	
	public static Employee foundId(List<Employee> list, int id) {	// Ambos os metodos fazem a mesma coisa, encontram o Objeto pelo ID
																	//e retornam o Objeto se existir ou "null"		
		Employee emp = null;
		for (Employee obj : list) {
			if (id == obj.getId()) {
				emp = obj;
			}
		}
		return emp;
	}
	
	public static Employee foundId2(List<Employee> list, int id) {
		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);	// filtra com base no predicado lambda	
		return emp;
	}

}
