package com.cms.test.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Optional;

@Component
@Slf4j
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
    public static String uploadFileToServer(MultipartFile multipartFile, String fileName) throws IOException {
        Path path = Paths.get( FileUtil.BASEPATH , fileName);
        Files.copy( multipartFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        return FileUtil.BASEPATH + File.separator + fileName;
    }
}
