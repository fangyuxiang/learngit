package com.example.springboot.mybatis.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @program: swagger-demo
 * @description: 学生实体类
 * @author: yuxiang.fang
 * @create: 2018-07-06 22:24
 **/
//@Data: 包含了@Setter、@Getter、@ToString、@EqualsAndHasCode
@Data
@Builder
public class Student {

    private  Long id;

    private  String name;

    private Integer age;

    private Integer score;
}
