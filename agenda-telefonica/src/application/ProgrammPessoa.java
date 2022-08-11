package application;

import model.entities.Pessoa;
import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;

public class ProgrammPessoa {
	
	public static void main (String args[]) {
		PessoaDao pessoaDao = DaoFactory.createPessoaDao();
		
		System.out.println("===========FindAll============");
		pessoaDao.findAll().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(pessoaDao.findById(3));
		
//		System.out.println("\n===========insert============");		
//		Endereco novoEndereco = new Endereco(null, "Av Arterial 18, 520", "Coqueiro", "Cidade Nova V", "Ananindeua");
//		DaoFactory.createEnderecoDAO().insert(novoEndereco);
//		
//		Pessoa novaPessoa = new Pessoa(null, "Erika Campos", "tia Erika", "542.856.841-55", "f", "erika@gmail.com", 
//				new Date(), DaoFactory.createCategoriaDAO().findById(3), novoEndereco);
//		
//		pessoaDao.insert(novaPessoa);
//		System.out.println(novaPessoa);
		
		System.out.println("\n===========update============");
		Pessoa pessoa = pessoaDao.findById(2);
		pessoa.setApelido("");
	}
}
