package com.example.springboot.dal.mapper;

import com.example.springboot.dal.model.NormalUser;
import com.example.springboot.dal.model.User;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @program: swagger-demo
 * @description: 通过Mapper注解实现sql操作。
 * @author: yuxiang.fang
 * @create: 2018-07-05 20:31
 **/
@Mapper
@Qualifier(value = "userMapper")
public interface UserMapper extends MyMapper {

    @Select("SELECT * FROM user WHERE username=#{username}")
    NormalUser selectByName(String username);


    @Select("SELECT username, id_card as idCard, password, phone FROM user WHERE username=#{username}")
    User selectByAliasName(String username);

}
