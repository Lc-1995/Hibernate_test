package com.qfedu.pojo;

import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/4 15:49
 */
public class QBCTest {

    private final static Logger LOG = LogManager.getLogger(QBCTest.class);

    /**
     *
     * @Description: 单表查询指定字段
     * @auther: lichao
     * @date: 2018/6/4 16:19
     * @param: []
     * @return: void
     */
    @Test
    public void query1() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(User.class);

        ProjectionList pList = Projections.projectionList();

        pList.add(Property.forName("name"));
        pList.add(Property.forName("gender"));

        criteria.setProjection(pList);

        List<Object[]> objects = criteria.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 多表查询指定字段
     * @auther: lichao
     * @date: 2018/6/4 16:54
     * @param: []
     * @return: void
     */
    @Test
    public void query1_1() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        /*将SimpleOrder中的对象user设置别名*/
        Criteria alias = criteria.createAlias("user", "u");

        ProjectionList pList = Projections.projectionList();

        pList.add(Property.forName("productName"));
        pList.add(Property.forName("u.name"));
        pList.add(Property.forName("u.gender"));

        criteria.setProjection(pList);

        List<Object[]> objects = criteria.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }
    }

    /**
     *
     * @Description: 单表查询
     * @auther: lichao
     * @date: 2018/6/4 16:58
     * @param: []
     * @return: void
     */
    @Test
    public void query2() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        List<SimpleOrder> orders = criteria.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 使用关联表的属性查询有关联的数据
     *               类似于inner join只会显示有关联的数据
     * @auther: lichao
     * @date: 2018/6/4 19:36
     * @param: []
     * @return: void
     */
    @Test
    public void query2_2() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        Criteria user = criteria.createCriteria("user");

        user.add(Restrictions.like("name","%橙%"));

        List<SimpleOrder> orders = criteria.list();

        int count = 0;
        for (SimpleOrder order : orders) {
            LOG.info(order);
            count += 1;
        }
        LOG.info("一共有:" + count + "条数据");
    }

    /**
     *
     * @Description: 条件查询
     * @auther: lichao
     * @date: 2018/6/4 18:54
     * @param: []
     * @return: void
     */
    @Test
    public void query3() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        /*添加条件*/
        criteria.add(Restrictions.eq("productName","Java"));

        SimpleOrder order = (SimpleOrder) criteria.uniqueResult();

        LOG.info(order);
    }

    /**
     *
     * @Description: 多条件查询
     * @auther: lichao
     * @date: 2018/6/4 19:00
     * @param: []
     * @return: void
     */
    @Test
    public void query4() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        criteria.add(Restrictions.and(Restrictions.eq("productName","Web第三课"),Restrictions.eq("orderNum","008")));

        List<SimpleOrder> orders = criteria.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 模糊条件查询
     * @auther: lichao
     * @date: 2018/6/4 19:08
     * @param: []
     * @return: void
     */
    @Test
    public void query5() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        /*模糊查询*/
        //criteria.add(Restrictions.like("productName","%Web%"));

        /*查询以什么为结尾的数据*/
        criteria.add(Restrictions.like("productName","%测试"));

        /*查询以什么为开头的数据*/
        //criteria.add(Restrictions.like("productName","JavaEE%"));

        List<SimpleOrder> orders = criteria.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 分页排序
     * @auther: lichao
     * @date: 2018/6/4 19:16
     * @param: []
     * @return: void
     */
    @Test
    public void query6() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        /*排序*/
        criteria.addOrder(Order.desc("id"));

        criteria.setFirstResult(0);
        criteria.setMaxResults(2);

        List<SimpleOrder> orders = criteria.list();

        for (SimpleOrder order : orders) {
            LOG.info(order);
        }
    }

    /**
     *
     * @Description: 查询指定字段并分页排序
     * @auther: lichao
     * @date: 2018/6/4 20:41
     * @param: []
     * @return: void
     */
    @Test
    public void query7() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(SimpleOrder.class);

        ProjectionList pList = Projections.projectionList();

        /*查询的指定字段*/
        pList.add(Property.forName("id"));
        pList.add(Property.forName("productName"));

        criteria.setProjection(pList);

        /*排序的依据和排序方式*/
        criteria.addOrder(Order.desc("id"));

        /*从第几条数据开始查询*/
        criteria.setFirstResult(0);

        /*最大返回多少数据*/
        criteria.setMaxResults(3);

        List<Object[]> objects = criteria.list();

        for (Object[] obj : objects) {
            LOG.info(Arrays.toString(obj));
        }

    }
}
