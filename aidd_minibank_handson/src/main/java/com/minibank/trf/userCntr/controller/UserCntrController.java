package com.minibank.trf.userCntr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minibank.trf.userCntr.dto.RegisterUserCntrInDto;
import com.minibank.trf.userCntr.dto.RetrieveUserCntrOutDto;
import com.minibank.trf.userCntr.dto.UpdateUserCntrInDto;
import com.minibank.trf.userCntr.service.UserCntrService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cntr")
public class UserCntrController {
	@Autowired
	private UserCntrService userCntrService;
	
	@Operation(summary = "Retrieve User Contract", description = "Retrieve User Contract")
	@RequestMapping(method = RequestMethod.GET, path = "/rest/v0.8/{userId}")
	public RetrieveUserCntrOutDto retrieveUserCntr(@PathVariable String userId) throws Exception {
		return userCntrService.retrieveUserCntr(userId);
	}
	
	@Operation(summary = "Register User Contract", description = "Register User Contract")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8")
	public int registerUserCntr(@RequestBody RegisterUserCntrInDto registerUserCntrInDto) throws Exception {
		return userCntrService.registerUserCntr(registerUserCntrInDto);
	}
	
	@Operation(summary = "Update User Contract", description = "Update User Contract")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/v0.8")
	public int updateUserCntr(@RequestBody UpdateUserCntrInDto updateUserCntrInDto) throws Exception {
		return userCntrService.updateUserCntr(updateUserCntrInDto);
	}
	
	@Operation(summary = "Delete User Contract", description = "Delete User Contract")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v0.8/{userId}")
	public int deleteUserCntr(@PathVariable String userId) throws Exception {
		return userCntrService.deleteUserCntr(userId);
	}
}
