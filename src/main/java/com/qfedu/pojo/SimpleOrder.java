package com.qfedu.pojo;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/31 11:06
 */
public class SimpleOrder {

    private Integer id;

    private String orderNum;

    private String productName;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SimpleOrder{" +
                "id=" + id +
                ", orderNum='" + orderNum + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
