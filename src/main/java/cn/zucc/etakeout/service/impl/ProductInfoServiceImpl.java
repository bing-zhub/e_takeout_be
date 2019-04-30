package cn.zucc.etakeout.service.impl;

import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dao.ProductInfoDAO;
import cn.zucc.etakeout.dto.CartDTO;
import cn.zucc.etakeout.mappings.ProductStatusMapping;
import cn.zucc.etakeout.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

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

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        // TODO
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        // TODO
    }
}
