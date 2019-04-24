package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
    ProductCategory save(ProductCategory productCategory);
}
