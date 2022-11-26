package com.xiao.mall.mapper;

import com.xiao.mall.dom.Authority;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthorityMapper {
    @Select("select a.* from customer c,authority a,customer_authority ca where c.id=ca.customer_id and a.id=ca.authority_id and c.name =#{name}")
    List<Authority> findByName(String name);
}
