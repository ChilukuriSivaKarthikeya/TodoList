package com.example.TodoList.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String password;
	
	@OneToMany(mappedBy = "user")
    private List<TodoList> tasks;
	
	public List<TodoList> getTasks() {
		return tasks;
	}
	public void setTasks(List<TodoList> tasks) {
		this.tasks = tasks;
		for (TodoList task : tasks) {
	        task.setUser(this);
	    }
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
