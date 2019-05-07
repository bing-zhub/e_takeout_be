package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory addProductCategory(String categoryName, Integer categoryType);
    void deleteProductCategory(Integer categoryType);
    void changeCategoryName(Integer categoryId,String categoryName);
    void changeCategoryType(Integer categoryId,Integer newCategoryType);
    ProductCategory getProductCategory(Integer categoryId);
    List<ProductCategory> getAllProductCategory();

}
