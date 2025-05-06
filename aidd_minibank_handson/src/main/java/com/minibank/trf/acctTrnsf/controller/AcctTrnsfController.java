package com.minibank.trf.acctTrnsf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minibank.trf.acctTrnsf.dto.RetrieveAcctTrnsfListOutDto;
import com.minibank.trf.acctTrnsf.service.AcctTrnsfService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/trnsf")
public class AcctTrnsfController {
	@Autowired
	private AcctTrnsfService acctTrnsfService;
	
	@Operation(summary = "Retrieve Account Transfers", description = "Retrieve Account Transfers")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v0.8/list/{wthdrAcno}")
	public List<RetrieveAcctTrnsfListOutDto> retrieveAcctTrnsfList(@PathVariable String wthdrAcno) throws Exception {
		return acctTrnsfService.retrieveAcctTrnsfList(wthdrAcno);
	}
}
