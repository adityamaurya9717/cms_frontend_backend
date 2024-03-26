package com.cms.test.utils;

import com.cms.test.security.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceUtils {

    private static ServiceUtils instance;
    @Autowired
    private CustomUserDetailServiceImpl userDetailsService;

    @PostConstruct
    private void init(){
        instance = this;
    }

    public static ServiceUtils getInstance(){
        return instance;
    }

    public static CustomUserDetailServiceImpl getUserDetailsService() {
        return instance.userDetailsService;
    }


}
