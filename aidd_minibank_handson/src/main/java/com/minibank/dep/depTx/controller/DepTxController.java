package com.minibank.dep.depTx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minibank.dep.depTx.dto.DepositAcctInDto;
import com.minibank.dep.depTx.dto.DepositAcctOutDto;
import com.minibank.dep.depTx.dto.WithdrawAcctInDto;
import com.minibank.dep.depTx.dto.WithdrawAcctOutDto;
import com.minibank.dep.depTx.service.DepTxService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tx")
public class DepTxController {
	@Autowired
	private DepTxService depTxService;
	
	@Operation(summary = "Account Deposit", description = "Account Deposit")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8/deposit")
	public DepositAcctOutDto depositAcct(@RequestBody DepositAcctInDto depositAcctInDto) throws Exception {
		return depTxService.depositAcct(depositAcctInDto);
	}
	
	@Operation(summary = "Account Withdrawal", description = "Account Withdrawal")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v0.8/withdraw")
	public WithdrawAcctOutDto withdrawAcct(@RequestBody WithdrawAcctInDto withdrawAcctInDto) throws Exception {
		return depTxService.withdrawAcct(withdrawAcctInDto);
	}

}
