package com.cs.service;

import com.cs.dao.impl.ContactDaoImpl;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaokxkx13 on 2016/3/24.
 */
@Service
@Transactional(readOnly = false)
public class ContactService {
    @Resource
    ContactDaoImpl cd;

    public boolean addContact(Contact contact, User user) {
        try {
            contact.setUser(user);
            cd.addContact(contact);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
