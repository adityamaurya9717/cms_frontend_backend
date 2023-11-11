package com.cms.test.service.impl;

import com.cms.test.service.UploadService;
import com.cms.test.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile multipartFile) throws IOException {
        Objects.requireNonNull(multipartFile,"multiPart cannot be null");
        String fileName = multipartFile.getOriginalFilename();
        String path =  FileUtil.uploadFileToServer(multipartFile,fileName);
        return ResponseEntity.ok(path);
    }
}
