package com.cms.test.controller;

import com.cms.test.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        return uploadService.uploadFile(multipartFile);
    }
    @PostMapping(value = "/s3",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFileToS3(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        return uploadService.uploadFile(multipartFile);
    }

}
