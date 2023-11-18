package com.cms.test.config;


import com.cms.test.dto.ResponseModel;
import com.cms.test.exception.DublicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler   {


    // for run time Exception
    @ExceptionHandler({RuntimeException.class})
    public ResponseModel<?> handleRuntimeException(RuntimeException exception) {
        ResponseModel responseModel = new ResponseModel(HttpStatus.BAD_REQUEST,exception.getMessage()).failed(exception.getMessage());
        return responseModel;
    }

    @ExceptionHandler({DublicateException.class})
    public ResponseEntity<Object> handleDublicateException(DublicateException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }


}
