package com.cs.pojo;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by zhaokxkx13 on 2016/3/19.
 */
@Entity
public class Contact {

    @Expose
    private String id;
    @Expose
    private String display_name;
    @Expose
    private String data1;
    private User user;
    @Expose
    private byte[] photo;

    @Id
    @GeneratedValue(generator = "generator2")
    @GenericGenerator(name = "generator2", strategy = "assigned")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    @Column
    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    @ManyToOne
    @JoinColumn(name = "contacts")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Blob", nullable = true)
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


}
