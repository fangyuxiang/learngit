package com.example.springboot.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: swagger-demo
 * @description: 用户数据模型
 * @author: yuxiang.fang
 * @create: 2018-07-05 20:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String idCard;

    private String phone;
}
