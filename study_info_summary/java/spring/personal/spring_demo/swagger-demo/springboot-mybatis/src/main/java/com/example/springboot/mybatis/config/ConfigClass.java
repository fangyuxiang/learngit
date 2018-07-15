package com.example.springboot.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @program: swagger-demo
 * @description: 自动注入bean,这样interface Mapper无需@Mapper注解了。
 * @author: yuxiang.fang
 * @create: 2018-07-07 15:37
 **/
@Configuration
@MapperScan("com.example.springboot.mybatis.dao")
//@ImportResource(locations = {"classpath:Configuration.xml"})
public class ConfigClass {
}
