package com.example.TodoList.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TodoList.Model.TodoList;
import com.example.TodoList.Model.User;


public interface TodoListRepository extends JpaRepository<TodoList,Long> {
	List<TodoList> findByUser(User user);
}
