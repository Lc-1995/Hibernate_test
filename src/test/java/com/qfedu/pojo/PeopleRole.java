package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/2 9:50
 */
public class PeopleRole {

    /**
     *
     * @Description: 普通保存
     * @auther: lichao
     * @date: 2018/6/2 11:21
     * @param: []
     * @return: void
     */
    @Test
    public void save() {

        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        People people = new People();
        people.setName("夏草");
        people.setAge(18);

        Role r1 = new Role();
        r1.setName("白富美");

        Role r2 = new Role();
        r2.setName("高富帅");

        people.getRoles().add(r1);
        people.getRoles().add(r2);

        r1.getPeoples().add(people);
        r2.getPeoples().add(people);

        session.save(people);
        session.save(r1);
        session.save(r2);

        transaction.commit();

        HibernateUtil.close();

    }

    /**
     *
     * @Description: 级联删除 主控方删除
     * @auther: lichao
     * @date: 2018/6/2 11:23
     * @param: []
     * @return: void
     */
    @Test
    public void delete() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        People people = session.get(People.class, 4);

        session.delete(people);

        transaction.commit();

        HibernateUtil.close();
    }

    /**
     *
     * @Description: 被控方删除
     * @auther: lichao
     * @date: 2018/7/6 11:46
     * @param: []
     * @return: void
     */
    @Test
    public void delete2() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        Role role = session.get(Role.class,8);
        Set<People> peoples = role.getPeoples();
        for (People people : peoples) {
            people.getRoles().remove(role);
        }
        session.delete(role);

        transaction.commit();

        HibernateUtil.close();
    }

    /**
     *
     * @Description: 级联保存
     * @auther: lichao
     * @date: 2018/6/2 12:54
     * @param: []
     * @return: void
     */
    @Test
    public void save2() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        People people = new People();
        people.setAge(19);
        people.setName("小草");

        Role r1 = new Role();
        r1.setName("白富美");

        Role r2 = new Role();
        r2.setName("高富帅");

        people.getRoles().add(r1);
        people.getRoles().add(r2);

        session.save(people);
        transaction.commit();
        HibernateUtil.close();

    }

    @Test
    public void test4() {
        Session session = HibernateUtil.openSession();
        String hql = "select count(*) from People p right join p.roles r where r.id = 7";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();
        System.out.println("查询条数:" + count);
    }
}
