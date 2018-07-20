package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/1 17:08
 */
public class QueryTest {

    private static final Logger LOG = LogManager.getLogger(QueryTest.class);

    /**
     *
     * @Description: 全表查询
     * @auther: lichao
     * @date: 2018/6/1 17:13
     * @param: []
     * @return: void
     */
    @Test
    public void queryAll(){
        Session session = HibernateUtil.openSession();

        Transaction tx = HibernateUtil.getTransaction();

        String hql = "from SimpleOrder";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        List<SimpleOrder> orders = query.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 别名查询
     * @auther: lichao
     * @date: 2018/6/1 17:20
     * @param: []
     * @return: void
     */
    @Test
    public void query2(){
        Session session = HibernateUtil.openSession();

        Transaction tx = HibernateUtil.getTransaction();

        String hql = "select o from SimpleOrder o";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        List<SimpleOrder> orders = query.list();

        int i = 0;
        for (SimpleOrder order : orders) {
            LOG.info(order);
            i += 1;
        }

        LOG.info("查询到" + i + "条数据");
        HibernateUtil.close();
    }

    /**
     *
     * @Description: 条件查询
     * @auther: lichao
     * @date: 2018/6/1 17:44
     * @param: []
     * @return: void
     */
    @Test
    public void query3(){

        Session session = HibernateUtil.openSession();

        String hql = "from SimpleOrder o where o.productName = ? ";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        query.setParameter(0,"Java");

        SimpleOrder order = query.uniqueResult();

        LOG.info(order);
    }

    /**
     *
     * @Description: 具名查询
     * @auther: lichao
     * @date: 2018/6/1 18:53
     * @param: []
     * @return: void
     */
    @Test
    public void query4(){
        Session session = HibernateUtil.openSession();

        String hql = "from SimpleOrder o where o.productName = :productName";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        query.setParameter("productName","Java");

        SimpleOrder order = query.uniqueResult();

        LOG.info(order);

        HibernateUtil.close();

    }

    /**
     *
     * @Description: 根据id查询名字
     * @auther: lichao
     * @date: 2018/6/1 18:59
     * @param: []
     * @return: void
     */
    @Test
    public void query5(){

        Session session = HibernateUtil.openSession();

        String hql = "select o.productName from SimpleOrder o where o.id = :id";

        Query query = session.createQuery(hql);

        query.setParameter("id",19);

        String productNamr = (String) query.getSingleResult();

        LOG.info(productNamr);

        HibernateUtil.close();
    }

    /**
     *
     * @Description: 排序分页查询
     * @auther: lichao
     * @date: 2018/6/1 19:49
     * @param: []
     * @return: void
     */
    @Test
    public void query6(){
        Session session = HibernateUtil.openSession();

        String hql = "from SimpleOrder order by id desc ";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        /*从第几行数据开始查询,从0开始*/
        query.setFirstResult(3);

        /*返回几条数据*/
        query.setMaxResults(5);

        List<SimpleOrder> orders = query.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 查询排序
     * @auther: lichao
     * @date: 2018/6/1 19:56
     * @param: []
     * @return: void
     */
    @Test
    public void query7() {

        Session session = HibernateUtil.openSession();

        String hql = "from SimpleOrder order by id asc";

        Query<SimpleOrder> query = session.createQuery(hql, SimpleOrder.class);

        List<SimpleOrder> orders = query.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 查询表中记录总条数
     * @auther: lichao
     * @date: 2018/6/1 20:19
     * @param: []
     * @return: void
     */
    @Test
    public void query8() {
        Session session = HibernateUtil.openSession();

        String hql = "select count(*) from SimpleOrder";

        Query<Long> query = session.createQuery(hql,Long.class);

        Long count = query.uniqueResult();

        LOG.info("表中一共有" + count + "条数据");
    }

    /**
     *
     * @Description: 查询指定字段并排序
     * @auther: lichao
     * @date: 2018/6/1 20:38
     * @param: []
     * @return: void
     */
    @Test
    public void query9() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.orderNum,o.productName from SimpleOrder o order by id desc";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            //LOG.info(obj[0]+"----"+obj[1]);
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 通过list查询指定字段
     * @auther: lichao
     * @date: 2018/6/2 15:50
     * @param: []
     * @return: void
     */
    @Test
    public void query10() {
        Session session = HibernateUtil.openSession();

        String hql = "select new list(o.orderNum,o.productName) from SimpleOrder o ";

        Query query = session.createQuery(hql);

        List<List> lists = query.list();

        for (List l : lists) {
            LOG.info(l);
        }
    }

    /**
     *
     * @Description: 测试不使用别名查询字段
     * @auther: lichao
     * @date: 2018/6/4 11:21
     * @param: []
     * @return: void
     */
    @Test
    public void test1() {
        Session session = HibernateUtil.openSession();

        String hql = "select productName from SimpleOrder";

        Query query = session.createQuery(hql);

        List<String> strings = query.list();

        /*for (int i = 0 ; i < strings.size() ; i++) {
            LOG.info(strings.get(i));
        }*/
        for (String s : strings) {
            LOG.info(s);
        }
    }


    /**
     *
     * @Description: 传统的多表查询
     * @auther: lichao
     * @date: 2018/6/4 10:59
     * @param: []
     * @return: void
     */
    @Test
    public void query11() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.productName,u.name from SimpleOrder o,User u where o.user.id = u.id ";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 传统多表查询
     * @auther: lichao
     * @date: 2018/6/4 14:31
     * @param: []
     * @return: void
     */
    @Test
    public void query11_1() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.productName,o.user.name from SimpleOrder o";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 左连接查询多表
     * @auther: lichao
     * @date: 2018/6/4 10:45
     * @param: []
     * @return: void
     */
    @Test
    public void query12() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.productName,u.name from SimpleOrder o left join o.user u";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 右连接多表查询
     * @auther: lichao
     * @date: 2018/6/4 11:39
     * @param: []
     * @return: void
     */
    @Test
    public void query13() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.productName,u.name from SimpleOrder o right join o.user u";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 内连接多表查询
     * @auther: lichao
     * @date: 2018/6/4 12:50
     * @param: []
     * @return: void
     */
    @Test
    public void query14() {
        Session session = HibernateUtil.openSession();

        String hql = "select o.productName,u.name from SimpleOrder o inner join o.user u";

        Query query = session.createQuery(hql);

        List<Object[]> objects = query.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

}
