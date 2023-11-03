package com.cms.test.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel<T> {

    private int statusCode;
    private String message;
    private boolean success;
    private T data;

   public ResponseModel(HttpStatus httpStatus,T data){
       this.statusCode = httpStatus.value();
       this.data = data;
    }
    public  ResponseModel success(String message){
       this.message = message;
       this.success = true;
        return this;
    }

    public  ResponseModel failed(String message){
        this.message = message;
        this.success = false;
        return this;
    }

}
