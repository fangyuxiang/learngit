package com.example.dao;

import com.example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: demo
 * @description: 仓库接口
 * @author: yuxiang.fang
 * @create: 2018-06-30 10:35
 **/
//@Component
public interface ReadRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
