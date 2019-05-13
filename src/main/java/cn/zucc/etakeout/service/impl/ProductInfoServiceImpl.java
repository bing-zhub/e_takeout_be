package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.ProductInfoDAO;
import cn.zucc.etakeout.dto.CartDTO;
import cn.zucc.etakeout.exception.SellException;
import cn.zucc.etakeout.form.ProductForm;
import cn.zucc.etakeout.mappings.ProductStatusMapping;
import cn.zucc.etakeout.mappings.ResultMapping;
import cn.zucc.etakeout.mappings.StatisMapping;
import cn.zucc.etakeout.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoDAO productInfoDAO;

    @Override
    public ProductInfo findOne(int productId) {
        return productInfoDAO.findOne(productId);
    }

    // 查询所有上架商品
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDAO.findByProductStatus(ProductStatusMapping.ONSALE.getCode());
    }

    // 查询所有商品
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDAO.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDAO.save(productInfo);
    }

    @Override
    public ProductInfo update(ProductForm productForm) {
        ProductInfo one = productInfoDAO.findOne(productForm.getProductId());
        if(one==null){
            throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
        }
        ProductInfo other = new ProductInfo();
        BeanUtils.copyProperties(productForm, other);
        other.setProductStock(one.getProductStock());
        other.setProductStatus(one.getProductStatus());
        other.setProductSellCount(one.getProductSellCount());
        return productInfoDAO.save(other);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo one = productInfoDAO.findOne(cartDTO.getProductId());
            if(one == null) {
                throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
            }
            int leftInStock = one.getProductStock() + cartDTO.getProductQuantity();

            one.setProductStock(leftInStock);
            productInfoDAO.save(one);
        }
    }

    @Override
    // 事务型 不成功全回滚
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo one = productInfoDAO.findOne(cartDTO.getProductId());
            if(one == null) {
                throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
            }
            int leftInStock = one.getProductStock() - cartDTO.getProductQuantity();
            if(leftInStock<0){
                throw new SellException(ResultMapping.OUT_OF_STOCK);
            }

            one.setProductStock(leftInStock);
            productInfoDAO.save(one);
        }
    }

    @Override
    @Transactional
    public ProductInfo delete(int productInfoId) {
        ProductInfo productInfo=productInfoDAO.findOne(productInfoId);
        if (productInfo!=null){
            productInfoDAO.delete(productInfo);
        }
        else {
            throw new SellException(ResultMapping.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

    @Override
    public List<ProductInfo> count() {
        return productInfoDAO.findAll();
    }

    @Override
    public List<ProductInfo> findByCategoryType(int categoryType) {
        List<ProductInfo> productInfoList=productInfoDAO.findByCategoryType(categoryType);
        return  productInfoList;
    }

    @Override
    public List<Double> getStatics(Integer code) {
        List<Double> staticDTOS = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Object[]> statics = new LinkedList<>();
        if (code.equals(StatisMapping.ProductAdded.getCode())) {
            statics = productInfoDAO.getCountOfProductAddedInOneWeek();
        } else if (code.equals(StatisMapping.Income.getCode())) {
            statics = productInfoDAO.getIncomeInOneWeek();
        } else if (code.equals(StatisMapping.AverageConsumption.getCode())) {
            statics = productInfoDAO.getAverage();
        } else if (code.equals(StatisMapping.OrderClosed.getCode())) {
            statics = productInfoDAO.getOrderClosed();
        }
        for (Object[] col : statics) {
            if (col[1] instanceof BigInteger)
                staticDTOS.set((Integer) col[0], ((BigInteger) col[1]).doubleValue());
            else if (col[1] instanceof BigDecimal)
                staticDTOS.set((Integer) col[0], ((BigDecimal) col[1]).doubleValue());
        }
        return staticDTOS;
    }

}
