package com.example.TodoList.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TodoList.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{
 
	Optional<User> findByName(String username);
	Boolean existsByName(String username);
}
