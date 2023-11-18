package com.cms.test.service;

import com.cms.test.dto.request.AddCustomerRequest;
import com.cms.test.dto.request.AddRoleRequest;
import com.cms.test.dto.request.GetCustomerRequest;
import com.cms.test.dto.request.MapRoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface CustomerService {

    ResponseEntity<?> addCustomer(AddCustomerRequest request, HttpServletRequest httpServletRequest);

    ResponseEntity<?> showUser();

    ResponseEntity<?> getUser(GetCustomerRequest request);

    ResponseEntity<?> deleteUser(Long userId);

    ResponseEntity<?> mapRole(MapRoleRequest mapRoleRequest);

    ResponseEntity<?> getAllRole();

    ResponseEntity<?> addRole(AddRoleRequest addRoleRequest);
}
