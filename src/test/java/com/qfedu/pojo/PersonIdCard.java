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
 * @Description: 外键关联
 * @Date: 2018/6/1 11:02
 */
public class PersonIdCard {

    private final static Logger LOG = LogManager.getLogger(PersonIdCard.class);

    @Test
    public void save(){
        Person person = new Person();
        person.setName("小草");

        IdCard card = new IdCard();
        card.setCardNum("999999");

        person.setIdCard(card);
        card.setPerson(person);

        Session session = HibernateUtil.openSession();

        Transaction tx = HibernateUtil.getTransaction();

        session.save(person);
        session.save(card);
        tx.commit();

        HibernateUtil.close();

    }

    /**
     *
     * @Description: 普通删除测试
     * @auther: lichao
     * @date: 2018/6/2 9:24
     * @param: []
     * @return: void
     */
    @Test
    public void delete(){
        Session session = HibernateUtil.openSession();

        Transaction tx = HibernateUtil.getTransaction();

        Person person = session.get(Person.class, 2);

        session.delete(person);

        tx.commit();

        HibernateUtil.close();

    }



    /**
     *
     * @Description: 级联保存
     * @auther: lichao
     * @date: 2018/6/1 11:09
     * @param: []
     * @return: void
     */
    @Test
    public void cascadeSave(){
        Person p = new Person();
        p.setName("小草");

        IdCard c = new IdCard();
        c.setCardNum("111");

        p.setIdCard(c);
        c.setPerson(p);

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();

        HibernateUtil.close();

    }

    @Test
    public void test3() {
        Session session = HibernateUtil.openSession();
        String hql = "from Person";
        Query query = session.createQuery(hql);
        List list = query.list();
        LOG.info(list);
    }
}
