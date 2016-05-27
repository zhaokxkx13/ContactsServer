package com.cs.service;

import org.junit.Test;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by zhaokxkx13 on 2016/5/28.
 */
@Repository
public class MailUtil {
    private JavaMailSenderImpl mailSender;

    public MailUtil(){
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com" );
        mailSender.setPort(587);
        mailSender.setUsername("535745714@qq.com");
        mailSender.setPassword("njzvhzwytaxsbghb");
        //njzvhzwytaxsbghb
    }
    public void send(String address,String content){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(mailSender.getUsername());
        smm.setTo(address);
        smm.setSubject("Your password in Liaomei");
        smm.setText(content);
        mailSender.send(smm);
    }
}
