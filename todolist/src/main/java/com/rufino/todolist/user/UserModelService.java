package com.rufino.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
		
		//Primeiro parametro é a complexidade da criptografia, quanto maior mais demora. O padrao recomendado é 12
		var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
		
		userModel.setPassword(passwordHashred);
		
		return repository.save(userModel);
	}
}
