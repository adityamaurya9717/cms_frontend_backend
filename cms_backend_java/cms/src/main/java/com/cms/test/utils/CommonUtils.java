package com.cms.test.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static String randomString(int length){
        String random = RandomStringUtils.randomAlphabetic(length);
        return random.toUpperCase();
    }

    public static Date longToDate(long millis){
        return new Date(millis);
    }

    public static String toDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return null;
    }
}
