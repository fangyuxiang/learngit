package com.example.springboot.mybatis.dao;

import com.example.springboot.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import java.security.PublicKey;

/**
 * @program: swagger-demo
 * @description: 学生mapper
 * @author: yuxiang.fang
 * @create: 2018-07-06 22:32
 **/

//@Alias("_Student")
@Mapper
public interface StudentMapper {

    Student getInfo(@Param("name") String name);

    int  deleteByPrimaryKey(@Param("id") Long id);

    Long insert(Student student);

}
