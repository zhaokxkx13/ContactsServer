package com.cs.dao.impl;

import com.cs.dao.ContactDao;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import org.hibernate.Query;
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
public class ContactDaoImpl extends HibernateDaoSupport implements ContactDao {
    @Resource
    UserDaoImpl userDaoImpl;
    @Resource
    SessionFactory sessionFactory;

    @Override
    public void addContact(Contact contact) {
        super.getHibernateTemplate().save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        super.getHibernateTemplate().update(contact);
    }

    @Override
    public void delContact(Contact contact) {
        super.getHibernateTemplate().delete(contact);
    }

    @Override
    public Contact getContact(String contact_id) {
        return super.getHibernateTemplate().load(Contact.class, contact_id);
    }

    @Override
    public List<Contact> getContact(String contact_id, int start, int ends) {
        User user = userDaoImpl.getUser(contact_id);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String hql = "select  contact from Contact contact where contact.user=:user";
        Query query = session.createQuery(hql).setFirstResult(start).setMaxResults(ends);
        query.setParameter("user", user);
        return (List<Contact>) query.list();
    }

    @Override
    public List<Contact> getContactByString(String contact_id, String arg) {
        User user = userDaoImpl.getUser(contact_id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "from Contact contact where contact.data1 like :phone_num and contact.user = :user";
        Query query = session.createQuery(sql);
        query.setParameter("phone_num", "%" + arg + "%");
        query.setParameter("user", user);
        return (List<Contact>) query.list();
    }

    @Override
    public List<Contact> getContactByName(String name) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            String sql = "from Contact contact where contact.display_name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name",name);
            List<Contact> list = query.list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }


}

