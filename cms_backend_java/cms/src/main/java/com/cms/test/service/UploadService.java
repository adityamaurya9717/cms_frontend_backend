package com.cms.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UploadService {
    ResponseEntity<?> uploadFile(MultipartFile multipartFile) throws IOException;
}
