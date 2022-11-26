package com.xiao.mall.service;

import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findLikeName(String sname);
    Boolean delById(Integer id);
    Boolean add(Product product);
    Boolean updateById(Product product);
    PageInfo<Product> findByPage(Integer pn);
}
