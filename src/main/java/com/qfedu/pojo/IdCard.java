package com.qfedu.pojo;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/1 10:55
 */
public class IdCard {

    private Integer id;

    private String cardNum;

    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
