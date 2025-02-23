package com.example.TodoList.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class TodoList {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private int id;
 private String task;
 private boolean completed;
 private LocalDateTime date;
 
 
@ManyToOne
 @JoinColumn(name = "userid", nullable = false)
 private User user;
 
public void setUser(User user) {
    this.user = user;
}

public LocalDateTime getDate() {
	return date;
}
public void setDate(LocalDateTime date) {
	this.date = date;
}
public int getId() {
	return id;
}
public String getTask() {
	return task;
}
public void setTask(String task) {
	this.task = task;
}
public boolean isCompleted() {
	return completed;
}
public void setCompleted(boolean completed) {
	this.completed = completed;
}

}
