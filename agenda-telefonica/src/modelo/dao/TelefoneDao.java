package modelo.dao;

import java.util.List;

import model.entities.Pessoa;
import model.entities.Telefone;

public interface TelefoneDao {
	void insert (Telefone obj);
	void update (Telefone obj);
	void deleteById (Integer id);
	Telefone findById (Integer id);
	List<Telefone> findAll();	
	List<Telefone> findByPessoa(Pessoa pessoa);
}
