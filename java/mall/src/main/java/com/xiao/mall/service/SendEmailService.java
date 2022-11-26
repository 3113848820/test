package com.xiao.mall.service;

public interface SendEmailService {
    void SendTemplateEmail(String to,String subject,String context);
}
