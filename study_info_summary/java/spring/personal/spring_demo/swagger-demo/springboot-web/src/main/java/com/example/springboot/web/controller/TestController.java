package com.example.springboot.web.controller;

import com.example.springboot.common.exception.DemoMyBatisException;
import com.example.springboot.web.constants.WebErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @program: swagger-demo
 * @description: 页面控制层验证
 * @author: yuxiang.fang
 * @create: 2018-07-04 15:03
 **/
@Api(tags = "Controller用法Demo")
@Controller
public class TestController {

    @ApiOperation(value = "直接返回字符串")
    @GetMapping(value = "/show", produces = "application/json;charset=utf-8")
    //解决中文乱码（https://blog.csdn.net/zknxx/article/details/52423608）
    @ResponseBody
    public String show() {
        return "直接返回数据:Hello World";
    }

    @ApiOperation(value = "页面渲染")
    @RequestMapping(value = "/render", method = RequestMethod.GET)
    public String index(ModelMap map) {
        // 模块中添加一个属性
        map.addAttribute("url", "通过@Control注入实现");
        return "render";
    }

}
