package com.cs.controller;

import com.cs.pojo.Contact;
import com.cs.pojo.User;
import com.cs.service.ContactService;
import com.cs.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.iharder.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
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
    @RequestMapping(value = "register/object", produces = "text/html;charset=utf-8")
    public String regist(@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError item : list)
                System.out.println(item.getDefaultMessage());
        }
        boolean status = userService.sign_up(user);
        if (!result.hasErrors())
            mp = new HashMap<>();
        mp.put("status", status ? "success" : "faild");
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
    public String regist(String id, String password, String name, String gender, String mail) {
        user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(new String(Base64.encodeBytes(password.getBytes())));
        user.setGender(gender);
        user.setMail(mail);
        System.out.println(id + " " + password + " " + gender + " " + name);
        boolean validation = userService.registerValidation(id);
        boolean status = false;
        if (validation) {
            status = userService.sign_up(user);
        }
        mp = new HashMap<>();
        mp.put("status", status && validation ? "success" : "failed");
        if (!validation)
            mp.put("message", "用户已存在");
        System.out.print(gson.toJson(mp));
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login(String id, final String password) {
        mp = new HashMap();
        System.out.println(id + " " + password);
        user = userService.sign_in(id, new String(new String(Base64.encodeBytes(password.getBytes()))));
        if (user != null) {
            System.out.println(id + "has login successed");
            mp.put("status", "success");
            mp.put("name", user.getName());
            mp.put("gender", user.getGender());
        } else {
            mp.put("status", "failed");
        }
        return gson.toJson(mp);
    }

    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String download(String id, String start, String count) {
        System.out.println("download");
        System.out.println("id" + id);
        List<Contact> list = contactService.download(id, Integer.parseInt(start), Integer.parseInt(count));
        return gson.toJson(list);
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String upload(String id, String contacts) {
        System.out.println(id);
        System.out.println(contacts);
        Type type = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        List<Contact> list;
        list = gson.fromJson(contacts, type);
        boolean status = contactService.addContact(list, id);
        mp = new HashMap<>();
        mp.put("status", status ? "success" : "failed");
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

    //此处需要修改
    @ResponseBody
    @RequestMapping(value = "/upload/sync", method = RequestMethod.POST)
    public String uploadSyn(String id, String contacts) {
        Type type = new TypeToken<List<Contact>>() {
        }.getType();
        User user = userService.get_user(id);
        List<Contact> list = gson.fromJson(contacts, type);
        List<Contact> list_local = user.getContacts();
        for (Contact item : list) {
            if (!list_local.contains(item))
                contactService.addContact(item, user);
        }
        mp = new HashMap<>();
        mp.put("status", "success");
        return gson.toJson(mp);
    }

    //修改用户信息
    @ResponseBody
    @RequestMapping(value = "updateuser", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String updateUser(String id, String name, String gender, String mail) {
        userService.update_user(id, name, gender, mail);
        mp = new HashMap<>();
        mp.put("status", "success");
        return gson.toJson(mp);
    }

    //找回密码
    @ResponseBody
    @RequestMapping(value = "getPassword", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String getPassword(String id) {
        mp = new HashMap<>();
        mp.put("status", userService.getPasswrod(id) ? "success" : "failed");
        return gson.toJson(mp);
    }
    //
}