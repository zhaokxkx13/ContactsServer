package com.cs.dao;

import com.cs.pojo.Contact;

/**
 * Created by zhaokxkx13 on 2016/3/20.
 */
public interface ContactDao {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);
    public void delContact(Contact contact);
    public Contact getContact(String contact_id);
}
