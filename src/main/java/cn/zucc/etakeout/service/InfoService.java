package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InfoService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save (ProductInfo productInfo);

}
