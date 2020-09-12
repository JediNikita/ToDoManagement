package com.yodaplus.todos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yodaplus.todos.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {


	List<Todo> findByUsername(String name);

	

	

}
