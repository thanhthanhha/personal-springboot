package com.email.email.repository;

import com.email.email.dto.Email;
import com.email.email.dto.EmailCollection;
import com.email.email.dto.EmailQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface EmailRepository {
	List<Email> getAllActiveEmail(@Param("userId") String userId) throws Exception;
	int getCountAllActiveEmail(@Param("userId") String userId) throws Exception;
	List<Email> getEmailByQuery(EmailQuery emailQuery) throws Exception;
	boolean existsEmailById(@Param("Id") Long Id) throws Exception;
	boolean existEmailWithUserId(@Param("Id") Long Id, @Param("userId") String userId) throws Exception;
	int deleteEmail(Email email) throws Exception;
	int registerEmail(Email email) throws Exception;
}
