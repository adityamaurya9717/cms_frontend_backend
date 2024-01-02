package com.cms.test.service.impl;

import com.cms.test.dto.ResponseModel;
import com.cms.test.dto.model.RoleDto;
import com.cms.test.exception.CustomException;
import com.cms.test.jpa.entity.AuthorityEntity;
import com.cms.test.jpa.entity.RoleEntity;
import com.cms.test.jpa.repository.AuthorityRepository;
import com.cms.test.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public ResponseModel getAllRoles(){
       List<RoleEntity> roleEntityList = roleRepository.findAll();
       List<RoleDto> roleDtos  = roleEntityList.stream().map(role->new RoleDto(role)).collect(Collectors.toList());
      return new ResponseModel(HttpStatus.OK,roleDtos).success("success Fully ");
    }

    public ResponseModel addRole(RoleDto roleDto){
    roleRepository.findByRoleNameCaseInsenstive(roleDto.getRoleName())
             .ifPresent( role-> {throw new CustomException(HttpStatus.BAD_REQUEST,"Role Already Present");});
     RoleEntity roleEntity = new RoleEntity(roleDto);
     roleEntity.setAuthorityEntitySet(new HashSet<>(roleDto.getAuthority()));
     roleEntity = roleRepository.save(roleEntity);
     return new ResponseModel(HttpStatus.OK,roleEntity).success("successfully");
    }
    public ResponseModel updateRole(RoleDto roleDto){
        Optional<RoleEntity> optional =  roleRepository.findById(roleDto.getRoleId());

        if(!optional.isPresent()){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Role Not Present");
        }
        RoleEntity roleEntity = optional.get();
        roleEntity.setAuthorityEntitySet(new HashSet<>(roleDto.getAuthority()));
        roleEntity = roleRepository.save(roleEntity);
        return new ResponseModel(HttpStatus.OK,roleEntity).success("successfully");
    }
    // authority

    public ResponseModel getAllAuthority(){
      List<AuthorityEntity> authorityEntities =  authorityRepository.findAll();
      return new ResponseModel(HttpStatus.OK,authorityEntities).success();
    }

    public ResponseModel addAuthority(String authorityName){
       authorityRepository.findByAuthorityNameCaseInsenstive(authorityName)
               .ifPresent( authority-> {throw new CustomException(HttpStatus.BAD_REQUEST,"Authority Already Present");});

       AuthorityEntity authorityEntity = new AuthorityEntity();
       authorityEntity.setAuthorityName(authorityName.toLowerCase().trim());
       authorityRepository.save(authorityEntity);
       return new ResponseModel(HttpStatus.OK,authorityEntity).success();
    }

}
