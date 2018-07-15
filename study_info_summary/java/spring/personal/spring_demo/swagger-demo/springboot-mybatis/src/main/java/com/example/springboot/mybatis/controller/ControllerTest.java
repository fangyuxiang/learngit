package com.example.springboot.mybatis.controller;

import com.example.springboot.mybatis.dao.StudentMapper;
import com.example.springboot.mybatis.exception.UserNotExistException;
import com.example.springboot.mybatis.model.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @program: swagger-demo
 * @description: Controller测试
 * @author: yuxiang.fang
 * @create: 2018-07-08 10:55
 **/
@Controller
public class ControllerTest {

    @Autowired
    private StudentMapper studentMapper;

    /*
     *  参考网址： https://blog.csdn.net/qq_33624284/article/details/72828977
     *  目前问题： 页面展示没有解析出具体的数据。
     * */
    @RequestMapping(value = "/studentList")
//    @RequestParam(required = true, defaultValue = "1") Integer page, HttpServletRequest request, Model model
    public String studentListAll(@RequestParam(required = true, defaultValue = "2") Integer page, HttpServletRequest request, Model model) {
        PageHelper.startPage(page, 3);
        List<Student> studentList = studentMapper.selectByList();
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
        /*
         *  获取分页信息：pageInfo
         *  总记录数：getTotal()
         *  总页数：getPages()
         * */
        System.out.println("pageInfo:" + pageInfo);
        System.out.println("page.getTotal:" + pageInfo.getTotal());
        System.out.println("page.getPages:" + pageInfo.getPages());
        System.out.println("page.getNextPage:" + pageInfo.getNextPage());
        model.addAttribute("page", pageInfo);
        model.addAttribute("userList", studentList);
        return "showStudent";
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public String GreetingController(String username) {
        return "Welcome! " + username;
    }

    /*
     * 验证自定义异常通过handler来处理
     * */
    @GetMapping("/user/{id:\\d+}")
    public String get(@PathVariable String id) {
        throw new UserNotExistException(id);
    }
}
