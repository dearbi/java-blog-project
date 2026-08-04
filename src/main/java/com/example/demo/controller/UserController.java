package com.example.demo.controller;

import com.example.demo.model.UserInfoParam;
import com.example.demo.model.UserLoginResponse;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserInfoParam userInfoParam){
        log.info("用户登录，userName: {}", userInfoParam.getUserName());
        return userService.login(userInfoParam);
    }
}
