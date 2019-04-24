package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDAOTest {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Test
    public void findOne(){
        ProductCategory productCategory =  productCategoryDAO.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    //测试使用 会回滚
    public void saveOne(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(3);
        productCategory.setCategoryName("哈哈");
        ProductCategory p  =  productCategoryDAO.save(productCategory);
        Assert.assertNotNull(p);
    }

    @Test
    public void findCategoryByTypes(){
        List<Integer> types = new LinkedList<>();
        types.add(1); types.add(3);
        List<ProductCategory> productCategories = productCategoryDAO.findByCategoryTypeIn(types);
        Assert.assertNotEquals(0,productCategories.size());
    }
}