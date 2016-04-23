package com.cs.pojo;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by zhaokxkx13 on 2016/3/19.
 */
@Entity
public class Contact {
    private Integer id;
    private String name;
    private String phone_num;
    private User user;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    @ManyToOne
    @JoinColumn(name = "contacts")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
