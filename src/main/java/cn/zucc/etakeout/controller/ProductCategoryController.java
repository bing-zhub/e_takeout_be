package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.form.CategoryCreateForm;
import cn.zucc.etakeout.form.CategoryForm;
import cn.zucc.etakeout.service.ProductCategoryService;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @RequestMapping("/list")
    public RootData list(){
        List<ProductCategory> list=productCategoryService.getAllProductCategory();
        return ResultUtil.success(list);
    }

    @PostMapping("/create")
    public RootData create(@RequestBody CategoryCreateForm categoryForm){
        ProductCategory productCategory=productCategoryService.addProductCategory(categoryForm.getCategoryName(),categoryForm.getCategoryType());
        return ResultUtil.success(productCategory);
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
