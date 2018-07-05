package com.example.springboot.dal.mapper;

import com.example.springboot.dal.model.User;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @program: swagger-demo
 * @description: 用户数据Mapper
 * @author: yuxiang.fang
 * @create: 2018-07-05 20:31
 **/
@Mapper
@Qualifier(value = "userMapper")
public interface UserMapper extends MyMapper{

    @Select("SELECT * FROM user WHERE username=#{username}")
    User selectByName(String username);

    User selectByIdCard(String idCard);
}
