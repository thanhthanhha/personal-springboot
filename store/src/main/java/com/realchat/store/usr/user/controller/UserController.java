package com.realchat.store.usr.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.realchat.store.exception.BusinessException;
import com.realchat.store.exception.SystemException;


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
    private UserService userService;
    
    @Operation(summary = "Register User", description = "Register User")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v1")
    public ResponseEntity<User> registerUser(@RequestBody User User) throws Exception {
        User result = userService.registerUser(User);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @Operation(summary = "Get User By Id", description = "Get User By Id")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable String user_id) throws Exception {
        User result = userService.getUser(user_id, null);
        if (result == null) {
        	throw new BusinessException("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @Operation(summary = "Update User", description = "Update User")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/v1/{user_id}")
    public ResponseEntity<User> updateUser(@RequestBody User User, @PathVariable String user_id) throws Exception {
        User updatedUser = userService.updateUser(User, user_id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete User", description = "Delete User")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v1/{user_id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable String user_id) throws Exception {
        int result = userService.deleteUser(user_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @Operation(summary = "List User", description = "List User")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/search")
    public ResponseEntity<List<User>> listUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String user_id) throws Exception {
        
        // Convert comma-separated strings to lists (if not null)
        List<String> nameList = name != null ? List.of(name.split(",")) : null;
        List<String> emailList = email != null ? List.of(email.split(",")) : null;
        List<String> userIdList = user_id != null ? List.of(user_id.split(",")) : null;
        
        // Build the UserQuery with the lists
        UserQuery userQuery = UserQuery.builder()
                .name(nameList)
                .email(emailList)
                .user_id(userIdList)
                .build();
        
        // Call the service
        List<User> users = userService.listUser(userQuery);
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
    @Operation(summary = "Authenticate User", description = "Authenticate User")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v1/auth")
    public ResponseEntity<?> authUser(@RequestBody UserAuth UserAuth) throws Exception {
        boolean result = userService.authUser(UserAuth);
        Map<String, Boolean> response = new HashMap<>();
        response.put("auth", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}