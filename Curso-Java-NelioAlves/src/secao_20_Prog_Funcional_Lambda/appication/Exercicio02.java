package secao_20_Prog_Funcional_Lambda.appication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import application.Util;
import secao_20_Prog_Funcional_Lambda.entities.Employee;

public class Exercicio02 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee("Maria", "maria@gmail.com", 3200.0));
		list.add(new Employee("Alex", "alex@gmail.com", 1900.0));
		list.add(new Employee("Marco", "marco@gmail.com", 1700.0));
		list.add(new Employee("Bob", "bob@gmail.com", 3500.0));
		list.add(new Employee("Anna", "anna@gmail.com", 2800.0));
		
		double salary = Util.inputDouble("Enter salary:");
		System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary));
		
		list.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail()).sorted().forEach(System.out::println);

		double sum = list.stream()
				.filter(e -> e.getName().charAt(0) == 'M')
				.map(e -> e.getSalary())
				.reduce(0.0, (n1,n2) -> n1 + n2);
		
//		Outra forma de fazer a somatoria
//		double sum = list.stream()
//				.filter(e -> e.getName().charAt(0) == 'M')
//				.map(e -> e.getSalary())
//				.collect(Collectors.summingDouble(e -> e.doubleValue()));
		
		System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
		System.exit(0);
	}

}
