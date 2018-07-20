package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/5 14:48
 */
public class EhcacheTest {

    private final static Logger LOG = LogManager.getLogger(EhcacheTest.class);

    @Test
    public void ehcacheTest1() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        String hql = "from User u";

        Query query =  session.createQuery(hql);

        List<User> users = query.list();

        for (User user : users) {
            LOG.info(users);
        }

        LOG.info("-----------------同一个session下第二次查询---------------");

        Query query1 = session.createQuery(hql);

        List<User> users1 = query1.list();

        for (User user : users1) {
            LOG.info(user);
        }

        transaction.commit();
        session.close();

        LOG.info("------------------新的sessiom执行查询----------------------");

        Session session1 = HibernateUtil.openSession();

        Transaction transaction1 = session1.beginTransaction();

        Query query2 = session1.createQuery(hql);

        List<User> users2 = query2.list();

        for (User user : users2) {
            LOG.info(users2);
        }
    }
}
