package com.rufino.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		taskModel.setIdUser((UUID) idUser);
		
		var currentDate = LocalDateTime.now();
		
		if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas de inicio ou fim devem ser maior que a data atual.");
		}
		
		if(taskModel.getEndAt().isBefore(taskModel.getStartAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de fim deve ser maior que a data inicial.");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));
	}
	
	@GetMapping
	public List<TaskModel> list(HttpServletRequest request){
		var idUser = request.getAttribute("idUser");
		
		return taskRepository.findByIdUser((UUID) idUser);
	}
	
	@PutMapping("/{id}")
	public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		taskModel.setIdUser((UUID) idUser);
		taskModel.setId(id);
		taskModel.setCreateAt(LocalDateTime.now());
		
		return taskRepository.save(taskModel);
	}
}
