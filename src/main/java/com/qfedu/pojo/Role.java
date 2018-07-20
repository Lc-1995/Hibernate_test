package com.qfedu.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/2 9:40
 */
public class Role {

    private Integer id;

    private String name;

    private Set<People> peoples = new HashSet<People>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(Set<People> peoples) {
        this.peoples = peoples;
    }
}
