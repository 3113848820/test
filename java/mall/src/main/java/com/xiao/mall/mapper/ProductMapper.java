package com.xiao.mall.mapper;

import com.xiao.mall.dom.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from product ")
    List<Product> findAll();
    List<Product> findLikeName(String sname);
    @Delete("delete from product where id=#{id}")
    Boolean delById(Integer id);
    Boolean add(Product product);
    Boolean updateById(Product product);
}
