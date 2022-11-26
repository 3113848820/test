package com.xiao.mall.dom;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Boolean active;
}
