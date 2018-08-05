package com.example.springboot.dal.model;

import lombok.Data;

import javax.sql.rowset.serial.SerialStruct;
import javax.validation.constraints.NotBlank;

/**
 * @program: swagger-demo
 * @description: login pojo
 * @author: Yuxiang.Fang
 * @create: 2018/07/28
 **/
@Data
public class Login {

    @NotBlank(message = "账号不能为空")
    private String phone;

    @NotBlank(message = "密码不能为空")
    private String password;
}
