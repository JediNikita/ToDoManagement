package com.yodaplus.todos.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yodaplus.todos.model.Todo;
import com.yodaplus.todos.repository.TodoRepository;

@Service
public class TodoServiceImpl implements ITodoService {

	@Autowired
	private TodoRepository repo;
	
	@Override
	public List<Todo> getTodosByUser(String name) {
		
		return repo.findByUsername(name);
	}

	@Override
	public void deleteTodo(long id) {
		Optional<Todo> todo= repo.findById(id);
		
		if(todo.isPresent()) {
			repo.delete(todo.get());
		}
	}

	@Override
	public Optional<Todo> getTodoById(long id) {
		return repo.findById(id);
	}

	@Override
	public void updateTodo(@Valid Todo todo) {
		repo.save(todo);
	}

	@Override
	public void saveTodo(@Valid Todo todo) {
		repo.save(todo);
	}
	
	

}
