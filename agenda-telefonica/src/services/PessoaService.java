package services;

import java.util.List;

import model.entities.Pessoa;
import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;

public class PessoaService {
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	
	public List<Pessoa> findAll(){
		return pessoaDao.findAll();
	}
	
	public Pessoa findById(Integer id) {
		return pessoaDao.findById(id);
	}
	
	public void deleteById(Integer id) {
		pessoaDao.deleteById(id);
	}
}
