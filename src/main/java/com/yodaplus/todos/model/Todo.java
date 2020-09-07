package com.yodaplus.todos.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="todo")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	@Size (min=10, message="Enter at least 10 characters")
	private String description;
	
	private Date targetDate;
	
	public Todo() {
		super();
	}

	public Todo(String username, @Size(min = 10, message = "Enter at least 10 characters") String description,
			Date targetDate, boolean isDone) {
		super();
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	
	
}
