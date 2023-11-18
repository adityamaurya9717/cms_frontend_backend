package com.cms.test.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils {

    public static String randomString(int length){
        String random = RandomStringUtils.randomAlphabetic(length);
        return random.toUpperCase();
    }
}
