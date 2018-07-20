package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/5 10:34
 */
public class LazyTest {

    private final static Logger LOG = LogManager.getLogger(LazyTest.class);

    /*-----------------------------类级别的懒加载------------------------------------*/

    /**
     * @Description: get测试
     * @auther: lichao
     * @date: 2018/6/5 9:58
     * @param: []
     * @return: void
     */
    @Test
    public void getTest() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        /*这里就执行了查询*/
        SimpleOrder order = session.get(SimpleOrder.class, 23);

        LOG.info("执行了。。。。。");

        LOG.info(order);

        HibernateUtil.close();

    }

    /**
     * @Description: load类级别的懒加载测试
     * @auther: lichao
     * @date: 2018/6/5 10:01
     * @param: []
     * @return: void
     */
    @Test
    public void loadTest() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        /*没有执行查询*/
        SimpleOrder order = session.load(SimpleOrder.class, 23);

        LOG.info("执行了。。。。。");
        /*这里需要数据才查询回来*/
        LOG.info(order);

        HibernateUtil.close();
    }

    /*----------------------------一级缓存测试---------------------------------*/
    /**
     * @Description: 一级缓存测试
     * @auther: lichao
     * @date: 2018/6/5 10:06
     * @param: []
     * @return: void
     */
    @Test
    public void cacheTest() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        SimpleOrder order = session.get(SimpleOrder.class, 23);

        SimpleOrder load = session.load(SimpleOrder.class, 23);

        LOG.info("get执行了");
        LOG.info(order);
        LOG.info("load执行了");
        LOG.info(load);

        transaction.commit();

        HibernateUtil.close();
    }

    /*-----------------------------多对多关联级别的懒加载------------------------------------*/

    /**
     *
     * @Description: 多对多默认是懒加载
     * @auther: lichao
     * @date: 2018/6/5 10:46
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest1(){
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        People people = session.get(People.class, 3);

        LOG.info("执行了。。。。。。");

        LOG.info("数据条数:" + people.getRoles().size());

        transaction.commit();

        HibernateUtil.close();
    }

    /**
     *
     * @Description: 多对多设置lazy="false"关闭懒加载，改为迫切加载
     * @auther: lichao
     * @date: 2018/6/5 10:49
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest1_1() {
        Session session = HibernateUtil.openSession();

        People people = session.get(People.class, 3);

        LOG.info("执行了。。。。");
        LOG.info("数据条数:" + people.getRoles().size());
    }

    /*-------------------------一对多关联级别的懒加载---------------------------------*/

    /**
     *
     * @Description: 一对多，一的一方默认懒加载
     * @auther: lichao
     * @date: 2018/6/5 10:53
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest3() {
        Session session = HibernateUtil.openSession();

        User user = session.get(User.class, 41);

        LOG.info("执行了。。。。。");
        LOG.info("数据条数:" + user.getOrders().size());
    }

    /**
     *
     * @Description: 设置lazy="false"关闭懒加载，迫切加载
     * @auther: lichao
     * @date: 2018/6/5 10:55
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest3_1() {
        Session session = HibernateUtil.openSession();

        User user = session.get(User.class, 41);

        LOG.info("执行了。。。。。");
        LOG.info("数据条数:" + user.getOrders().size());
    }

    /**
     *
     * @Description: 一对多，多的一方默认懒加载
     * @auther: lichao
     * @date: 2018/6/5 12:42
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest4() {
        Session session = HibernateUtil.openSession();

        SimpleOrder order = session.get(SimpleOrder.class, 19);

        LOG.info("执行了。。。。");
        LOG.info("返回的数据:" + order.getUser());
    }

    /**
     *
     * @Description: 设置lazy="false"关闭懒加载，改为迫切加载
     * @auther: lichao
     * @date: 2018/6/5 12:43
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest4_1() {
        Session session = HibernateUtil.openSession();

        SimpleOrder order = session.get(SimpleOrder.class, 19);

        LOG.info("执行了。。。。");
        LOG.info("返回的数据:" + order.getUser());
    }
}
