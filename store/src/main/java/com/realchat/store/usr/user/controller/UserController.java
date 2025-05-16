package com.realchat.store.usr.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.realchat.store.usr.user.dto.User;
import com.realchat.store.usr.user.dto.UserAuth;
import com.realchat.store.usr.user.dto.UserQuery;
import com.realchat.store.usr.user.service.UserService;
import com.realchat.store.utils.StringUtils;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService UserService;
	
	@Operation(summary = "Register User", description = "Register User")
	@RequestMapping(method = RequestMethod.POST, path = "/rest/v1")
	public int registerUser(@RequestBody User User) throws Exception {
		return UserService.registerUser(User);
	}
	
	@Operation(summary = "Update User", description = "Update User")
	@RequestMapping(method = RequestMethod.PUT, path = "/rest/v1/{user_id}")
	public User updateUser(@RequestBody User User, @PathVariable String user_id) throws Exception {
		return UserService.updateUser(User, user_id);
	}
	
	@Operation(summary = "Delete User", description = "Delete User")
	@RequestMapping(method = RequestMethod.DELETE, path = "/rest/v1/{user_id}")
	public int deleteUser(@PathVariable String user_id) throws Exception {
		return UserService.deleteUser(user_id);
	}
	
	@Operation(summary = "List User", description = "List User")
	@RequestMapping(method = RequestMethod.GET, path = "/rest/v1")
	public List<User> listUser(@RequestBody UserQuery UserQuery) throws Exception {
		return UserService.listUser(UserQuery);
	}
	
	@Operation(summary = "Get User", description = "Get User")
	@RequestMapping(method = RequestMethod.GET, path = "/rest/v1/{searchCrit}")
	public User getUser(@PathVariable String searchCrit) throws Exception {
		if (StringUtils.isValidEmail(searchCrit)) {
			return UserService.getUser(null, searchCrit);
		} else {
	        // If searchCrit is not an email, pass it as user_id parameter and null as email
	        return UserService.getUser(searchCrit, null);
	    }
	}
}




