package com.yodaplus.todos.service;

import java.util.List;

import com.yodaplus.todos.model.Tag;
import com.yodaplus.todos.model.Todo;

public interface TagService {

	List<Todo> getTodoForTag(long id);

	List<Tag> getAllTags();

	Tag findTag(String s);

	Tag addTag(String s);

}
