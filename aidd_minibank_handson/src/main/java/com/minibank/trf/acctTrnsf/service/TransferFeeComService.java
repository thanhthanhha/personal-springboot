package com.minibank.trf.acctTrnsf.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.minibank.trf.acctTrnsf.dto.CaculateTransferFeeInDto;

@Service("transferFeeComService")
public class TransferFeeComService {
	//Calculate Transfer Fee
	public BigDecimal caculateTransferFee(CaculateTransferFeeInDto caculateTransferFeeInDto) {
		return BigDecimal.ZERO;
	}
}
