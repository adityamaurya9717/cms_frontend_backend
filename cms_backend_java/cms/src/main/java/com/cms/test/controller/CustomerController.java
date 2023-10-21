package com.cms.test.controller;

import com.cms.test.dto.request.AddCustomerRequest;
import com.cms.test.dto.request.GetCustomerRequest;
import com.cms.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("add-user")
   public ResponseEntity<?> addCustomer(@RequestBody AddCustomerRequest request, HttpServletRequest httpServletRequest){
       return customerService.addCustomer(request,httpServletRequest);
    }

    @GetMapping("show-user")
    public ResponseEntity<?> addCustomer(){
        return customerService.showUser();
    }

    @PostMapping ("get-user")
    public ResponseEntity<?> getUsers(@RequestBody GetCustomerRequest request){
        return customerService.getUser(request);
    }
    @DeleteMapping ("delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long userId){
        return customerService.deleteUser(userId);
    }

}
