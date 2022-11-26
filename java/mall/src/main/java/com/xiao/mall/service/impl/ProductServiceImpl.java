package com.xiao.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Product;
import com.xiao.mall.mapper.ProductMapper;
import com.xiao.mall.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public List<Product> findLikeName(String sname) {
        return productMapper.findLikeName(sname);
    }

    @Override
    public Boolean delById(Integer id) {
        return productMapper.delById(id);
    }

    @Override
    public Boolean add(Product product) {
        return productMapper.add(product);
    }

    @Override
    public Boolean updateById(Product product) {
        return productMapper.updateById(product);
    }

    @Override
    public PageInfo<Product> findByPage(Integer pn) {
        PageHelper.startPage(pn,5);
        List<Product> productList=this.findAll();
        PageInfo<Product> pageInfo = new PageInfo<Product>(productList);
        return pageInfo;
    }
}
