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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;
    @RequestMapping("/list")
    public RootData list(){
        List<ProductCategory> list=productCategoryService.getAllProductCategory();
        return ResultUtil.success(list);
    }
    @PostMapping("/create")
    public RootData create(@RequestBody @Valid CategoryCreateForm categoryForm){
        ProductCategory productCategory=productCategoryService.addProductCategory(categoryForm.getCategoryName(),categoryForm.getCategoryType());
        return ResultUtil.success(productCategory);
    }
    @PostMapping("/cancel")
    public RootData delete(@RequestBody @Valid CategoryForm categoryForm){
        List<ProductInfo> productCategoryList=productInfoService.findByCategoryType(categoryForm.getCategoryType());
        if (productCategoryList.size()!=0){
            throw new SellException(ResultMapping.CATEGORY_PRODUCT_EXIT);
        }
        productCategoryService.deleteProductCategory(categoryForm.getCategoryType());
        return ResultUtil.success("success");
    }
    @PostMapping("/change")
    public RootData change(@RequestBody @Valid CategoryForm categoryForm){
        ProductCategory productCategory=productCategoryService.getProductCategory(categoryForm.getCategoryId());
        if (!productCategory.getCategoryName().equals(categoryForm.getCategoryName())){
            productCategory.setCategoryName(categoryForm.getCategoryName());
        }
        if (!productCategory.getCategoryType().equals(categoryForm.getCategoryName())){
            productCategory.setCategoryType(categoryForm.getCategoryType());
        }
        productCategoryService.save(productCategory);
        return ResultUtil.success(productCategory);
    }

}
