package application;

import model.entities.Categoria;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;

public class Programm {

	public static void main(String[] args) {
		
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDAO();
		
		System.out.println("===========FindAll============");		
		categoriaDao.findAll().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(categoriaDao.findById(2));
		
//		System.out.println("\n===========insert============");
//		Categoria novaCategoria = new Categoria(null, "Futebol");
//		categoriaDao.insert(novaCategoria);
//		
//		//Foi instanciado com id=null, se for inserido no banco, vai ter que mostrar o id que foi inserido
//		System.out.println(novaCategoria); 		
		
		System.out.println("\n===========update============");
		Categoria categoria = categoriaDao.findById(4);
		categoria.setNome("Faculdade");
		categoriaDao.update(categoria);
		System.out.println(categoriaDao.findById(4));
		
		System.out.println("\n===========deleteById============");
		categoriaDao.deleteById(4);
		categoriaDao.findAll().stream().forEach(System.out::println);
	}

}
