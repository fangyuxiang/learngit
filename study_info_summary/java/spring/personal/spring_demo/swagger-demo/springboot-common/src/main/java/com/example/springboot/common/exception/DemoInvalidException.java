package com.example.springboot.common.exception;

import com.example.springboot.common.constant.CodeMessage;

/**
 * @program: swagger-demo
 * @description: 非法参数异常
 * @author: Yuxiang.Fang
 * @create: 2018/07/28
 **/
public class DemoInvalidException extends BasicException {

    public DemoInvalidException(CodeMessage codeMessage) {
        super(codeMessage.getCode(), codeMessage.getMsg());
    }

    public DemoInvalidException(int code, String message) {
        super(code, message);
    }
}
