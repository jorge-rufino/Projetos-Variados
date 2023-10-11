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
		return repository.save(userModel);
	}
}
