package cn.zucc.etakeout.controller;

import cn.zucc.etakeout.bean.ProductCategory;
import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.data.ProductData;
import cn.zucc.etakeout.data.ProductInfoData;
import cn.zucc.etakeout.data.RootData;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.ProductCreateForm;
import cn.zucc.etakeout.form.ProductForm;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.service.CategoryService;
import cn.zucc.etakeout.service.ProductInfoService;
import cn.zucc.etakeout.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/seller/product")
public class SellerProductController  {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public RootData list(){

        // 查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 将上架商品的类目名称添加添加到列表
        List<Integer> categoryTypeList = new LinkedList<>();
        for(ProductInfo info: productInfoList){
            categoryTypeList.add(info.getCategoryType());
        }

        // 获取上架商品所在的类别列表
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 最终返回
        List<ProductData> productDataList = new LinkedList<>();

        // 对类别遍历
        for(ProductCategory category: productCategories){
            ProductData productData = new ProductData();
            productData.setCategoryType(category.getCategoryType());
            productData.setCategoryName(category.getCategoryName());

            // 每个类别下的产品详情信息
            List<ProductInfoData> infos = new LinkedList<>();

            // 对产品遍历
            for(ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoData productInfoData = new ProductInfoData();
                    BeanUtils.copyProperties(productInfo, productInfoData);
                    infos.add(productInfoData);
                }
            }
            productData.setProductInfoList(infos);
            productDataList.add(productData);
        }

        return ResultUtil.success(productDataList);
    }

    @RequestMapping("/create")
    public RootData addProduct(@RequestBody ProductCreateForm productCreateForm){
        ProductInfo productInfo=new ProductInfo();
        BeanUtils.copyProperties(productCreateForm, productInfo);
        ProductInfo save = productInfoService.save(productInfo);
        return ResultUtil.success(save);
    }

    @RequestMapping("/delete")
    public RootData delete(@RequestBody ProductForm productForm){
        ProductInfo productInfo= productInfoService.delete(productForm.getProductId());
        return ResultUtil.success(productInfo);
    }
    @RequestMapping("/update")
    public RootData changeProduct(@RequestBody ProductForm productForm){
        ProductInfo update = productInfoService.update(productForm);
        return ResultUtil.success(update);
    }
}
