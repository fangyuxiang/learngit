package com.example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: demo
 * @description: RestController Demo
 * @author: yuxiang.fang
 * @create: 2018-07-02 14:23
 * website: https://blog.csdn.net/qq_33082731/article/details/75010221
 **/
@RestController
@RequestMapping(value = "/v2")
public class RestControllerTest {

    @ApiOperation(value = "返回String", notes = "")
    @GetMapping("/display")
    public String display() {
        return "RestController Display Test";
    }

    @ApiOperation(value = "页面渲染", notes = "ModelAndView实现")
    @GetMapping("/index")
    public ModelAndView renderIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("host", "ModelAndView实现页面渲染");
        return modelAndView;
    }
}
