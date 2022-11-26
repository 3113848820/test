package com.xiao.mall.controller;

import com.xiao.mall.dom.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    @RequestMapping("/main")
    public String toMain(){
        return "main";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/userLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/getUserName")
    public String getUserName(HttpServletRequest request){
        return null;
    }
}
