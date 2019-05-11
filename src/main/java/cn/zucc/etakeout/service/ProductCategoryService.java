package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory addProductCategory(String categoryName, Integer categoryType);
    ProductCategory save(ProductCategory productCategory);
    void deleteProductCategory(Integer categoryType);

    ProductCategory updateProductCategory(ProductCategory productCategory);

    ProductCategory getProductCategory(Integer categoryId);
    List<ProductCategory> getAllProductCategory();

}
