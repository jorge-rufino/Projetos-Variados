package com.rufino.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	public TaskModel create(TaskModel task) {
		return repository.save(task);
	}
	
	public List<TaskModel> findByIdUser(UUID idUser){
		
		return repository.findByIdUser(idUser);		
	}
	
	public TaskModel findById (UUID id) {
		return repository.findById(id).orElse(null);
	}
}
