package com.example.controller;

import com.example.dao.ReadRepository;
import com.example.domain.Book;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 读取数据控制器
 * @author: yuxiang.fang
 * @create: 2018-06-30 10:34
 **/
/*@RestController注解表示返回的内容都是HTTP Content不会被模版引擎处理的,所以要使用Controller
 */
//@RestController
@Controller
@RequestMapping(value = "/v1")
public class ReadController {
    @Resource
    private ReadRepository readRepository;

    @ApiOperation(value = "display content", notes = "直接返回数据")
    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "Hello World";
    }

    @ApiOperation(value = "index", notes = "页面渲染")
    @RequestMapping("/index")
    public String index(ModelMap map) {
        // 加一个属性，用来在模板中读取
        map.addAttribute("host", "http://www.zhibo8.com");
        // return 模板文件的名称，对应src/main/resource/templates/index
        return "index";
    }

    @ApiOperation(value = "获取用户信息", notes = "根据reader id获取")
    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader, Model model
    ) {
        List<Book> readingList = readRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @ApiOperation(value = "提交reader信息", notes = "")
    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readRepository.save(book);
        return "redirect:/v1/{reader}";
    }
}
