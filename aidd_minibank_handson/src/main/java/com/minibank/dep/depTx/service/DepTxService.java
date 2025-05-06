package com.minibank.dep.depTx.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.dep.depTx.dto.DepositAcctInDto;
import com.minibank.dep.depTx.dto.DepositAcctOutDto;
import com.minibank.dep.depTx.dto.RegisterDepTxInDto;
import com.minibank.dep.depTx.dto.WithdrawAcctInDto;
import com.minibank.dep.depTx.dto.WithdrawAcctOutDto;
import com.minibank.dep.depTx.repository.DepTxLRepository;
import com.minibank.exception.BusinessException;

@Service("depTxService")
public class DepTxService {
	@Autowired
	private DepTxLRepository depTxLRepository;
//	
//	@Autowired
//	private AcctRepository acctRepository;
	
	@Autowired
	private DepTxComService depTxComService;
	
	// 	Account Deposit
	@Transactional(rollbackFor = Exception.class)
	public DepositAcctOutDto depositAcct(DepositAcctInDto depositAcctInDto) throws Exception {
		String acno = depositAcctInDto.getAcno();
		BigDecimal txAmt = depositAcctInDto.getTxAmt();
		
		// 	Input Validation
		if(acno == null || "".equals(acno))
			throw new BusinessException("Please enter the account number.");
		
		// Check if withdrawal amount is greater than 0
		if(txAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The deposit amount must be greater than 0.");
		
		// Retrieve account balance before deposit
		//RetrieveAcctOutDto retrieveAcctOutDto = acctRepository.retrieveAcct(acno);
		//BigDecimal accoBal = retrieveAcctOutDto.getAccoBal();
		BigDecimal accoBal = new BigDecimal("50000000");
				
		// Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Formatter in YYYYMMDD format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(dateFormatter);

        // Formatter in HHMISS format
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String currentTime = now.format(timeFormatter);
		
		// Insert withdrawal transaction into received transaction history table
		depTxLRepository.registerDepTx(RegisterDepTxInDto.builder()
				.acno(acno)
				.txDt(currentDate)
				.txTime(currentTime)
				.txCd("000001")
				.txAmt(txAmt)
				.build());
		
		// Update account balance in account master table
//		acctRepository.updateAccoBal(UpdateAccoBalInDto.builder()
//				.acno(acno)
//				.accoBal(accoBal.add(txAmt))
//				.build());
		
		return DepositAcctOutDto.builder()
				.acno(acno)
				.txAmt(txAmt)
				.preAccoBal(accoBal)
				.accoBal(accoBal.add(txAmt))
				.build();
	}
	
	// Account Withdrawal
	@Transactional(rollbackFor = Exception.class)
	public WithdrawAcctOutDto withdrawAcct(WithdrawAcctInDto withdrawDepAcctInDto) throws Exception {
		String acno = withdrawDepAcctInDto.getAcno();
		BigDecimal txAmt = withdrawDepAcctInDto.getTxAmt();
		
		// Input Validation
		if(acno == null || "".equals(acno))
			throw new BusinessException("Please enter the account number.");
		
		// Check if withdrawal amount is greater than 0
		if(txAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The withdrawal amount must be greater than 0.");
		
		// Check if the available balance is greater than or equal to the withdrawal amount
		BigDecimal accoBal = depTxComService.caculatePayPossAmt(acno);
		
		if(accoBal.compareTo(txAmt) < 0)
			throw new BusinessException(" Not enough available balance to complete the withdrawal.");
		
		// Retrieve account balance before withdrawal
		//RetrieveAcctOutDto retrieveAcctOutDto = acctRepository.retrieveAcct(acno);
		//accoBal = retrieveAcctOutDto.getAccoBal();
		accoBal = new BigDecimal("50000000");
		
		// Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Formatter in YYYYMMDD format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(dateFormatter);

        // Formatter in HHMISS format
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String currentTime = now.format(timeFormatter);
		
		// Insert withdrawal transaction into received transaction history table
		depTxLRepository.registerDepTx(RegisterDepTxInDto.builder()
				.acno(acno)
				.txDt(currentDate)
				.txTime(currentTime)
				.txCd("000002")
				.txAmt(txAmt)
				.build());
		
		//Update account balance in account master table
//		acctRepository.updateAccoBal(UpdateAccoBalInDto.builder()
//				.acno(acno)
//				.accoBal(accoBal.subtract(txAmt))
//				.build());
		
		
		return WithdrawAcctOutDto.builder()
				.acno(acno)
				.txAmt(txAmt)
				.preAccoBal(accoBal)
				.accoBal(accoBal.subtract(txAmt))
				.build();
	}
}
