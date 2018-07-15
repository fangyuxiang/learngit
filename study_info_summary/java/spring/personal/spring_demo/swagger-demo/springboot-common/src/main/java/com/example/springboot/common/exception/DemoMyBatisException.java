package com.example.springboot.common.exception;

import com.example.springboot.common.constant.CodeMessage;


/**
 * @program: swagger-demo
 * @description: mybatis模板异常类
 * @author: yuxiang.fang
 * @create: 2018-07-08 19:20
 **/
public class DemoMyBatisException extends BasicException {

    public DemoMyBatisException(CodeMessage codeMessage) {
        super(codeMessage.getCode(), codeMessage.getMsg());
    }

    public DemoMyBatisException(int code, String msg) {
        super(code, msg);
    }
}
