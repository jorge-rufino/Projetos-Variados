package application;

import db.Crud;
import db.DB;
import util.Util;

public class Programm {

	public static void main(String[] args) {
		String name = Util.inputString("Name:");
		String email = Util.inputString("Email");
		String data = Util.inputString("Data (dd/MM/yyyy):");
		double salary = Util.inputDouble("Salary:");
		int idDepartment = Util.inputInt("Id Department:");
		
		Crud.addSeller(name, email, data, salary, idDepartment);
		
		System.out.println("Dados das tabelas:");
		
		Crud.listaDados().forEach(System.out::println);
		
		DB.closeConnections();
	}

}

