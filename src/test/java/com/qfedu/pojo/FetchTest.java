package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/5 12:59
 */
public class FetchTest {

    private final static Logger LOG = LogManager.getLogger(FetchTest.class);

    /*-------------------------一对多的fetch测试------------------------------------*/

    /**
     *
     * @Description: 一对多，一方设置fetch
     * 1.默认情况fetch="select",两条sql，默认懒加载
     * 2.fetch="join",执行left join查询，一条sql，懒加载失效
     * 3.fetch="subselect",两条sql，默认懒加载
     * @auther: lichao
     * @date: 2018/6/5 13:02
     * @param: []
     * @return: void
     */
    @Test
    public void fetchTest1() {
        Session session = HibernateUtil.openSession();

        User user = session.get(User.class, 41);

        LOG.info("执行了。。。。。");

        LOG.info(user.getOrders());

    }

    /**
     *
     * @Description: 一对多，多的一方设置fetch
     * 1.fetch="select",两条sql,默认懒加载
     * 2.fetch="join",执行left join查询，一条sql，懒加载失效
     * 3.fetch="subselect"多对一无法设置,说明subselect只能对多方有用，
     * 所以subselect只对集合有作用
     * @auther: lichao
     * @date: 2018/6/5 13:16
     * @param: []
     * @return: void
     */
    @Test
    public void fetchTest2() {
        Session session = HibernateUtil.openSession();

        SimpleOrder order = session.get(SimpleOrder.class, 19);

        LOG.info("执行了。。。。。");
        LOG.info(order.getUser());
    }

    /**
     *
     * @Description: 返回对象集合设置fetch,一方
     * 1.fetch="select",返回n+1条sql，默认懒加载
     * 2.fetch="join",返回n+1条sql，默认懒加载
     * 3.fetch="subselect",两条sql，默认懒加载
     * @auther: lichao
     * @date: 2018/6/5 13:26
     * @param: []
     * @return: void
     */
    @Test
    public void fetchTest3() {
        Session session = HibernateUtil.openSession();

        String hql = "from User u";

        Query query = session.createQuery(hql);

        List<User> users = query.list();

        LOG.info("执行了。。。。");
        for (User user : users) {
            LOG.info(user);
            LOG.info(user.getOrders());
        }

    }
}
