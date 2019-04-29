package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.ProductInfoDAO;
import cn.zucc.etakeout.mappings.ProductStatusMapping;
import cn.zucc.etakeout.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService{

    @Autowired
    ProductInfoDAO productInfoDAO;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDAO.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDAO.findByProductStatus(ProductStatusMapping.ONSALE.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDAO.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDAO.save(productInfo);
    }
}
