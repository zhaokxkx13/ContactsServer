package com.cs.dao;

import com.cs.pojo.Contact;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/20.
 */
public interface ContactDao {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);
    public void delContact(Contact contact);
    public Contact getContact(String name);
    public List<Contact> getContact(String contact_id, int start, int end);
    public List<Contact> getContactByString(String contact_id,String arg);
    public List<Contact> getContactByName(String name);
}
