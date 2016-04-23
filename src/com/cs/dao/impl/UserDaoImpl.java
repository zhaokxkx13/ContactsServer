package com.cs.dao.impl;

import com.cs.dao.UserDao;
import com.cs.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/20.
 */
@Component
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public void addUser(User user) {
        super.getHibernateTemplate().save(user);
    }

    @Override
    public void delUser(User user) {
        super.getHibernateTemplate().delete(user);
    }

    @Override
    public void updateUser(User user) {
        super.getHibernateTemplate().update(user);
    }

    @Override
    public User getUser(String user_id) {
        return super.getHibernateTemplate().get(User.class,user_id);
    }

    @Override
    public User loadUser(String user_id) {
        return super.getHibernateTemplate().load(User.class,user_id);
    }

    @Override
    public List<User> getAllUser() {
        return super.getHibernateTemplate().loadAll(User.class);
    }
}
