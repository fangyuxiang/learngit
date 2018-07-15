package com.example.springboot.common.exception;

/**
 * @program: swagger-demo
 * @description: 通用基础exception
 * @author: yuxiang.fang
 * @create: 2018-07-08 18:07
 **/
public abstract class BasicException extends RuntimeException {

    private int code;

    BasicException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
