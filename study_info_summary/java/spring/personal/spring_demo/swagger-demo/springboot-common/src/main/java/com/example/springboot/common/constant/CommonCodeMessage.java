package com.example.springboot.common.constant;


/**
 * @program: swagger-demo
 * @description: 错误信息基础类
 * @author: yuxiang.fang
 * @create: 2018-07-08 19:03
 **/
public enum CommonCodeMessage implements CodeMessage {

    // 具体无效的参数名称需要自定义
    INVALID_PARAMETER(10003, "无效的参数");

    private int code;
    private String msg;

    CommonCodeMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
