package cn.zucc.etakeout.service;

import cn.zucc.etakeout.bean.ProductInfo;
import cn.zucc.etakeout.dto.CartDTO;
import cn.zucc.etakeout.form.ProductForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(int productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    ProductInfo update(ProductForm productForm);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    ProductInfo delete(int productInfoId);
}
