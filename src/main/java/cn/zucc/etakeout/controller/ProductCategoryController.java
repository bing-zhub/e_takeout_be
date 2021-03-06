package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.data.RootData;

import cn.zucc.etakeout.exception.SellException;

import cn.zucc.etakeout.form.CategoryCreateForm;
import cn.zucc.etakeout.form.CategoryForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.ProductCategoryService;
import cn.zucc.etakeout.service.ProductInfoService;
import cn.zucc.etakeout.util.ResultUtil;
import org.simpleframework.xml.Root;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public RootData list(){
        List<ProductCategory> list=productCategoryService.getAllProductCategory();
        return ResultUtil.success(list);
    }

    @PostMapping("/create")
    public RootData create(@RequestBody @Valid CategoryCreateForm categoryCreateForm){
        ProductCategory productCategory=productCategoryService.addProductCategory(categoryCreateForm.getCategoryName(),categoryCreateForm.getCategoryType());
        return ResultUtil.success(productCategory);
    }

    @PostMapping("/import")
    public RootData importCategories(@RequestBody List<CategoryCreateForm> categoryCreateForms){
        for(CategoryCreateForm form: categoryCreateForms){
            productCategoryService.addProductCategory(form.getCategoryName(), form.getCategoryType());
        }
        return ResultUtil.success(categoryCreateForms.size());
    }

    @PostMapping("/delete")
    public RootData delete(@RequestBody CategoryForm categoryForm){
        productCategoryService.deleteProductCategory(categoryForm.getCategoryId());
        return ResultUtil.success("success");
    }

    @PostMapping("/update")
    public RootData change(@RequestBody CategoryForm categoryForm){
        ProductCategory productCategory=productCategoryService.getProductCategory(categoryForm.getCategoryId());
        productCategory.setCategoryName(categoryForm.getCategoryName());
        productCategory.setCategoryType(categoryForm.getCategoryType());
        productCategory = productCategoryService.updateProductCategory(productCategory);
        return ResultUtil.success(productCategory);
    }

}
