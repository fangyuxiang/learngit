package com.example.springboot.controller.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @program: swagger-demo
 * @description: 返回值数据模型
 * @author: yuxiang.fang
 * @create: 2018-07-08 20:06
 **/
@Data
@Builder
@ToString
public class ResponseDTO<T> {

    private int code;
    private String msg;
    private T data;

    public static <D> ResponseDTO<D> success(D data) {
        return (ResponseDTO<D>) ResponseDTO.builder().code(0).msg("").data(data).build();
    }


}
