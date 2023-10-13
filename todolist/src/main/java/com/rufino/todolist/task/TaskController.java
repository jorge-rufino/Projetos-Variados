package com.rufino.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rufino.todolist.util.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody TaskModel taskInput, HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		taskInput.setIdUser((UUID) idUser);
		
		var currentDate = LocalDateTime.now();
		
		if(currentDate.isAfter(taskInput.getStartAt()) || currentDate.isAfter(taskInput.getEndAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas de inicio ou fim devem ser maior que a data atual.");
		}
		
		if(taskInput.getEndAt().isBefore(taskInput.getStartAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de fim deve ser maior que a data inicial.");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskInput));
	}
	
	@GetMapping
	public List<TaskModel> list(HttpServletRequest request){
		var idUser = request.getAttribute("idUser");
		
		return taskRepository.findByIdUser((UUID) idUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TaskModel taskInput, @PathVariable UUID id, HttpServletRequest request) {
		
		TaskModel task = taskRepository.findById(id).orElse(null);
		
		var idUser = request.getAttribute("idUser");
		
		if(task == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não existe.");
		}
		
		if(!task.getIdUser().equals(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar a tarefa.");
		}
				
		BeanUtils.copyProperties(taskInput, task,"id", "idUser", "createAt");
		
		return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(task));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateParcial(@RequestBody TaskModel taskInput, @PathVariable UUID id, HttpServletRequest request) {
		
		TaskModel task = taskRepository.findById(id).orElse(null);
		
		var idUser = request.getAttribute("idUser");
		
		if(task == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não existe.");
		}
		
		if(!task.getIdUser().equals(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar a tarefa.");
		}
		
		Utils.copyNonNullProperties(taskInput, task);
	
		return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(task));
	}
}
