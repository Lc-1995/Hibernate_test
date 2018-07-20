package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/31 11:23
 */
public class UserOrderTest {

    private static final Logger LOG = LogManager.getLogger(UserOrderTest.class);


    @Test
    public void save(){
        User user = new User();
        user.setGender("女");
        user.setName("小青");
        user.setAge(18);

        SimpleOrder order = new SimpleOrder();
        order.setOrderNum("009");
        order.setProductName("JavaEE第三天");

        SimpleOrder order1 = new SimpleOrder();
        order1.setOrderNum("010");
        order1.setProductName("Web第四课");

        Set<SimpleOrder> orders = new HashSet<SimpleOrder>();
        orders.add(order);
        orders.add(order1);

        user.setOrders(orders);

        //order.setUser(user);
        //order1.setUser(user);

        //user.getOrders().add(order);
        //user.getOrders().add(order1);

        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        session.save(user);
        //session.save(order);
        //session.save(order1);
        transaction.commit();
        HibernateUtil.close();
    }

    @Test
    public void delete(){
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        User user = session.get(User.class, 35);

        session.delete(user);

        transaction.commit();

        HibernateUtil.close();

    }

    /**
     *
     * @Description: 具名查询
     * @auther: lichao
     * @date: 2018/6/1 16:17
     * @param: []
     * @return: void
     */
    @Test
    public void query(){
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        String hql = "from User where name = :name";
        Query<User> query = session.createQuery(hql, User.class);

        query.setParameter("name","小黄");

        User user = query.getSingleResult();

        LOG.info(user);

        transaction.commit();
        HibernateUtil.close();
    }

    @Test
    public void getTest() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        SimpleOrder order = session.get(SimpleOrder.class, 19);

        LOG.info(order);

        transaction.commit();
        HibernateUtil.close();

    }
}