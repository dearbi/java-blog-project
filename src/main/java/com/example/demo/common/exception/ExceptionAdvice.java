package com.example.demo.common.exception;

import com.example.demo.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public Result handler(Exception e){
        log.error("发生异常，：{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Result handler(BlogException e){
        log.error("发生异常，：{}",e.getMessage());
        return Result.fail(e.getMessage());
    }
}
