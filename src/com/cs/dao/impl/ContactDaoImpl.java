package com.cs.dao.impl;

import com.cs.dao.ContactDao;
import com.cs.pojo.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhaokxkx13 on 2016/3/20.
 */
@Component
public class ContactDaoImpl extends HibernateDaoSupport implements ContactDao {
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
}
