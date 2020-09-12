package com.yodaplus.todos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yodaplus.todos.model.Tag;
import com.yodaplus.todos.model.Todo;
import com.yodaplus.todos.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagRepository repo;

	@Override
	public List<Todo> getTodoForTag(long id) {
		Optional<Tag> tag= repo.findById(id);

		if(tag.isPresent()) {
			List<Todo> todoList= tag.get().getTodos();
			return todoList;
		}
		return null;
	}

	@Override
	public List<Tag> getAllTags() {
		
		return repo.findAll();
	}

	@Override
	public Tag findTag(String s) {
		return repo.findTag(s);
	}

	@Override
	public Tag addTag(String s) {
		Tag tag= new Tag(s);
		repo.save(tag);
		return tag;
	}

}
