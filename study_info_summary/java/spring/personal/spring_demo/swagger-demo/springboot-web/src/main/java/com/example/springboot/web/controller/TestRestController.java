package com.example.springboot.web.controller;

import com.example.springboot.common.exception.DemoMyBatisException;
import com.example.springboot.web.constants.WebErrorMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: swagger-demo
 * @description: RestController测试
 * @author: yuxiang.fang
 * @create: 2018-07-04 15:08
 **/
@RestController
@RequestMapping(value = "/v2", produces = "application/json;charset=utf-8")
public class TestRestController {

    @ApiOperation("RestController返回字符串")
    @GetMapping("/display")
    public String display() {
        return "直接返回字符串实现方法:@RestController";
    }

    @ApiOperation("@RestController页面渲染")
    @GetMapping("/render")
    public ModelAndView renderIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("render");
        modelAndView.addObject("url", "通过@RestController + ModelAndView实现");
        return modelAndView;
    }

    @ApiOperation(value = "验证控制器的异常处理类")
    @GetMapping(value = "/user/{id:\\d+}")
    public String batisExceptionTest(@PathVariable String id) {
        throw new DemoMyBatisException(WebErrorMessage.PARAMS_ERROR.getCode(), WebErrorMessage.PARAMS_ERROR.getMsg());
    }
}
