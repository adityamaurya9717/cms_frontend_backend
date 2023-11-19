package com.cms.test.service.impl;

import com.cms.test.dto.ResponseModel;
import com.cms.test.dto.request.LoginRequest;
import com.cms.test.security.JwtUtils;
import com.cms.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
     private JwtUtils jwtUtils;
    @Override
    public ResponseEntity login(LoginRequest loginRequest) {
        String token =   jwtUtils.generateToken(loginRequest.getEmail());
        ResponseModel responseModel = new ResponseModel(HttpStatus.OK,token).success("SuccessFully Login");
        return ResponseEntity.ok(responseModel);
    }
}
