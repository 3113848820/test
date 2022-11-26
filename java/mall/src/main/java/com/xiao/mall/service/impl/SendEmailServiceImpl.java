package com.xiao.mall.service.impl;

import com.xiao.mall.service.SendEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public void SendTemplateEmail(String to, String subject, String context) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context,true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
