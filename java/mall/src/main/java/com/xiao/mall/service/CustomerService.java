package com.xiao.mall.service;

import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Authority;
import com.xiao.mall.dom.Customer;

import java.util.List;

public interface CustomerService {
    Customer findCustomerByName(String name);
    List<Customer> findLikeName(String name);
    List<Authority> findAuthorityByName(String name);
    List<Customer> findAll();
    Boolean updateCustomerById(Customer customer);
    Boolean add(Customer customer);
    Boolean delById(Customer customer);
    PageInfo<Customer> findByPage(Integer pn);
}
