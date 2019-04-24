package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.dao.ProductCategoryDAO;
import cn.zucc.etakeout.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDAO.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDAO.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return productCategoryDAO.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }
}
