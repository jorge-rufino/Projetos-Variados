package modelo.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.Endereco;
import model.entities.Pessoa;

public interface PessoaDao {
	void insert (Pessoa obj);
	void update (Pessoa obj);
	void deleteById (Integer id);
	Pessoa findById (Integer id);
	List<Pessoa> findAll();
	List<Pessoa> findByEndereco(Endereco endereco);
	List<Pessoa> findByCategoria(Categoria categoria);
}
