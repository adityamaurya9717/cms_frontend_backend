package com.cms.test.dao;

import com.cms.test.dto.UserModel;
import com.cms.test.dto.request.GetCustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UsersDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public  List<UserModel> getCustomers(GetCustomerRequest request){
     String sqlQuery = "select * from users where 1=1 and active = true ";
     StringBuilder builder = new StringBuilder(sqlQuery);
     List<Object> params = new ArrayList<>();
     filterCustomers(params,request,builder,true);
     sqlQuery = builder.toString();
     Object[] obj = params.toArray(new Object[params.size()]);
     log.info("query={} and params={}",sqlQuery,params);
     List<UserModel> userModel = this.jdbcTemplate.query(sqlQuery,obj,new RowMapper<UserModel>() {
         @Override
         public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
             UserModel model = new UserModel();
             model.setFromResultSet(rs);
             return model;
         }
     });
     return userModel;

    }

    public Integer getTotalCount(GetCustomerRequest request) {
        String sqlQuery = "select count(*) from users where 1=1 and active=true ";
        StringBuilder builder = new StringBuilder(sqlQuery);
        List<Object> params = new ArrayList<>();
        filterCustomers(params,request,builder,false);
        sqlQuery = builder.toString();
        Object[] obj = params.toArray(new Object[params.size()]);
        log.info("query={} and params={}",sqlQuery,params);
        return  this.jdbcTemplate.queryForObject(sqlQuery,obj,Integer.class);

    }

        private void filterCustomers(List<Object> params,GetCustomerRequest request,StringBuilder builder,boolean pagination){
        if( !ObjectUtils.isEmpty(request.getName())){
          builder.append(" and first_name like ? ");
          params.add(request.getName()+"%");
        }
        if( !ObjectUtils.isEmpty(request.getEmail())){
            builder.append(" and email = ? ");
            params.add(request.getEmail());

        }
        if(pagination) {
            int offset = (request.getPageNo() - 1) * request.getSize();
            builder.append(" limit " + request.getSize() + " offset " + offset);
        }

    }

}
