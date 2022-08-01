package entities;

import java.sql.Date;

public class Seller {
	
	private int id;
	private String name;
	private String email;
	private Date birthDate;
	private Double salary;
	private int idDepartment;
	
	public Seller(int id, String name, String email, Date birthDate, Double salary, int idDepartment) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.salary = salary;
		this.idDepartment = idDepartment;
	}
	
	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", salary="
				+ salary + ", idDepartment=" + idDepartment + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}
	
}
