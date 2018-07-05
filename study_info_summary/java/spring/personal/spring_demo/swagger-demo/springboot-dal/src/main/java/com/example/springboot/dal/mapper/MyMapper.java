package com.example.springboot.dal.mapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @program: swagger-demo
 * @description: mapper注解引入
 * @author: yuxiang.fang
 * @create: 2018-07-05 20:36
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
