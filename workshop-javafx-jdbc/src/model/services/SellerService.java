package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate (Seller obj) {
		if (obj.getId() == null) {	//Se o Seller nao tem ID significa que é um department novo
			dao.insert(obj);
		}
		else {						//Se o Seller já tiver um ID então ele já existe e so vai atualizar
			dao.update(obj);
		}
	}
	
	public void remove (Seller obj) {
		dao.deleteById(obj.getId());
	}
}
