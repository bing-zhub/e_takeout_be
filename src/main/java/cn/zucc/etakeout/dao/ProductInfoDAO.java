package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDAO extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer Status);
}
