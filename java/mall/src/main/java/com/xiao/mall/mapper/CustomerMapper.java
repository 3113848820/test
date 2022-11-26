package com.xiao.mall.mapper;

import com.xiao.mall.dom.Customer;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface CustomerMapper {
    Customer findByName(String name);
    @Select("select * from customer")
    List<Customer> findAll();
    Boolean updateById(Customer customer);
    Boolean add(Customer customer);
    Boolean delById(Customer customer);
    List<Customer> findLikeName(String name);
}
