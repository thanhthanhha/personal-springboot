package com.minibank.trf.acctTrnsf.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minibank.cst.cust.repository.CustRepository;
import com.minibank.dep.depTx.service.DepTxComService;
import com.minibank.dep.depTx.service.DepTxService;
import com.minibank.exception.BusinessException;
import com.minibank.trf.acctTrnsf.dto.RetrieveAcctTrnsfListOutDto;
import com.minibank.trf.acctTrnsf.repository.AcctTrnsfRepository;
import com.minibank.trf.userCntr.service.UserCntrComService;


@Service("acctTrnsfService")
public class AcctTrnsfService {
	@Autowired
	private AcctTrnsfRepository acctTrnsfRepository;
	@Autowired
	private UserCntrComService userCntrComService;
	@Autowired
	private TransferFeeComService transferFeeComService;
	@Autowired
	private DepTxComService depTxComService;
	@Autowired
	private DepTxService depTxService;
	@Autowired
	private CustRepository custRepository;
	

	// Retrieve Account Transfers
	public List<RetrieveAcctTrnsfListOutDto> retrieveAcctTrnsfList(String wthdrAcno) throws Exception {
		
		return acctTrnsfRepository.retrieveAcctTrnsfList(wthdrAcno);
	}
}
