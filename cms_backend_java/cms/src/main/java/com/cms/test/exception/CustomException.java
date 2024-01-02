package com.cms.test.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException{
     private HttpStatus httpStatus;
     private String message;
    public CustomException(HttpStatus httpStatus , String message){
       this.httpStatus = httpStatus;
       this.message = message;
    }

}
