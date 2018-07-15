package com.example.springboot.mybatis.controller;

import com.example.springboot.mybatis.dao.StudentMapper;
import com.example.springboot.mybatis.model.Student;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: swagger-demo
 * @description: 控制成测试
 * @author: yuxiang.fang
 * @create: 2018-07-07 16:46
 **/
@RestController
public class RestControllerTest {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Student getStudent() {
        Student student = studentMapper.getInfo("fyx");
        return student;
    }

    @RequestMapping(value = "/addStudent", produces = "application/json; charset=utf-8")
    public Student addStudent() {
        return Student.builder().id(11L).name("皇马").age(11).score(95).build();
    }

    /*
    * usage: http://localhost:8080/getStudentList?pageNum=1&pageSize=3
    * */
    @RequestMapping(value = "/getStudentList")
    public Page<Student> getStudentList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Student> studentList = studentMapper.getStudentList();
        return studentList;
    }

    @GetMapping("/render")
    public ModelAndView renderIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("render");
        modelAndView.addObject("url", "通过@RestController + ModelAndView实现");
        return modelAndView;
    }
}
