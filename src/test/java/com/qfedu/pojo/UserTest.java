package com.qfedu.pojo;

import com.qfedu.service.UserService;
import com.qfedu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/29 19:25
 */
public class UserTest {

    @Test
    public void save(){
        User user = new User();
        user.setAge(18);
        user.setGender("nan");
        user.setName("zhangsan");

        /*读取hibernate.cfg.xml文件*/
        Configuration cf = new Configuration();
        cf.configure();

        /*创建SessionFactory工厂*/
        SessionFactory sessionFactory = cf.buildSessionFactory();

        /*打开Session对象*/
        Session session = sessionFactory.openSession();

        /*开启事务*/
        Transaction tx = session.beginTransaction();

        /*执行方法*/
        session.save(user);

        /*事务提交*/
        tx.commit();

        /*资源关闭*/
        session.close();
        sessionFactory.close();
    }

    @Test
    public void query(){

        Configuration cf = new Configuration();
        cf.configure();

        SessionFactory sessionFactory = cf.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, 1);

        System.out.println(user);

        tx.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void update(){
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setGender("nv");
        user.setName("diaochan");

        Configuration cf = new Configuration();
        cf.configure();

        SessionFactory sessionFactory = cf.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.update(user);

        tx.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void delete(){
        User user = new User();
        user.setId(2);

        Configuration cf = new Configuration();
        cf.configure();

        SessionFactory sessionFactory = cf.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete(user);

        tx.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void save2(){

        User user = new User();
        user.setName("chutian");
        user.setGender("nv");
        user.setAge(18);

        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        session.save(user);

        transaction.commit();

        session.close();
        HibernateUtil.close();
    }

    private UserService userService = new UserService();

    @Test
    public void save3(){
        userService.save();
    }
}
