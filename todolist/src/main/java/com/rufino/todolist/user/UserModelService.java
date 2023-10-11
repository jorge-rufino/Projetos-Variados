package com.rufino.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserModelService {

	@Autowired
	private UserModelRepository repository;
	
	public List<UserModel> listAll(){
		return repository.findAll();
	}
	
	public UserModel create(UserModel userModel) {
		UserModel existUserModel = repository.findByUsername(userModel.getUsername());
		
		if (existUserModel != null) {			
			return null;
		}
		
		return repository.save(userModel);
	}
}
