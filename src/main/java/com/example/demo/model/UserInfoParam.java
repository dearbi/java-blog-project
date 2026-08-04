package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserInfoParam {
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20,message = "用户名最多20个字符")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Length(max = 20,message = "密码最多20个字符")
    private String password;
}
