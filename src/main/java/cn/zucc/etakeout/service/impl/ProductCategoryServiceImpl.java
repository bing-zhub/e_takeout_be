package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.ProductCategoryDAO;
import cn.zucc.etakeout.dto.CartDTO;
import cn.zucc.etakeout.service.ProductCategoryService;
import cn.zucc.etakeout.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public ProductCategoryService addProductCategoryService(String categoryName, Integer categoryType) {
        ProductCategory one=productCategoryDAO.findOne(categoryType);
        if (one==null){
            List<ProductCategory> all=productCategoryDAO.findAll();
            for (ProductCategory one:)
            List<Integer> categoryid=
        }

    }

    @Override
    public void deleteProductCategory(Integer categoryType) {

    }

    @Override
    public void changeCategoryName(Integer categoryType, String categoryName) {

    }

    @Override
    public void changeCategoryType(Integer categoryType) {

    }

    @Override
    public void getProductCategory(Integer categoryType) {

    }
}
