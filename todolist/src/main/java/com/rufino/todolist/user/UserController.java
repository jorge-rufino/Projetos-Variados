package com.rufino.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserModelService userModelService;
	
	@GetMapping
	public List<UserModel> listAll() {
		return userModelService.listAll();
	}
	
	@PostMapping
	public UserModel createUser(@RequestBody UserModel userModel) {
		return userModelService.create(userModel);
	}
}
