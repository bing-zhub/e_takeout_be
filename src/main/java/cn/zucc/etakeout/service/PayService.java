package cn.zucc.etakeout.service;


import cn.zucc.etakeout.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);
}