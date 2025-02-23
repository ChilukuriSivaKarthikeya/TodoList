package com.example.TodoList.Service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.TodoList.Model.TodoList;
import com.example.TodoList.Model.User;
import com.example.TodoList.Repository.TodoListRepository;
import com.example.TodoList.Repository.UserRepository;

@Service
public class TodoListService {
	
	private final TodoListRepository tr;
	@Autowired
	private UserRepository ur;
	 @Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public TodoListService(TodoListRepository todoListRepository) {
	        this.tr = todoListRepository;
	}
	public User getuser() {
		User user=null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
	        user = ur.findByName(username).orElse(null);
	    }
	    return user;
	}
	public List<TodoList> findTasks() {
		
		User user=getuser();  
		if(user!=null)
	        return tr.findByUser(user);
		else
	       return Collections.emptyList();
	}
	public String addtasks(TodoList todolist) {
		
	      User user = getuser();
		  todolist.setUser(user); 
	      tr.save(todolist);
	      return "Successfully added task";
	}        
    public TodoList updatetasks(TodoList todolist) {
		
    	 User user = getuser();
		 todolist.setUser(user); 
	     tr.save(todolist);
		 return tr.save(todolist);
	}
	public String deleteTask(long id) {
		
		tr.deleteById(id);
		return "deleted task";
	}
	 public ResponseEntity<String> addUser(User userInfo) {
		    
		 if (ur.existsByName(userInfo.getName())) {
		        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		    }
		    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

		    try {
		        ur.save(userInfo);
		        return ResponseEntity.status(HttpStatus.CREATED).body("User added to system");
		    } catch (Exception ex) {
		        //
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding user: " + ex.getMessage());
		    }
	 }

}
