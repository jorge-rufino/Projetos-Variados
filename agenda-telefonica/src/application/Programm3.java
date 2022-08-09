package application;

import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;

public class Programm3 {

	public static void main(String[] args) {
		PessoaDao pessoaDao = DaoFactory.createPessoaDao();
		
		System.out.println("===========FindAll============");
		pessoaDao.findAll().stream().forEach(System.out::println);

	}

}
