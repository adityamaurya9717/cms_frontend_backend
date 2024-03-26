package com.cms.test.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Component
public class InstanceUtils {
    private static InstanceUtils instanceUtils;
    @Autowired
    private  Environment environment;

    @PostConstruct
    public void init(){
        instanceUtils = this;
    }
    public static InstanceUtils getInstanceUtils() {
        return instanceUtils;
    }

    public static <T>  T  getProperty(String key, Class<T> aClass) {
        getProperty("dsd",List.class);
       return getInstanceUtils().environment.getProperty(key,aClass);


    }
    public static <T> T getProperty(String key, Class<T> aClass, Supplier supplier) {
        Objects.requireNonNull(key,"key cannot ne null");
        return getInstanceUtils().environment.getProperty(key,aClass);

    }
}
