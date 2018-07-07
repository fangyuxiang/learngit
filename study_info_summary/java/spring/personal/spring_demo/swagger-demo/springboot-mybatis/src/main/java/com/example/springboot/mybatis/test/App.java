package com.example.springboot.mybatis.test;

import com.example.springboot.mybatis.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: swagger-demo
 * @description: 测试
 * @author: yuxiang.fang
 * @create: 2018-07-07 11:43
 **/
public class App {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("Configuration.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Student student = (Student) session.selectOne("com.example.springboot.mybatis.model.StudentMapper.getInfo", 1);
            System.out.println(student.getAge());
            System.out.println(student.getName());
        } finally {
            session.close();
        }
    }
    
}
