package com.qfedu.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/30 18:45
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    private static Map map = new ConcurrentHashMap();

    private static Session session = null;
    static {
        Configuration cf = new Configuration();
        cf.configure();
        sessionFactory = cf.buildSessionFactory();
    }

    /*开启session对象*/
    public static Session openSession(){
        session = sessionFactory.openSession();
        return session;
    }

    public static Transaction getTransaction(){
        Transaction transaction = session.beginTransaction();
        return transaction;
    }

    public static Session getCurrentSession1(){
        Session session = (Session) map.get(Thread.currentThread());
        if (session == null){
            session = openSession();
            map.put(Thread.currentThread(),session);
        }
        return session;

    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public static void close(){
        if (session != null) {
            session.close();
        }if(sessionFactory != null){
            sessionFactory.close();
        }
    }

}
