package com.gp.learners.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gp.learners.entities.User;
import com.gp.learners.repositories.UserRepository;
import com.gp.learners.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*",maxAge=3600)
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("login/{email}/{password}")
	public Object loginUser( @PathVariable("email") String email,@PathVariable("password") String password) {
		
		Object user=userService.isValidLogin(email, password);
		
		if(user != null) {
			return user;
		}
		else {
			//no such a user
			return "false";
		}
	}
	
	@PostMapping("user/register")
	public User userRegister(@RequestBody User user) {
		System.out.println(user);
		return userRepository.save(user);
		
	}
	
	//delete user
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> userDelete(@PathVariable("id") Integer id){
		
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
