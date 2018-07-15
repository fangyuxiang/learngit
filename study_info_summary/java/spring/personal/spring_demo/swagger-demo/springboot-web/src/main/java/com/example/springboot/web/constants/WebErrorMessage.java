package com.example.springboot.web.constants;/**
 * @author yuxiang.fang
 * @date 2018/7/8 22:41
 * 类说明:
 */

import com.example.springboot.common.constant.CodeMessage;
import com.example.springboot.common.constant.CommonCodeMessage;

/**
 * @program: swagger-demo
 * @description: 网络请求异常类
 * @author: yuxiang.fang
 * @create: 2018-07-08 22:41
 **/
public enum WebErrorMessage implements CodeMessage {

    PARAMS_ERROR(20001, "参数错误");

    private int code;
    private String msg;

    WebErrorMessage(int code, String msg) {
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
