package com.cms.test.controller;


import com.cms.test.dto.ResponseModel;
import com.cms.test.dto.model.RoleDto;
import com.cms.test.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());

    }
    @PostMapping("/add-role")
    public ResponseModel addRole(@Validated @RequestBody RoleDto roleDto){
        return roleService.addRole(roleDto);
    }
    @PostMapping("/update-role")
    public ResponseModel updateRole(@Validated @RequestBody RoleDto roleDto){
        return roleService.updateRole(roleDto);
    }
}
