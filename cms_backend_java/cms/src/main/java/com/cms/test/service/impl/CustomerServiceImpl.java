package com.cms.test.service.impl;

import com.cms.test.dao.UsersDao;
import com.cms.test.dto.UserModel;
import com.cms.test.dto.UserResponse;
import com.cms.test.dto.request.AddCustomerRequest;
import com.cms.test.dto.request.GetCustomerRequest;
import com.cms.test.jpa.entity.UserEntity;
import com.cms.test.jpa.repository.UserRepository;
import com.cms.test.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
     private UserRepository userRepository;

    @Autowired
    private UsersDao usersDao;
    @Override
    public ResponseEntity<?> addCustomer(AddCustomerRequest request, HttpServletRequest httpServletRequest) {

        UserEntity entity = new UserEntity(request);
        userRepository.save(entity);
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
}
