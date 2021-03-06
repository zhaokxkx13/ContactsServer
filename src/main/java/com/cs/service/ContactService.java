package com.cs.service;

import com.cs.dao.impl.ContactDaoImpl;
import com.cs.dao.impl.UserDaoImpl;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/24.
 */
@Service
@Transactional(readOnly = false)
public class ContactService {
    @Resource
    ContactDaoImpl cd;
    @Resource
    UserDaoImpl ud;
    public boolean addContact(List<Contact> list,String  id) {
        try {
            User user = ud.getUser(id);
            for (Contact contact : list) {
                if (cd.getContactByName(id+"_"+contact.getDisplay_name()).isEmpty()) {
                    contact.setUser(user);
                    contact.setId(id+"_"+contact.getDisplay_name());
                    cd.addContact(contact);
                } else {
                    cd.updateContact(contact);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

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

    public List<Contact> download(String id, int start, int num) {
        try {
            return cd.getContact(id, start, num);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contact> download(String id, String args) {
        try {
            return cd.getContactByString(id, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
