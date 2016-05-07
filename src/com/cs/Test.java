package com.cs;

/**
 * Created by zhaokxkx13 on 2016/3/19.
 */

import com.cs.dao.UserDao;
import com.cs.dao.impl.ContactDaoImpl;
import com.cs.dao.impl.UserDaoImpl;
import com.cs.pojo.Contact;
import com.cs.pojo.User;
import com.cs.service.ContactService;
import com.cs.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:E:\\ContactsServer\\web\\WEB-INF\\applicationContext.xml")
public class Test {

    @Resource
    UserDaoImpl userDaoImpl;
    @Resource
    ContactDaoImpl contactDaoImpl;
    @Resource
    UserService userService;
    @Resource
    ContactService contactService;
    @org.junit.Test
    public void test() {
        User user = userDaoImpl.loadUser("1");
        Contact contact = new Contact();
        contact.setName("hahaa");
        contact.setPhone_num("18646597036");
        contactService.addContact(contact,user);
    }
}
