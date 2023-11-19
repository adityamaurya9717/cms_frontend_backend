package com.cms.test.service;


import com.cms.test.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    ResponseEntity login(LoginRequest loginRequest);
}
