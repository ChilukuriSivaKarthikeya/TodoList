package com.example.TodoList.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import com.example.TodoList.Model.TodoList;
import com.example.TodoList.Model.User;
import com.example.TodoList.Service.JwtService;
import com.example.TodoList.Service.TodoListService;


@RestController
public class TodoListController {
	@Autowired
    private JwtService jwtService;
	

    @Autowired
    private AuthenticationManager authenticationManager;
    
    private final TodoListService ts;
	@Autowired
    public TodoListController(TodoListService ts) {
        this.ts = ts;
    }
	@PostMapping("/addtask")
	public String addTask(@RequestBody TodoList todolist) {
		return ts.addtasks(todolist);
	}
	@GetMapping("/gettasks")
	public List<TodoList> getTask() {
		return ts.findTasks();
	}
	@PutMapping("/updatetask/{id}")
	public TodoList updateTask(@RequestBody TodoList todolist) {
		return ts.updatetasks(todolist);
	}
	@DeleteMapping("/deletetask/{id}")
	public String deletetask(@PathVariable long id) {
		return ts.deleteTask(id);
	}
	
	@PostMapping("/new")
    public ResponseEntity<String> addNewUser(@RequestBody User userInfo) {
        return ts.addUser(userInfo);
    }

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody User authRequest) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            String token = jwtService.generateToken(authRequest.getName());
	            return ResponseEntity.ok(token);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	        }
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}
}
