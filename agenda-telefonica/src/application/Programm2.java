package application;

import model.entities.Endereco;
import modelo.dao.DaoFactory;
import modelo.dao.EnderecoDao;

public class Programm2 {

	public static void main(String[] args) {
		EnderecoDao enderecoDao  = DaoFactory.createEnderecoDAO();

		System.out.println("===========FindAll============");		
		enderecoDao.findAll().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(enderecoDao.findById(2));
		
//		System.out.println("\n===========Insert============");
//		Endereco novoEndereco = new Endereco(null, "Av. Dr Freitas", "Duque", "Prox ao Bobs", "Belem");
//		enderecoDao.insert(novoEndereco);
//		System.out.println(novoEndereco);
		
		System.out.println("\n===========Update============");
		Endereco endereco = enderecoDao.findById(4);
		endereco.setBairro("Canudos");
		enderecoDao.update(endereco);
		System.out.println(enderecoDao.findById(4));
		
		System.out.println("\n===========deleteById============");
		enderecoDao.deleteById(4);
		enderecoDao.findAll().forEach(System.out::println);
	}

}
