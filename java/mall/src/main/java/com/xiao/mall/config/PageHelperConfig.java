package com.xiao.mall.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true"); //会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
        properties.setProperty("rowBoundsWithCount","true"); //使用 RowBounds 分页会进行 count 查询。
        properties.setProperty("reasonable","true"); //配置分页的合理化数据 true表示 pageNum<=0 时会查询第一页， pageNum>pages （超过总数时），会查询最后一页。
        properties.setProperty("dialect","mysql"); //配置数据库方言为mysql
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
