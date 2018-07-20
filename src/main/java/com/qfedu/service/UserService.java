package com.qfedu.service;

import com.qfedu.dao.UserDAO;
import com.qfedu.pojo.User;
import com.qfedu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/30 19:25
 */
public class UserService {

    private UserDAO userDAO = new UserDAO();

    public void save(){
        User user = new User();
        user.setAge(12);
        user.setName("dahe");
        user.setGender("nan");

        Transaction transaction = HibernateUtil.getCurrentSession1().beginTransaction();

        userDAO.save(user);

        transaction.commit();

        HibernateUtil.close();

    }
}
