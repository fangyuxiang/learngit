package com.example.springboot.common.constant;/**
 * @author yuxiang.fang
 * @date 2018/7/8 19:06
 * 类说明:
 */

/**
 * @program: swagger-demo
 * @description: 定义错误信息和错误码的接口
 * @author: yuxiang.fang
 * @create: 2018-07-08 19:06
 **/
public interface CodeMessage {

    int getCode();

    String getMsg();
}
