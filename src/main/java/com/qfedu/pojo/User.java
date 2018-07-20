package com.qfedu.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/29 19:10
 */
public class User {

    private Integer id;

    private String name;

    private String gender;

    private int age;

    private Set<SimpleOrder> orders = new HashSet<SimpleOrder>();

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<SimpleOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<SimpleOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
