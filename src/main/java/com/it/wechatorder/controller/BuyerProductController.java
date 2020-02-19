package com.it.wechatorder.controller;

import com.it.wechatorder.VO.ProductInfoVO;
import com.it.wechatorder.VO.ProductVO;
import com.it.wechatorder.VO.ResultVO;
import com.it.wechatorder.domain.ProductCategory;
import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.service.CategoryService;
import com.it.wechatorder.service.ProductService;
import com.it.wechatorder.uitls.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService CategoryService;

    @GetMapping("/list")
//    @Cacheable(cacheNames = "project",key = "123")
    public ResultVO list(){
        /*查询所有上架商品*/
        List<ProductInfo> productInfoList = productService.findUpAll();
        /*（一次性查询）商品类目*/
        List<Integer> productCategoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = CategoryService.findByCategoryTypeIn(productCategoryTypeList);
        /*数据拼接*/
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
