package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.service.CategoryService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals("热销榜",productCategory.getCategoryName());
        productCategory = categoryService.findOne(10);
        Assert.assertEquals(null, productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertEquals(3, all.size());

    }

    @Test
    public void findByCategoryTypeIn() {

    }

    @Test
    public void save() {
    }
}