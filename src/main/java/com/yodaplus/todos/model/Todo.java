package com.yodaplus.todos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	@Temporal(TemporalType.DATE)
	private Date targetDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="todo_tags", 
	joinColumns = { @JoinColumn(name="todo_id")},
	inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	List<Tag> tags = new ArrayList<>();
	
	public Todo() {
		super();
	}

	public Todo(String username, @Size(min = 10, message = "Enter at least 10 characters") String description,
			Date targetDate, List<Tag> tags, boolean isDone) {
		super();
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.tags=tags;
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	

	
	
	
	
}
