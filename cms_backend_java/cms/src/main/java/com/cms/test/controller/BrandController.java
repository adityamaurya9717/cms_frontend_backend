package com.cms.test.controller;

import com.cms.test.dto.request.AddBrandRequest;
import com.cms.test.dto.request.GetBrandRequest;
import com.cms.test.service.BrandService;
import com.cms.test.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin

public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/add-brand")
    public ResponseEntity<?> addBrand(@RequestBody AddBrandRequest request, HttpServletRequest httpServletRequest){
        return brandService.addBrand(request,httpServletRequest);
    }

    @PutMapping("/update-brand")
    public ResponseEntity<?> updateBrand(@RequestBody AddBrandRequest request, HttpServletRequest httpServletRequest){
        return brandService.updateBrand(request,httpServletRequest);
    }
    // get Brand
    @PostMapping("/get-brand")
    public ResponseEntity<?> addBrand(@RequestBody GetBrandRequest request, HttpServletRequest httpServletRequest) throws InterruptedException {
        return brandService.getBrands(request,httpServletRequest);
    }
    @GetMapping("/get-allbrand")
    public ResponseEntity<?> getAllBrands( HttpServletRequest httpServletRequest){
        return brandService.getAllBrands(httpServletRequest);
    }




}
