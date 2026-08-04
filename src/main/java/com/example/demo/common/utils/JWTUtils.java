package com.example.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {
    //密钥
    public static String secret="dVnsmy+SIX6pNptQdeclDSJ26EMSPEIhvZYKBTTug4k=";
    //过期时间一天
    public static final long Expiration=24*60*60*1000;

    //生成安全密钥
    private static final SecretKey KEY= Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    /**
     * 生成密钥
     */
    public static String genJwt(Map<String,Object> claims){
        //签名算法
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+Expiration))
                .signWith(KEY)
                .compact();
        return jwt;
    }

    /**
     * 验证密钥
     */
    public static Claims parseJWT(String jwt){
        if(!StringUtils.hasLength(jwt)){
            return null;
        }
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();
        jwtParserBuilder.setSigningKey(KEY);
        Claims claims = null;
        try {
            //解析token
            claims = jwtParserBuilder.build().parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            log.error("解析token失败，：{}",e.getMessage());
        }
        return claims;
    }

    /**
     * 从token中获取⽤户ID
     */
    public static Integer getUserIdFromToken(String jwtToken) {
        Claims claims = JWTUtils.parseJWT(jwtToken);
        if (claims != null) {
            Map<String, Object> userInfo = new HashMap<>(claims);
            return (Integer) userInfo.get("id");
        }
        return null;
    }
}
