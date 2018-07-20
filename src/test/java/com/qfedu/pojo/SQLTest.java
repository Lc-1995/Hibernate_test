package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/5 9:23
 */
public class SQLTest {

    private final static Logger LOG = LogManager.getLogger(SQLTest.class);


    /**
     *
     * @Description: left join 查询
     * @auther: lichao
     * @date: 2018/6/5 9:25
     * @param: []
     * @return: void
     */
    @Test
    public void query1() {
        Session session = HibernateUtil.openSession();

        String sql = "select o.productName,u.name from simpleorder o left join user u on o.user_id = u.id";

        NativeQuery nativeQuery = session.createNativeQuery(sql);
        List<Object[]> objects = nativeQuery.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    @Test
    public void query2() {
        Session session = HibernateUtil.openSession();

        String sql = "select * from simpleorder";

        NativeQuery nativeQuery = session.createNativeQuery(sql);

        List<Object> orders = nativeQuery.list();

        for (Object order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 单表查询指定字段
     * @auther: lichao
     * @date: 2018/6/5 9:31
     * @param: []
     * @return: void
     */
    @Test
    public void query3() {
        Session session = HibernateUtil.openSession();

        String sql = "select o.orderNum,o.productName from simpleorder o ";

        NativeQuery nativeQuery = session.createNativeQuery(sql);

        List<Object[]> objects = nativeQuery.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    @Test
    public void query4() {
        Session session = HibernateUtil.openSession();

        String sql = "select * from user";

        NativeQuery nativeQuery = session.createNativeQuery(sql);

        List<User> users = nativeQuery.list();

        LOG.info(users);
    }
}
