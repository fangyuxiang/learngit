package com.example.springboot.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    private String password;

    private String username;

    private String idCard;

    private String phone;
}
