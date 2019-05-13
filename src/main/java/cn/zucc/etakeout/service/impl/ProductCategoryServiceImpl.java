package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.dao.ProductCategoryDAO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public ProductCategory addProductCategory(String categoryName, Integer categoryType) {
        ProductCategory type=productCategoryDAO.findByCategoryType(categoryType);
        ProductCategory name=productCategoryDAO.findByCategoryName(categoryName);
        ProductCategory result=new ProductCategory();
        if (type!=null){
            throw new SellException(ResultMapping.CATRGORY_TYPE_EXIT);

        }
        else if(name!=null){
            throw new SellException(ResultMapping.CATRGORY_NAME_EXIT);
        }
        else{
            List<ProductCategory> all=productCategoryDAO.findAll();
            List<Integer> categoryid= new LinkedList<>();
            for (ProductCategory pc:all) {
                categoryid.add(pc.getCategoryId());
            }

            result.setCategoryId(Collections.max(categoryid)+1);
            result.setCategoryName(categoryName);
            result.setCategoryType(categoryType);
            productCategoryDAO.save(result);

        }
        return result;

    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        productCategoryDAO.save(productCategory);
        return productCategory;
    }

    @Override
    public void deleteProductCategory(Integer categoryId) {
        ProductCategory type=productCategoryDAO.findOne(categoryId);

        if (type==null){
            throw new SellException(ResultMapping.CATRGORY_TYPE_NO);
        }
        else{
            productCategoryDAO.delete(type.getCategoryId());
        }
    }


    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }


    @Override
    public ProductCategory getProductCategory(Integer categoryId) {
        ProductCategory type=productCategoryDAO.findOne(categoryId);
        if (type==null){
            throw new SellException(ResultMapping.CATRGORY_TYPE_NO);
        }
        else {
            return type;
        }
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryDAO.findAll();
    }
}
