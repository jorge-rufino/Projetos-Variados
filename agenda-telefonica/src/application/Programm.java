package application;

import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;

public class Programm {

	public static void main(String[] args) {
		
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDAO();
		
		System.out.println("===========FindAll============");		
		categoriaDao.findAll().forEach(System.out::println);
		
		System.out.println("\n===========FindById============");
		System.out.println(categoriaDao.findById(2));
		
	}

}
