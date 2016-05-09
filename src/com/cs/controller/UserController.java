package com.cs.controller;

import com.cs.pojo.Contact;
import com.cs.pojo.User;
import com.cs.service.ContactService;
import com.cs.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2016/3/24.
 */
@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    ContactService contactService;
    User user = null;
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    Map<String, String> mp = null;

    //id password  name gender status:success,faild
    @ResponseBody
    @RequestMapping("/register")
    public String regist(String id, String password, String name, String gender) {
        user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        System.out.println(id + " " + password + " " + gender + " " + name);
        boolean status = userService.sign_up(user);
        mp = new HashMap<>();
        mp.put("status", status ? "success" : "faild");
        System.out.print(gson.toJson(mp));
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String id, final String password) {
        mp = new HashMap();
        user = userService.sign_in(id, password);
        if (user != null) {
            mp.put("stauts", "true");
            mp.put("name", user.getName());
            mp.put("gender", user.getGender());
        } else {
            mp.put("status", "false");
        }
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String download(String id, String start, String count) {
        List<Contact> list = contactService.download(id, Integer.parseInt(start), Integer.parseInt(count));
        List<Map<String, String>> list_return = new ArrayList<>();
        for (Contact item : list) {
            mp = new HashMap<>();
            mp.put("name", item.getName());
            mp.put("phone_num", item.getPhone_num());
            list_return.add(mp);
        }
        return gson.toJson(list_return);
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(String id, String contacts) {
        System.out.println(id);
        System.out.println(contacts);
        User user = userService.get_user(id);
        Type type = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        List<Contact> list;
        list = gson.fromJson(contacts, type);
        for (Contact contact : list) {
            contactService.addContact(contact, user);
        }
        mp = new HashMap<>();
        mp.put("status", "success");
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "download/args", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String download(String id, String arg) {
        System.out.println(id);
        System.out.println(arg);
        System.out.println(contactService.download(id, arg));
        return (gson.toJson(contactService.download(id, arg)));
    }
}
