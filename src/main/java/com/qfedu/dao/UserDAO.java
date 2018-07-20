package com.qfedu.dao;

import com.qfedu.pojo.User;
import com.qfedu.utils.HibernateUtil;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/5/30 19:20
 */
public class UserDAO {

    public void save(User user) {
        HibernateUtil.getCurrentSession1().save(user);
    }
}
