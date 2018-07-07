package com.example.springboot.mybatis;

import com.example.springboot.mybatis.SpringBootMybatisTestApplication;
import com.example.springboot.mybatis.dao.StudentMapper;
import com.example.springboot.mybatis.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: swagger-demo
 * @description: 学生类测试
 * @author: yuxiang.fang
 * @create: 2018-07-06 23:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisTestApplication.class)
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testGetInfo() {
        Student student = studentMapper.getInfo("fyx");
        System.out.println("studentInfo_toString:" + student.toString());
        System.out.println("studentInfo_name:" + student.getName());
        System.out.println("studentInfo_score:" + student.getScore());
    }

}
