package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JWTUtilsTest {
    //过期毫秒时长30分钟
    public static final long Expiration=30*60*1000;
    //密钥
    private static final String secretString="dVnsmy+SIX6pNptQdeclDSJ26EMSPEIhvZYKBTTug4k=";
    //⽣成安全密钥
    private static final SecretKey KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    @Test
    public void genJwt(){
        //自定义信息
        Map<String,Object> claim =new HashMap<>();
        claim.put("id",1);
        claim.put("userName","admin");

        String jwt= Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(KEY)
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void parseJWT(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTc4NTgxMTIzNCwiZXhwIjoxNzg1ODEzMDM0fQ.vx9Z-Xz2d48N0hZ03U7XItV9MPw-VDbFvJto2LdgHvY";
    //创建解析器,设置签名密钥

        JwtParserBuilder jwtParserBuilder =
                Jwts.parserBuilder().setSigningKey(KEY);
    //解析token
        Claims claims = jwtParserBuilder.build().parseClaimsJws(token).getBody();
        System.out.println(claims);
    }
}
