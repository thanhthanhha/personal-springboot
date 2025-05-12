package com.realchat.store.usr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.realchat.store.usr.dto.User;
import com.realchat.store.usr.dto.UserAuth;
import com.realchat.store.usr.dto.UserQuery;
import com.realchat.store.usr.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService UserService;
	
	@Operation(summary = "Register User", description = "Register User")
	@RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8")
	public int registerCust(@RequestBody User User) throws Exception {
		return UserService.registerUser(User);
	}

}
