package com.example.springboot.controller.handler;

import com.example.springboot.common.exception.DemoMyBatisException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


/**
 * @program: swagger-demo
 * @description: 控制器的异常处理类
 * @author: yuxiang.fang
 * @create: 2018-07-08 20:04
 **/

@RestControllerAdvice(basePackages = "com.example.springboot.controller")
//@ControllerAdvice
//@ResponseBody     //将返回的值转成json格式的数据
public class ControllerExceptionHandler {

    //这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler(DemoMyBatisException.class)
    //返回的状态码
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)     //服务内部错误
    public Map<String, Object> handlerMyBatisException(DemoMyBatisException ex) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", ex.getCode());
        result.put("message", ex.getMessage());
        return result;
    }


}
