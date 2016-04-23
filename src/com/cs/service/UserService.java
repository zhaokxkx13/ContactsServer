package com.cs.service;

import com.cs.dao.UserDao;
import com.cs.dao.impl.UserDaoImpl;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/22.
 */
@Service
@Transactional(readOnly = false)
public class UserService {
    @Resource
    UserDaoImpl ud;

    public User sign_in(String user_id, String user_password) {
        User user = ud.getUser(user_id);
        if (user != null) {
            if (user.getPassword().equals(user_password))
                return user;
            else
                return null;
        }
        return null;
    }

    public boolean sign_up(User user) {
        try {
            ud.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> get_users() {
        return ud.getAllUser();
    }

    public List<Contact> getContact(String id) {
        User user = ud.loadUser(id);
        return user.getContacts();
    }
}
