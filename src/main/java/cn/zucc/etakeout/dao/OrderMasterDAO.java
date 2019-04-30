package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface OrderMasterDAO extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByConsumerOpenid(String consumerOpenId, Pageable pageable);
}
