package com.cms.test.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class FileUtil {

    private static String BASEPATH;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        BASEPATH =  Optional.of(environment.getProperty("upload.basepath"))
                     .orElseThrow(()->new RuntimeException("Base Path not Mention in Property File"));
    }

    public static void uploadFileToServer(InputStream inputStream,String fileName) throws IOException {
        Path path = Paths.get( FileUtil.BASEPATH , fileName);
        Files.copy(inputStream,path);
    }
}
