package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.form.CategoryForm;
import cn.zucc.etakeout.service.ProductCategoryService;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RootData create(CategoryForm categoryForm){
        ProductCategory productCategory=productCategoryService.addProductCategory(categoryForm.getCategoryName(),categoryForm.getCategoryType());
        return ResultUtil.success(productCategory);
    }
    @PostMapping("/cancel")
    public RootData delete(CategoryForm categoryForm){
        productCategoryService.deleteProductCategory(categoryForm.getCategoryType());
        return ResultUtil.success("success");
    }
    @PostMapping("/change")
    public RootData change(CategoryForm categoryForm){
        ProductCategory productCategory=productCategoryService.getProductCategory(categoryForm.getCategoryId());
        if (!productCategory.getCategoryName().equals(categoryForm.getCategoryName())){
            productCategoryService.changeCategoryName(categoryForm.getCategoryType(),categoryForm.getCategoryName());
        }
        if (!productCategory.getCategoryType().equals(categoryForm.getCategoryName())){
            productCategoryService.changeCategoryName(categoryForm.getCategoryType(),categoryForm.getCategoryName());
        }
        return ResultUtil.success(productCategory);
    }

}
