package com.email.email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import com.email.exception.BusinessException;
import com.email.exception.SystemException;
import com.email.utils.StringUtils;
import com.email.utils.LoggerUtils;

import org.springframework.http.HttpStatus;

import com.email.email.repository.EmailRepository;

import com.email.email.dto.Email;
import com.email.email.dto.EmailCollection;
import com.email.email.dto.EmailQuery;

public class EmailService {

}
