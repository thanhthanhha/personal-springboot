//package com.minibank.cmm.user.controller;
package com.sample.MethodGen.Basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.MethodGen.Basic.dto.RegisterUserInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListOutDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserOutDto;
import com.sample.MethodGen.Basic.dto.UpdateUserInDto;
import com.sample.MethodGen.Basic.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Register User", description = "Register User")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8")
	public int registerUser(@RequestBody RegisterUserInDto registerUserInDto) throws Exception {
		return userService.registerUser(registerUserInDto);
	}
	
	@Operation(summary = "Update User", description = "Update User")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/v0.8")
	public int updateUser(@RequestBody UpdateUserInDto updateUserInDto) throws Exception {
		return userService.updateUser(updateUserInDto);
    }
    
	@Operation(summary = "Delete User", description = "Delete User")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v0.8/{userNo}")
	public int deleteUser(@PathVariable String userNo) throws Exception {
		return userService.deleteUser(userNo);
    }
    
	@Operation(summary = "Retrieve User", description = "Retrieve User")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v0.8/{userNo}")
	public RetrieveUserOutDto retrieveUser(@PathVariable String userNo) throws Exception {
		return userService.retrieveUser(userNo);
    }
    
	@Operation(summary = "Retrieve User List", description = "Retrieve User List")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8/list")
	public List<RetrieveUserListOutDto> retrieveUserList(@RequestBody RetrieveUserListInDto retrieveUserListInDto) throws Exception {
		return userService.retrieveUserList(retrieveUserListInDto);
    }
}
