package com.xiao.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Authority;
import com.xiao.mall.dom.Customer;
import com.xiao.mall.mapper.AuthorityMapper;
import com.xiao.mall.mapper.CustomerMapper;
import com.xiao.mall.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private AuthorityMapper authorityMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public Customer findCustomerByName(String name) {
        return customerMapper.findByName(name);
    }

    @Override
    public List<Customer> findLikeName(String name) {
        return customerMapper.findLikeName(name);
    }

    @Override
    public List<Authority> findAuthorityByName(String name) {
        return authorityMapper.findByName(name);
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    public Boolean updateCustomerById(Customer customer) {
        return customerMapper.updateById(customer);
    }

    @Override
    public Boolean add(Customer customer) {

        return customerMapper.add(customer);
    }

    @Override
    public Boolean delById(Customer customer) {
        return customerMapper.delById(customer);
    }

    @Override
    public PageInfo<Customer> findByPage(Integer pn) {
        PageHelper.startPage(pn,5);
        List<Customer> customerList=this.findAll();
        PageInfo<Customer> pageInfo=new PageInfo<>(customerList);
        return pageInfo;
    }
}
