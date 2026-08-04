package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.BlogException;
import com.example.demo.common.utils.JWTUtils;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.model.UserInfoParam;
import com.example.demo.model.UserLoginResponse;
import com.example.demo.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserLoginResponse login(UserInfoParam userInfoParam) {
        //验证账号密码是否正确
        UserInfo userInfo = selectUserInfoByName(userInfoParam.getUserName());
        if (userInfo == null) {
            throw new BlogException("用户名不存在");
        }
        if (!userInfo.getPassword().equals(userInfoParam.getPassword())) {
            throw new BlogException("密码错误");
        }

        //返回登录成功
        Map<String,Object> cliams = new HashMap<>();
        cliams.put("id",userInfo.getId());
        cliams.put("name",userInfo.getUserName());

        String jwt= JWTUtils.genJwt(cliams);
        return new UserLoginResponse(userInfo.getId(),jwt);
    }

    private UserInfo selectUserInfoByName(@NotBlank(message = "用户名不能为空") @Length(max = 20,message = "用户名最多20个字符") String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }
}
