package com.yodaplus.todos.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yodaplus.todos.model.Tag;
import com.yodaplus.todos.model.Todo;
import com.yodaplus.todos.model.TodoDTO;
import com.yodaplus.todos.service.ITodoService;
import com.yodaplus.todos.service.TagService;

@Controller
public class TodoController {

	@Autowired
	private ITodoService todoService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	Tag tag;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUserName(model));
		return "welcome";
	}
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getTodosByUser(name));
		// model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	public String getLoggedInUserName(ModelMap model) {
		Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		
		return principal.toString();
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.addAttribute("todoDTO", new TodoDTO());
		model.addAttribute("username", name);
		return "todo";
	}

	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid TodoDTO tododto, BindingResult result) {
		String name = getLoggedInUserName(model);
		List<Tag> tagList= new ArrayList<Tag>();
		
		if(result.hasErrors()) {
			return "todo";
		}
		List<String> tagString=Arrays.asList(tododto.getTags().split(","));
		for(String s:tagString) {
			tag= tagService.findTag(s);
			if(tag==null) {
				tagService.addTag(s);
				tagList.add(tag);
			}else
				tagList.add(tag);
		}
		tododto.setUsername(name);
		todoService.addTodo(tododto, tagList);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
		
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdatePage(@RequestParam long id, ModelMap model) {
		Todo todo= todoService.getTodoById(id).get();
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		
		todo.setUsername(getLoggedInUserName(model));
		todoService.saveTodo(todo);
		return "redirect:/list-todos";
	}
	
	
	@RequestMapping(value="/showTodosForTags", method=RequestMethod.GET)
	public String showTodosForTags(@RequestParam long id, ModelMap model) {
		List<Todo> todoList= tagService.getTodoForTag(id);
		model.put("todoList", todoList);
		return "todosForTags";
	}
	
	@RequestMapping(value="/postponeTodo", method=RequestMethod.POST)
	public String postponeTodo(@RequestBody Map<String, String> paramMap ) {
		todoService.postponeTodo(paramMap.get("postponeDays"),paramMap.get("todoId") );
		//model.put("todoList", todoList);
		return "list-todos";
	}
}