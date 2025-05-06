package com.minibank.cst.cust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minibank.cst.cust.dto.RegisterCustInDto;
import com.minibank.cst.cust.dto.RetrieveCustListInDto;
import com.minibank.cst.cust.dto.RetrieveCustListOutDto;
import com.minibank.cst.cust.dto.RetrieveCustOutDto;
import com.minibank.cst.cust.dto.UpdateCustInDto;
import com.minibank.cst.cust.service.CustService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cust")
public class CustController {
	@Autowired
	private CustService custService;
	
	@Operation(summary = "Register Customer", description = "Register Customer")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8")
	public int registerCust(@RequestBody RegisterCustInDto registerCustInDto) throws Exception {
		return custService.registerCust(registerCustInDto);
	}
	
	@Operation(summary = "Update Customer", description = "Update Customer")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/v0.8")
    int updateCust(@RequestBody UpdateCustInDto updateCustInDto) throws Exception {
		return custService.updateCust(updateCustInDto);
    }
    
	@Operation(summary = "Delete Customer", description = "Delete Customer")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v0.8/{custNo}")
    int deleteCust(@PathVariable String custNo) throws Exception {
		return custService.deleteCust(custNo);
    }	
    
	@Operation(summary = "Retrieve Customer", description = "Retrieve Customer")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v0.8/{custNo}")
    RetrieveCustOutDto retrieveCust(@PathVariable String custNo) throws Exception {
		return custService.retrieveCust(custNo);
    }
    
	@Operation(summary = "Retrieve Customer List", description = "Retrieve Customer List")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8/list")
    List<RetrieveCustListOutDto> retrieveCustList(@RequestBody RetrieveCustListInDto retrieveCustListInDto) throws Exception {
		return custService.retrieveCustList(retrieveCustListInDto);
    }
	
	}
