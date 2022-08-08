package modelo.dao;

import java.util.List;

import model.entities.Endereco;

public interface EnderecoDao {
	void insert (Endereco obj);
	void update (Endereco obj);
	void deleteById (Integer id);
	Endereco findById (Integer id);
//	List<Endereco> findByFields(String name, String email, Double salary, String depName);	
	List<Endereco> findAll();
}
