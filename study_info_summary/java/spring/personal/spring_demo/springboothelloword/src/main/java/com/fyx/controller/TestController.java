package com.fyx.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuxiang.fang
 * @date 2018/6/29 18:23
 * 类说明:
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
