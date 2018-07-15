package com.example.springboot.mybatis.dao;

import com.example.springboot.mybatis.model.Student;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import java.security.PublicKey;
import java.util.List;

/**
 * @program: swagger-demo
 * @description: 学生mapper
 * @author: yuxiang.fang
 * @create: 2018-07-06 22:32
 **/

/*如果要指定别名，则添加@Alias注解*/
//@Alias("_Student")

/*当配置了MapperScan后,这里就可以省略*/
//@Mapper
public interface StudentMapper {

    Student getInfo(@Param("name") String name);

    int deleteByPrimaryKey(@Param("id") Long id);

    Long insert(Student student);

    List<Student> selectByList();

   Page<Student> getStudentList();
}
