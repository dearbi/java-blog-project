package com.example.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从header中获取token
        String token = request.getHeader("user_token");
        log.info("从header获取token:{}",token);
        //验证token
        Claims claims = JWTUtils.parseJWT(token);
        if(claims!=null){
            log.info("token验证通过");
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
