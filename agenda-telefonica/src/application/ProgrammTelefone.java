package application;

import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
import modelo.dao.TelefoneDao;

public class ProgrammTelefone {

	public static void main(String[] args) {
		TelefoneDao telefoneDao = DaoFactory.createTelefoneDao();
		
		System.out.println("===========FindAll============");		
		telefoneDao.findAll().stream().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(telefoneDao.findById(3));
		
		System.out.println("\n===========FindByPessoa============");	
//		Pessoa pessoa = DaoFactory.createPessoaDao().findById(1);
//		System.out.println(telefoneDao.findByPessoa(pessoa));
		System.out.println(telefoneDao.findByPessoa(new Pessoa(1, null, null, null, null, null, null, null, null)));
		
//		System.out.println("\n===========insert============");
//		Pessoa pessoa = DaoFactory.createPessoaDao().findById(2);
//		Telefone novoTelefone = new Telefone(null, "091", "98542-2569", "celular", pessoa);
//		telefoneDao.insert(novoTelefone);
//		System.out.println(novoTelefone);
		
		System.out.println("\n===========update============");
		Telefone telefone = telefoneDao.findById(5);
		telefone.setDdd("011");
		telefone.setPessoa(DaoFactory.createPessoaDao().findById(3));
		telefoneDao.update(telefone);
		System.out.println(telefoneDao.findById(5));
		
		System.out.println("\n===========deleteById============");
		telefoneDao.deleteById(2);
		telefoneDao.findAll().stream().forEach(System.out::println);
	}

}
