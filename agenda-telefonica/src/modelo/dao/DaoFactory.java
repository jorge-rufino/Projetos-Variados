package modelo.dao;

import db.DB;
import model.dao.implem.CategoriaDaoJDBC;
import model.dao.implem.EnderecoDaoJDBC;
import model.dao.implem.PessoaDaoJDBC;
import model.dao.implem.TelefoneDaoJDBC;

public class DaoFactory {
	
	public static CategoriaDao createCategoriaDAO() {
		return new CategoriaDaoJDBC(DB.getConnetcion());
	}
	
	public static EnderecoDao createEnderecoDAO() {
		return new EnderecoDaoJDBC(DB.getConnetcion());
	}
	
	public static PessoaDao createPessoaDao() {
		return new PessoaDaoJDBC(DB.getConnetcion());
	}
	
	public static TelefoneDao createTelefoneDao() {
		return new TelefoneDaoJDBC(DB.getConnetcion());
	}
}
