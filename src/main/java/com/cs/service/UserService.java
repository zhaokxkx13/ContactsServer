package com.cs.service;

import com.cs.dao.UserDao;
import com.cs.dao.impl.UserDaoImpl;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import net.iharder.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/22.
 */
@Service
@Transactional(readOnly = false)
public class UserService {
    @Resource
    UserDaoImpl ud;
    @Resource
    MailUtil md;

    public User sign_in(String user_id, String user_password) {
        User user = ud.getUser(user_id);
        System.out.print("database password" + user.getPassword());
        System.out.println("remote password" + user_password);
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

    public User get_user(String id) {
        return ud.getUser(id);
    }

    public void update_user(String id, String name, String gender, String mail) {
        User user = ud.loadUser(id);
        if (gender != null)
            user.setGender(gender);
        if (name != null)
            user.setName(name);
        if (mail != null)
            user.setMail(mail);
        ud.updateUser(user);
    }

    public boolean registerValidation(String id) {
        return ud.userValidaton(id);
    }


    public boolean getPasswrod(String id) {
        try {
            User user = ud.getUser(id);
            if (user.getPassword() != null) {
                String password = new String(Base64.decode(user.getPassword().getBytes()), Charset.forName("UTF-8"));
                md.send(user.getMail(), "Your password is " + password);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
