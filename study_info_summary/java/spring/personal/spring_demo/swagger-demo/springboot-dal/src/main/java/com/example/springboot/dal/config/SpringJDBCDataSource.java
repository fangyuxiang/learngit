package com.example.springboot.dal.config;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * @program: swagger-demo
 * @description: db数据源配置
 * @author: yuxiang.fang
 * @create: 2018-07-05 22:17
 **/
public class SpringJDBCDataSource {
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
