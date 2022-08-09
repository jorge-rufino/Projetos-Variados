package modelo.dao;

import db.DB;
import model.dao.implem.CategoriaDaoJDBC;
import model.dao.implem.EnderecoDaoJDBC;

public class DaoFactory {
	
	public static CategoriaDao createCategoriaDAO() {
		return new CategoriaDaoJDBC(DB.getConnetcion());
	}
	
	public static EnderecoDao createEnderecoDAO() {
		return new EnderecoDaoJDBC(DB.getConnetcion());
	}
}
