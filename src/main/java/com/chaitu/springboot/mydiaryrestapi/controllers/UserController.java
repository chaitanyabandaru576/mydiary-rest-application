package com.chaitu.springboot.mydiaryrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaitu.springboot.mydiaryrestapi.entities.User;
import com.chaitu.springboot.mydiaryrestapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		
		List<User> users=userService.findAll();
		
		
		return users;
		
	}
	
	@PostMapping("/")
	public User InsertUser(@RequestBody User user) {
		User savedUser=userService.saveUser(user);
		
		
		return savedUser;
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		
		User updatedUser=userService.updateUser(user);
		return updatedUser;
	}
	
	@PutMapping("/{id}")
	public User updateUser1(@PathVariable("id") int id,@RequestBody User user) {
		
		User user1=userService.findById(id);//entry1 is from DB
		
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		
		User updatedUser=userService.saveUser(user1);
		return updatedUser;
	}
	
	@PatchMapping("/{id}")
	public User updateUser2(@PathVariable("id") int id,@RequestBody User user) {
		
		User user1=userService.findById(id);//entry1 is from DB
		
		
		String username=user.getUsername();
		String password=user.getPassword();
		if(username!=null && username.length()>0)
			user1.setUsername(username);
		if(password!=null && password.length()>0)
			user1.setPassword(password);
		
		User updatedUser=userService.saveUser(user1);
		return updatedUser;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") int id) {
		User user=userService.findById(id);
		return user;
		
	}
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id")int id) {
		
		User user=userService.findById(id);
		
		
		userService.deleteUser(user);
	}
	
	@GetMapping("/user/{username}")
	public User getUserDetails(@PathVariable("username")String userName) {
		
		User user=userService.findByUsername(userName);
		return user;
	}

}
