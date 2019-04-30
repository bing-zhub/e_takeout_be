package cn.zucc.etakeout.dao;

import cn.zucc.etakeout.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{
    List<OrderDetail> findByOrderId(String orderId);
}
