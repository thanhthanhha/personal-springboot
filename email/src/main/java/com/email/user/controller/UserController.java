package com.email.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import com.email.user.dto.User;
import com.email.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Get all users", description = "Retrieves a list of all registered users")
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() throws Exception  {
		return userService.getUsers();
	}
	
	@Operation(summary = "Get user by id", description = "Retrieves a user by id")
	@RequestMapping(method = RequestMethod.GET, path = "/{user_id}")
	public User getUserById(@PathVariable Long user_id) throws Exception  {
		return userService.getUserById(user_id);
	}
	
	
	@Operation(summary = "Get user by id", description = "Retrieves a user by id")
	@RequestMapping(method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) throws Exception  {
		return userService.registerUser(user);
	}
	

}
