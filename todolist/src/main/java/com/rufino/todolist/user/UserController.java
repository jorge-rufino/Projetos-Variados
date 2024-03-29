package com.rufino.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
		
		UserModel newUser = userModelService.create(userModel);
		
		if (newUser != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");		
	}
}
