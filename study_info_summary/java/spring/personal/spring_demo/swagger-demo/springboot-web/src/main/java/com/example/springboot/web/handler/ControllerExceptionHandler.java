package com.example.springboot.web.handler;

import com.example.springboot.common.exception.DemoMyBatisException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: swagger-demo
 * @description: 控制器的异常处理类:(需要确保Controller与Handler处于同一个module)
 * @author: yuxiang.fang
 * @create: 2018-07-09 15:04
 **/
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DemoMyBatisException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handlerMyBatisException(DemoMyBatisException ex) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", ex.getCode());
        result.put("message", ex.getMessage());
        return result;
    }


}
