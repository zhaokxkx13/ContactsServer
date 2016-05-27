package com.cs.pojo;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2016/3/19.
 */
@Entity
public class User {
    @Expose
    @NotEmpty(message = "id can't be null")
    private String id;      //电话号码
    @Expose
    @NotEmpty(message= "name can't be null")
    private String name;
    @Expose
    @NotEmpty(message = "password.can't.be.null")
    private String password;
    @Expose
    @NotEmpty(message = "{genderNull}")
    private String gender;
    @Expose
    private List<Contact> contacts;
    @Expose
    private String mail;

    @Column
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Id
    @GeneratedValue(generator = "generator1")
    @GenericGenerator(name = "generator1", strategy = "assigned")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "user")
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
