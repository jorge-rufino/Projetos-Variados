package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate (Department obj) {
		if (obj.getId() == null) {	//Se o Department nao tem ID significa que é um department novo
			dao.insert(obj);
		}
		else {						//Se o Department já tiver um ID então ele já existe e so vai atualizar
			dao.update(obj);
		}
	}
	
	public void remove (Department obj) {
		dao.deleteById(obj.getId());
	}
}
