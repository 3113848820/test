package com.xiao.mall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.mall.dom.Product;
import com.xiao.mall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Product> productList=productService.findAll();
        mv.addObject("productList",productList);
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/findLikeName")
    public ModelAndView findLikeName(String name){
        ModelAndView mv=new ModelAndView();
        if(name!=null||name!=""){
            PageHelper.startPage(1,5);
            List<Product> productList= productService.findLikeName(name);
            PageInfo<Product> pageInfo=new PageInfo<>(productList);
            List<Product> list=pageInfo.getList();
            mv.addObject("productList",list);
            mv.addObject("pageNum",pageInfo.getPageNum());
            mv.addObject("pages",pageInfo.getPages());
        }else {
            PageInfo<Product> pageInfo=productService.findByPage(1);
            List<Product> list=pageInfo.getList();
            mv.addObject("productList",list);
            mv.addObject("pageNum",pageInfo.getPageNum());
            mv.addObject("pages",pageInfo.getPages());
        }
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/delById")
    public ModelAndView delById(Integer id,Integer pn){
        ModelAndView mv=new ModelAndView();
        productService.delById(id);
        PageInfo<Product> pageInfo=productService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("productList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/add")
    public ModelAndView add(Product product){
        ModelAndView mv=new ModelAndView();
        if (product!=null){
            productService.add(product);
        }
        PageInfo<Product> pageInfo=productService.findByPage(1);
        List list=pageInfo.getList();
        mv.addObject("productList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/updateById")
    public ModelAndView updateById(Product product,Integer pn){
        ModelAndView mv=new ModelAndView();
        productService.updateById(product);
        PageInfo<Product> pageInfo=productService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("productList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/findByPage")
    public ModelAndView find(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn){
        ModelAndView mv=new ModelAndView();
        PageInfo<Product> pageInfo=productService.findByPage(pn);
        List list=pageInfo.getList();
        mv.addObject("productList",list);
        mv.addObject("pageNum",pageInfo.getPageNum());
        mv.addObject("pages",pageInfo.getPages());
        mv.addObject("flag",true);
        mv.setViewName("index");
        return mv;
    }
}
