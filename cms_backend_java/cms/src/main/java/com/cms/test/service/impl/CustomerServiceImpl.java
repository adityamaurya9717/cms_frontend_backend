package com.cms.test.service.impl;

import com.cms.test.dao.UsersDao;
import com.cms.test.dto.UserModel;
import com.cms.test.dto.UserResponse;
import com.cms.test.dto.request.AddCustomerRequest;
import com.cms.test.dto.request.AddRoleRequest;
import com.cms.test.dto.request.GetCustomerRequest;
import com.cms.test.dto.request.MapRoleRequest;
import com.cms.test.jpa.entity.RoleEntity;
import com.cms.test.jpa.entity.UserEntity;
import com.cms.test.jpa.entity.UserRoleMappingEntity;
import com.cms.test.jpa.repository.RoleRepository;
import com.cms.test.jpa.repository.UserRepository;
import com.cms.test.jpa.repository.UserRoleMappingRepository;
import com.cms.test.security.JwtUtils;
import com.cms.test.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
     private UserRepository userRepository;

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public ResponseEntity<?> addCustomer(AddCustomerRequest request, HttpServletRequest httpServletRequest) {
        String encodePassword = bCryptPasswordEncoder.encode(request.getPassword());
        UserEntity entity = new UserEntity(request,encodePassword);
        userRepository.save(entity);
        // String token =  jwtUtils.generateToken(request.getEmail());
        return ResponseEntity.ok(entity);
    }

    @Override
    public ResponseEntity<?> showUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getUser(GetCustomerRequest request) {
        log.info("getUser requestPayload=",request.toString());
        List<UserModel> customers =usersDao.getCustomers(request);
         Integer count = usersDao.getTotalCount(request);
        UserResponse userResponse = new UserResponse(request.getPageNo(),request.getSize(),count,customers);
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        Optional<UserEntity> optional = userRepository.findById(userId);
        if(!optional.isPresent()){
           return ResponseEntity.badRequest().body("user Not Found for delete");
        }
        UserEntity userEntity = optional.get();
        userEntity.setActive(false);
        userRepository.save(userEntity);
        return ResponseEntity.ok("Deleted SuccessFully");
    }

    @Override
    public ResponseEntity<?> mapRole(MapRoleRequest mapRoleRequest) {

      UserEntity user  = userRepository.findById(mapRoleRequest.getUserId()).orElseThrow(()->new RuntimeException("This user is not Present"));
        List<UserRoleMappingEntity> roleMappingEntities =   userRoleMappingRepository.findByUserId(mapRoleRequest.getUserId());
        Map<Long,UserRoleMappingEntity> roleMappingEntityMap = new HashMap<>();
        for(UserRoleMappingEntity userRoleMappingEntity : roleMappingEntities){
            roleMappingEntityMap.put(userRoleMappingEntity.getRoleId(),userRoleMappingEntity);
        }
        List<UserRoleMappingEntity> insertOrUpdate = new ArrayList<>();
        for(Long roleId : mapRoleRequest.getRoleIds()){
            if(!roleMappingEntityMap.containsKey(roleId)){
                insertOrUpdate.add(new UserRoleMappingEntity(mapRoleRequest.getUserId(),roleId));
            }
            else{
                insertOrUpdate.add(roleMappingEntityMap.get(roleId));
                roleMappingEntityMap.remove(roleId);
            }
        }
        // chnage status to 0;
        for(Map.Entry<Long,UserRoleMappingEntity> entry : roleMappingEntityMap.entrySet()){
            UserRoleMappingEntity userRoleMappingEntity =  entry.getValue();
            userRoleMappingEntity.setActive(0);
            insertOrUpdate.add(userRoleMappingEntity);
        }
        userRoleMappingRepository.saveAll(insertOrUpdate);
        return ResponseEntity.ok("Role Mapped To User");
    }

    @Override
    public ResponseEntity<?> getAllRole() {
       List<RoleEntity> roles  = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<?> addRole(AddRoleRequest addRoleRequest) {
        if(roleRepository.findByRoleName(addRoleRequest.getRoleName().toUpperCase()).isPresent()){
            return ResponseEntity.badRequest().body("roleName already Exists");
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(addRoleRequest.getRoleName().toUpperCase());
        roleRepository.save(roleEntity);
        return ResponseEntity.ok(roleEntity);
    }
}
