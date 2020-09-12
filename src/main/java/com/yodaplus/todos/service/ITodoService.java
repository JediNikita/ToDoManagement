package com.yodaplus.todos.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yodaplus.todos.model.Tag;
import com.yodaplus.todos.model.Todo;
import com.yodaplus.todos.model.TodoDTO;

public interface ITodoService {

	List<Todo> getTodosByUser(String name);

	void deleteTodo(long id);

	Optional<Todo> getTodoById(long id);

	void updateTodo(@Valid Todo todo);

	void saveTodo(@Valid Todo todo);

	void postponeTodo(String string, String string2);


	void addTodo(@Valid TodoDTO tododto, List<Tag> tagList);


	
}
