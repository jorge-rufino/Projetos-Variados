package modelo.dao;

import db.DB;
import model.dao.implem.CategoriaDaoJDBC;

public class DaoFactory {
	
	public static CategoriaDao createCategoriaDAO() {
		return new CategoriaDaoJDBC(DB.getConnetcion());
	}
}
