package com.minibank.cst.cust.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.minibank.cst.cust.dto.RegisterCustInDto;
import com.minibank.cst.cust.dto.RetrieveCustListInDto;
import com.minibank.cst.cust.dto.RetrieveCustListOutDto;
import com.minibank.cst.cust.dto.RetrieveCustOutDto;
import com.minibank.cst.cust.dto.UpdateCustInDto;

@Mapper
public interface CustRepository {
	public int registerCust(RegisterCustInDto registerCustInDto) throws Exception;
    public int updateCust(UpdateCustInDto updateCustInDto) throws Exception;
    public int deleteCust(String custNo) throws Exception;
    public RetrieveCustOutDto retrieveCust(String custNo) throws Exception;
    public List<RetrieveCustListOutDto> retrieveCustList(RetrieveCustListInDto retrieveCustListInDto) throws Exception;
}
