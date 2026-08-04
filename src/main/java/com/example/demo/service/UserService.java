package com.example.demo.service;

import com.example.demo.model.UserInfoParam;
import com.example.demo.model.UserLoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserLoginResponse login(UserInfoParam userInfoParam);
}
