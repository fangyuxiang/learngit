package com.example.springboot.web.model;

import lombok.Builder;
import lombok.Data;

/**
 * @program: swagger-demo
 * @description: 返回结果DTO
 * @author: Yuxiang.Fang
 * @create: 2018/07/28
 **/
@Data
@Builder
public class ResponseDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static <D> ResponseDTO<D> success(D data) {
        return (ResponseDTO<D>) ResponseDTO.builder().code(0).message("").data(data).build();
    }

    public static <D> ResponseDTO<D> fail(int code, String message) {
        return (ResponseDTO<D>) ResponseDTO.builder().code(code).message(message).data(null).build();
    }
}
