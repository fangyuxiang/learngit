package com.example.springboot.dal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: swagger-demo
 * @description:
 * @author: yuxiang.fang
 * @create: 2018-07-05 20:45
 **/
public class BaseEntity implements Serializable {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private Integer page=1;

    @Transient
    private Integer rows=10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return rows;
    }

    public  void setPage() {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }
}
