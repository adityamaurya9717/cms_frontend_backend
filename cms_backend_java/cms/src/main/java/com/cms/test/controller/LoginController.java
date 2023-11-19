package com.cms.test.controller;

import com.cms.test.dto.request.LoginRequest;
import com.cms.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.net.ftp.FtpClient;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class LoginController {

  @Autowired
  private LoginService loginService;
  @PostMapping("/login")
  public ResponseEntity login( @RequestBody LoginRequest loginRequest){
    return loginService.login(loginRequest);
  }
}
