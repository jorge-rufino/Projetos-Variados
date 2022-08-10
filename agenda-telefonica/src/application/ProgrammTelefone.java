package application;

import model.entities.Pessoa;
import modelo.dao.DaoFactory;
import modelo.dao.TelefoneDao;

public class ProgrammTelefone {

	public static void main(String[] args) {
		TelefoneDao telefoneDao = DaoFactory.createTelefoneDao();
		
		System.out.println("===========FindAll============");		
		telefoneDao.findAll().stream().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(telefoneDao.findById(4));
		
		System.out.println("\n===========FindByPessoa============");	
		Pessoa pessoa = DaoFactory.createPessoaDao().findById(3);
		System.out.println(telefoneDao.findByPessoa(pessoa));
//		System.out.println(telefoneDao.findByPessoa(new Pessoa(1, null, null, null, null, null, null, null, null)));
	}

}
