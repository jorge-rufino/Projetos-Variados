package services;

import java.util.List;

import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;
import modelo.dao.TelefoneDao;

public class PessoaService {
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	TelefoneDao telefoneDao = DaoFactory.createTelefoneDao();
	
	public List<Pessoa> findAll(){
		return pessoaDao.findAll();
	}
	
	public Pessoa findById(Integer id) {
		return pessoaDao.findById(id);
	}
	
	public void deleteById(Integer id) {
		pessoaDao.deleteById(id);
	}
	
	public void salvaContato(Pessoa pessoa, Telefone telefone) {
		if (pessoa.getId() == null) {			
			pessoaDao.salvaPessoaEnderecoTelefone(pessoa, telefone);
		}
		else {
			pessoaDao.update(pessoa);
			telefoneDao.update(telefone);
		}
	}
}
