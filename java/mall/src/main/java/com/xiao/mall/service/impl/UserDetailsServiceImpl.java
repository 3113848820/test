package com.xiao.mall.service.impl;

import com.xiao.mall.dom.Authority;
import com.xiao.mall.dom.Customer;
import com.xiao.mall.service.CustomerService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer=customerService.findCustomerByName(s);
        List<Authority> authorities=customerService.findAuthorityByName(s);
        List<SimpleGrantedAuthority> list=authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        if(customer!=null){
            UserDetails userDetails=new User(customer.getName(),customer.getPassword(),list);
            return userDetails;
        }else {
            throw new UsernameNotFoundException("当前用户不存在");
        }
    }
}
