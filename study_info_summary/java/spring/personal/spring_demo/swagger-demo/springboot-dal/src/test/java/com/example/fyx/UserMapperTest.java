package com.example.fyx;

import com.example.springboot.dal.SpringBootMybatisApplication;
import com.example.springboot.dal.mapper.UserMapper;
import com.example.springboot.dal.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Temporal;

/**
 * @program: swagger-demo
 * @description: mapper测试
 * @author: yuxiang.fang
 * @create: 2018-07-05 21:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User user = userMapper.selectByIdCard("111");
        System.out.println("userInfo:" + user);
        Assert.assertNotNull(user);
    }
}
