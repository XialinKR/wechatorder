package com.it.wechatorder.controller;

import com.it.wechatorder.domain.ProductCategory;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.form.CategoryForm;
import com.it.wechatorder.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String, Object> map){
        if (categoryId!=null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory =new ProductCategory();
        try {
            if (categoryForm.getCategoryId()!=null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.saveOrUpdate(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}