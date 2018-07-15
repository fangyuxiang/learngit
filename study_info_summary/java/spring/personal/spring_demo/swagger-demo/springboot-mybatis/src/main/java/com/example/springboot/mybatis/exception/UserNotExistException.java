package com.example.springboot.mybatis.exception;

/**
 * @program: swagger-demo
 * @description: 自定义异常，继承于RuntimeException
 * @author: yuxiang.fang
 * @create: 2018-07-09 14:34
 **/
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 3100567970047861248L;

    private String id;

    public UserNotExistException(String id) {
        super("user not exists");
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
