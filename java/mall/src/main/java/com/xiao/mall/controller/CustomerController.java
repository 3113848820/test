package com.xiao.mall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Customer;
import com.xiao.mall.service.CustomerService;
import com.xiao.mall.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Customer> customerList=customerService.findAll();
        mv.addObject("customerList",customerList);
        mv.addObject("flag",false);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/updateById")
    public ModelAndView updateById(Customer customer,Integer pn){
        ModelAndView mv=new ModelAndView();
        customerService.updateCustomerById(customer);
        PageInfo<Customer> pageInfo=customerService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("customerList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",false);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/add")
    public ModelAndView add(String name,String password,String email){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        ModelAndView mv=new ModelAndView();
        Customer customer=new Customer();
        customer.setName(name);
        customer.setPassword(encoder.encode(password));
        customer.setEmail(email);
        if(customer!=null){
            customerService.add(customer);
        }
        PageInfo<Customer> pageInfo=customerService.findByPage(1);
        List list=pageInfo.getList();
        mv.addObject("customerList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",false);
        mv.setViewName("index");
       return mv;
    }
    @RequestMapping("/delById")
    public ModelAndView delById(Integer id,Boolean active,Integer pn){
        ModelAndView mv=new ModelAndView();
        Customer customer=new Customer();
        customer.setId(id);
        customer.setActive(active);
        Boolean flag=customerService.delById(customer);
        PageInfo<Customer> pageInfo=customerService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("customerList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",false);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/findLikeName")
    public ModelAndView findLikeName(String name){
        ModelAndView mv=new ModelAndView();
        if(name!=null||name!=""){
            PageHelper.startPage(1,5);
            List<Customer> customerList= customerService.findLikeName(name);
            PageInfo<Customer> pageInfo=new PageInfo<>(customerList);
            List<Customer> list=pageInfo.getList();
            mv.addObject("customerList",list);
            mv.addObject("pageNum",pageInfo.getPageNum());
            mv.addObject("pages",pageInfo.getPages());
        }else {
            PageInfo<Customer> pageInfo=customerService.findByPage(1);
            List<Customer> list= pageInfo.getList();
            mv.addObject("customerList",list);
            mv.addObject("pageNum",pageInfo.getPageNum());
            mv.addObject("pages",pageInfo.getPages());
        }
        mv.addObject("flag",false);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/registered")
    public ModelAndView registered(Customer customer){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        ModelAndView mv=new ModelAndView();
        if(customer!=null){
//           注册用户
            String password=customer.getPassword();
            String pwd= encoder.encode(password);
            customer.setPassword(pwd);
            customerService.add(customer);
//          发送邮件信息
            Context context=new Context();
            context.setVariable("username",customer.getName());
            context.setVariable("password",password);
            String emailContent=templateEngine.process("emailTemplate",context);
            sendEmailService.SendTemplateEmail(customer.getEmail(), "注册信息",emailContent);
//            查询用户信息
            PageInfo<Customer> pageInfo=customerService.findByPage(1);
            List list=pageInfo.getList();
            mv.addObject("customerList",list);
            mv.addObject("pageNum",pageInfo.getPageNum());
            mv.addObject("pages",pageInfo.getPages());
            mv.addObject("flag",false);
            mv.setViewName("index");
        }else {
            mv.setViewName("register");
        }
        return mv;
    }
    @RequestMapping("/findByPage")
    public ModelAndView find(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn){
        ModelAndView mv=new ModelAndView();
        PageInfo<Customer> pageInfo=customerService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("customerList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",false);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/getuserByContext")
    @ResponseBody
    public Map<String,String> getUser(){
        SecurityContext context= SecurityContextHolder.getContext();
        Authentication authentication=context.getAuthentication();
        UserDetails principal= (UserDetails) authentication.getPrincipal();
        String username=principal.getUsername();
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        return map;
    }
}
