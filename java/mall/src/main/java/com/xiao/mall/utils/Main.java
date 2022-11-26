package com.xiao.mall.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//        String pwd=encoder.encode("123");
//        System.out.println(pwd);
        String str="￥1100.0";
        String s=str.substring(1);
        System.out.println(s);
    }
}
