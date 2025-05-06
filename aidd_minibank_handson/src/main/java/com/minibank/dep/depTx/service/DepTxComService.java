package com.minibank.dep.depTx.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service("depTxComService")
public class DepTxComService {
//	@Autowired
//	private AcctRepository acctRepository;
	
	public BigDecimal caculatePayPossAmt(String acno) throws Exception {
		//RetrieveAcctOutDto retrieveAcctOutDto = acctRepository.retrieveAcct(acno);
		
		//return retrieveAcctOutDto.getAccoBal();
		return new BigDecimal("50000000");
	}
}
