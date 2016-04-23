package com.cs.controller;

import com.cs.pojo.Contact;
import com.cs.pojo.User;
import com.cs.service.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    User user = null;
    Gson gson = new Gson();
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
        System.out.println(id+" "+password+" "+gender+" "+name);
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
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String download(String id) {
        List<Contact> list = userService.getContact(id);
        return gson.toJson(list);
    }
}
