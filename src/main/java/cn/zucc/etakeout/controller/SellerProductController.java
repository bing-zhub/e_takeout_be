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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.Controller;

import javax.validation.Valid;
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

    public RootData addProduct(@RequestBody @Valid ProductCreateForm productCreateForm){

        ProductInfo productInfo=new ProductInfo();
        BeanUtils.copyProperties(productCreateForm, productInfo);
        ProductInfo save = productInfoService.save(productInfo);
        return ResultUtil.success(save);
    }

    @PostMapping("/delete")

    public RootData deleteProduct(@RequestBody @Valid ProductForm productForm){
         ProductInfo productInfo= productInfoService.delete(productForm.getProductId());
        return ResultUtil.success(productInfo);
    }
    @PostMapping("/update")
    public RootData changeProduct(@RequestBody @Valid ProductForm productForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new SellException(ResultMapping.ORDER_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
//        ProductInfo productInfo1=new ProductInfo();
//        ProductInfo productInfo = productInfoService.findOne(productForm.getProductId());
//        if (productInfo != null) {
//
//            BeanUtils.copyProperties(productForm,productInfo1);
//            productInfo1.setProductStock(productInfo.getProductStock());
//            productInfo1.setProductStatus(productInfo.getProductStatus());
//            productInfoService.save(productInfo1);
//        } else {
//            throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
//        }
        return ResultUtil.success(productInfoService.update(productForm));
    }


}
