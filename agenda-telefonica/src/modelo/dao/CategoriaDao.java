package modelo.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {
	void insert (Categoria obj);
	void update (Categoria obj);
	void deleteById (Integer id);
	Categoria findById (Integer id);
//	List<Endereco> findByFields(String name, String email, Double salary, String depName);	
	List<Categoria> findAll();
}
