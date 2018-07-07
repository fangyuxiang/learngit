package com.example.springboot.dal.model;

import lombok.Data;

/**
 * @program: swagger-demo
 * @description:
 * @author: yuxiang.fang
 * @create: 2018-07-06 10:12
 **/
@Data
public class NormalUser {

    private String username;

    private String id_card;

    private String phone;

    private String password;

}
