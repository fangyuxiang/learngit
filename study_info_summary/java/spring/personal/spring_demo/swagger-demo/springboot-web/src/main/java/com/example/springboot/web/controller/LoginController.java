package com.example.springboot.web.controller;

import com.example.springboot.dal.model.User;
import com.example.springboot.web.model.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @program: swagger-demo
 * @description: 登陆验证
 * @author: Yuxiang.Fang
 * @create: 2018/07/28
 **/

@Api(value ="验证参数是否合法demo")
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @ApiOperation(value = "参数非法时异常通过bindingResult返回")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@Valid User userInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }

        return userInfo;
    }
}
