package com.example.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @program: swagger-demo
 * @description: springboot mybatis启动入口
 * @author: yuxiang.fang
 * @create: 2018-07-05 22:12
 **/
@EnableWebMvc
@SpringBootApplication
public class SpringBootMybatisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisTestApplication.class, args);
    }
}
