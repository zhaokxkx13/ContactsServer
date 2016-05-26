package com.cs.dao;

import com.cs.pojo.User;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/20.
 */
public interface UserDao {
    public void addUser(User user);
    public void delUser(User user);
    public void updateUser(User user);
    public User getUser(String user_id);
    public User loadUser(String user_id);
    public List<User> getAllUser();
    public boolean userValidaton(String id);
}
