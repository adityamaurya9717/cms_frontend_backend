package com.cms.test.controller;

import com.cms.test.dto.request.AddBrandRequest;
import com.cms.test.service.BrandService;
import com.cms.test.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/add-brand")
    public ResponseEntity<?> addBrand(@RequestBody AddBrandRequest request, HttpServletRequest httpServletRequest){
        return brandService.addBrand(request,httpServletRequest);
    }


}
