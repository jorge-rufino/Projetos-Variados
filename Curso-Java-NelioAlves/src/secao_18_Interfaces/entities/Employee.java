package secao_18_Interfaces.entities;

//Para poder order uma Lista de Objetos de classes criadas pelo programador, através do "Collection.sort", é necessário 
//implementar a classe "Comparable<nome_da_classe>"
public class Employee implements Comparable<Employee>{
	private String name;
	private Double salary;
	public Employee(String name, Double salary) {		
		this.name = name;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public int compareTo(Employee other) {			//Como queremos ordenar pelo nome, então fazemos a comparaçao pelos nomes dos funcionarios	
													//Em relação a ordem alfabetica, segue os resultados abaixo:
													//Se o nome for maior que o outro 	=> retorna um inteiro positivo
													//Se o nome for menor que o outro 	=> retorna um inteiro negativo
													//Se o nome for igual ao outro		=> retorna zero
		
		//return salary.compareTo(other.getSalary());	Caso quisessemos ordernar pelo salario
		//return -name.compareTo(other.getName());		Caso quisessemos ordernar pelo nome em ordem descrescente
		//return -salary.compareTo(other.getSalary());	Caso quisessemos ordernar pelo salario em ordem descrescente
		return name.compareTo(other.getName());
	}
	
}	
