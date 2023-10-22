package com.cms.test.service;

import com.cms.test.dto.request.AddBrandRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface BrandService {

    ResponseEntity<?> addBrand(AddBrandRequest request, HttpServletRequest httpServletRequest);

    ResponseEntity<?> updateBrand(AddBrandRequest request, HttpServletRequest httpServletRequest);

}
