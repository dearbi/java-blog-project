package com.example.demo.common.exception;

public class BlogException extends RuntimeException {
    private Integer code;
    private String message;

    public BlogException(String message){
        super(message);
        this.message = message;
    }

    public BlogException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
